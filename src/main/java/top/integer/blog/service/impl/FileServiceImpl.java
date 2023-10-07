package top.integer.blog.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.integer.blog.enums.FileStatus;
import top.integer.blog.exception.DataException;
import top.integer.blog.file.FileManager;
import top.integer.blog.file.FileManagers;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.model.def.FilesDef;
import top.integer.blog.model.def.FolderDef;
import top.integer.blog.model.dto.FileUploadDto;
import top.integer.blog.model.dto.FolderDto;
import top.integer.blog.model.entity.Files;
import top.integer.blog.model.entity.Folder;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;
import top.integer.blog.properties.ObjectStorage;
import top.integer.blog.service.FileService;
import top.integer.blog.utils.IpUtils;
import top.integer.blog.utils.UserUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService, InitializingBean {
    private final FileManagers fileManagers;
    private final FilesMapper filesMapper;
    private final FolderMapper folderMapper;
    private FileManager fileManager;
    private ObjectStorage objectStorage;


    public Map<String, String> getObjectStorageConfig() {
        String protocol = objectStorage.getHttpProtocol().toString() + "://";
        String url = (objectStorage.getDomain().startsWith("http://") ||
                objectStorage.getDomain().startsWith("https://")) ? objectStorage.getDomain() : protocol + objectStorage.getDomain();
        return Map.of(
                "url", url,
                "method", "post"
        );
    }

    @Override
    public UploadRequestResponseVo uploadRequest(FileUploadDto dto) {
        Files files = getFiles(dto);
        filesMapper.insert(files);
        return fileManager.requestUpload(files.getFullPath(), "http://localhost:8080?id=" + files.getId(), 5);
    }


    @Override
    public void uploadComplete(Long id) {
        Long userId = UserUtils.getUserId();
        FilesDef f = FilesDef.FILES;

        QueryWrapper wrapper = QueryWrapper.create()
                .select(f.FULL_PATH)
                .from(f)
                .where(f.USER_ID.eq(userId))
                .and(f.ID.eq(id));
        Files files = this.filesMapper.selectOneByQuery(wrapper);

        Optional.ofNullable(files)
                .map(Files::getFullPath)
                .ifPresentOrElse(p -> {
                    if (!fileManager.fileExist(p)) {
                        throw new DataException("尚未上传完成");
                    }
                    this.filesMapper.update(Files.builder().id(id).status(FileStatus.UPLOADED.code).build());
                }, () -> {
                    throw new DataException("未找到文件");
                });
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long upload(MultipartFile multipartFile, FileUploadDto dto) {
        Files files = getFiles(dto);
        try {
            filesMapper.insert(files);
            if (!fileManager.upload(files.getFullPath(), multipartFile.getInputStream())) {
                throw new DataException("上传失败");
            }
            // todo 压缩图片

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.uploadComplete(files.getId());
        return files.getId();
    }

    @Override
    public void mkdir(FolderDto dto) {
        Long userId = UserUtils.getUserId();
        LocalDateTime now = LocalDateTime.now();
        long ipLong = IpUtils.getIpLong();

        Folder parent = getParentFolder(dto);
        if (dto.getParentId() != 0L && parent == null) {
            throw new DataException("父文件夹不存在");
        }

        String fullPath = dto.getParentId() == 0L ? "/" + dto.getName() : parent.getFullPath() + "/" + dto.getName();

        Folder folder = Folder.builder()
                .parentId(dto.getParentId())
                .userId(userId)
                .name(dto.getName())
                .description(dto.getDescription())
                .fullPath(fullPath)
                .createTime(now)
                .updateTime(now)
                .createIp(ipLong)
                .build();

        if (existFolder(dto)) {
            throw new DataException("已存在此文件夹，请勿重复创建");
        }

        this.folderMapper.insert(folder);
    }

    /**
     * 通过dto获取Files实例
     *
     * @param dto
     * @return
     */
    private Files getFiles(FileUploadDto dto) {
        if (dto.getFolderId() != 0 && !existFolder(dto.getFolderId())) {
            throw new DataException("文件夹不存在");
        }
        Long userId = UserUtils.getUserId();
        LocalDateTime now = LocalDateTime.now();
        long ip = IpUtils.getIpLong();

        return Files.builder()
                .folderId(dto.getFolderId())
                .name(dto.getName())
                .fullPath(FileManager.getFilePath(dto.getName()))
                .compressPath("")
                .size(dto.getSize())
                .userId(userId)
                .status(FileStatus.NOT_UPLOADED.code)
                .policy(objectStorage.getMode().code)
                .createTime(now)
                .updateTime(now)
                .createIp(ip)
                .build();
    }

    /**
     * 获取父文件夹
     *
     * @param dto
     * @return
     */
    private Folder getParentFolder(FolderDto dto) {
        FolderDef f = FolderDef.FOLDER;
        return folderMapper.selectOneByQuery(
                QueryWrapper.create()
                        .select(f.FULL_PATH)
                        .from(f)
                        .where(f.ID.eq(dto.getParentId()))
        );
    }

    /**
     * 是否存在文件夹
     *
     * @param dto
     * @return
     */
    private boolean existFolder(FolderDto dto) {
        FolderDef f = FolderDef.FOLDER;
        return this.folderMapper.selectCountByQuery(
                QueryWrapper.create()
                        .select(f.ID)
                        .from(f)
                        .where(f.PARENT_ID.eq(dto.getParentId()))
                        .and(f.NAME.eq(dto.getName()))
        ) != 0;
    }

    /**
     * 是否存在文件夹
     *
     * @param id
     * @return
     */
    private boolean existFolder(Long id) {
        FolderDef f = FolderDef.FOLDER;
        return this.folderMapper.selectCountByQuery(
                QueryWrapper.create()
                        .select(f.ID)
                        .from(f)
                        .where(f.ID.eq(id))
        ) != 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.fileManager = fileManagers.getFileManager();
        this.objectStorage = fileManagers.getObjectStorage();
    }
}
