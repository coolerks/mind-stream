package top.integer.blog.file;

import com.mybatisflex.core.query.QueryWrapper;
import top.integer.blog.enums.FileStatus;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.model.def.FilesDef;
import top.integer.blog.model.entity.Files;
import top.integer.blog.properties.ObjectStorage;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractFileManager implements FileManager {
    protected final ObjectStorage objectStorage;
    protected final FilesMapper filesMapper;
    protected final FolderMapper folderMapper;

    @Override
    public boolean mkdir(String path) {
        return false;
    }

    @Override
    public void deleteInvalidFile() {
        FilesDef f = FilesDef.FILES;
        int code = objectStorage.getMode().code;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(f.ID, f.FULL_PATH)
                .where(f.STATUS.eq(FileStatus.NOT_UPLOADED))
                .and(f.POLICY.eq(code))
                .and(f.CREATE_TIME.le(LocalDateTime.now().minusMinutes(5)));
        List<Files> files = filesMapper.selectListByQuery(wrapper);

        // 找出所有上传完成的id
        List<Long> complete = files.stream()
                .filter(it -> fileExist(it.getFullPath()))
                .map(Files::getId)
                .toList();

        // 找出符合条件的文件id，并去除所有上传完成的id
        Set<Long> notComplete = files.stream()
                .map(Files::getId)
                .collect(Collectors.toSet());
        complete.forEach(notComplete::remove);

        if (!complete.isEmpty()) {
            filesMapper.complete(complete);
        }
        if (!notComplete.isEmpty()) {
            filesMapper.deleteBatchByIds(notComplete);
        }
    }

    protected AbstractFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        this.objectStorage = objectStorage;
        this.filesMapper = filesMapper;
        this.folderMapper = folderMapper;
    }
}
