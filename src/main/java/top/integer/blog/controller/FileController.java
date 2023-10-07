package top.integer.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.integer.blog.model.dto.FileUploadDto;
import top.integer.blog.model.dto.FolderDto;
import top.integer.blog.model.vo.R;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;
import top.integer.blog.service.FileService;

import java.util.Map;

@RestController
@Tag(name = "附件接口")
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Operation(summary = "获取上传配置")
    @GetMapping("/config")
    public R<Map<String, String>> getUploadConfig() {
        return R.ok(fileService.getObjectStorageConfig());
    }

    @Operation(summary = "请求上传文件")
    @PostMapping("/upload/request")
    public R<UploadRequestResponseVo> uploadRequest(@Validated @RequestBody FileUploadDto dto) {
        UploadRequestResponseVo result = fileService.uploadRequest(dto);
        return R.ok(result);
    }

    @Operation(summary = "上传图片", parameters = {
            @Parameter(name = "folderId"),
            @Parameter(name = "name"),
            @Parameter(name = "size")
    })
    @PostMapping("/upload")
    public R<Long> upload(@RequestPart("file") MultipartFile multipartFile, @Validated FileUploadDto dto) {
        Long id = fileService.upload(multipartFile, dto);
        return R.ok(id);
    }

    @PostMapping("/folder")
    @Operation(summary = "创建文件夹")
    public R<String> mkdir(@Validated @RequestBody FolderDto dto) {
        fileService.mkdir(dto);
        return R.ok();
    }

    @Operation(summary = "文件上传完成")
    @PostMapping("/upload/complete/{id}")
    public R<String> uploadComplete(@PathVariable Long id) {
        fileService.uploadComplete(id);
        return R.ok();
    }

}
