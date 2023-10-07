package top.integer.blog.service;

import org.springframework.web.multipart.MultipartFile;
import top.integer.blog.model.dto.FileUploadDto;
import top.integer.blog.model.dto.FolderDto;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;

import java.util.Map;

public interface FileService {

    Map<String, String> getObjectStorageConfig();

    UploadRequestResponseVo uploadRequest(FileUploadDto dto);

    void uploadComplete(Long id);

    Long upload(MultipartFile multipartFile, FileUploadDto dto);

    void mkdir(FolderDto dto);
}
