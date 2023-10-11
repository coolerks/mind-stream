package top.integer.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.integer.blog.annotation.PermissionCheck;
import top.integer.blog.enums.Permission;
import top.integer.blog.model.dto.FileDeleteDto;
import top.integer.blog.model.dto.FilePageQueryDto;
import top.integer.blog.model.dto.FileUploadDto;
import top.integer.blog.model.dto.FolderDto;
import top.integer.blog.model.vo.file.FileDetailVo;
import top.integer.blog.model.vo.file.FileItemVo;
import top.integer.blog.model.vo.PageVo;
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
    @PermissionCheck(Permission.UPLOAD_FILE)
    public R<Map<String, String>> getUploadConfig() {
        return R.ok(fileService.getObjectStorageConfig());
    }

    @Operation(summary = "请求上传文件")
    @PostMapping("/upload/request")
    @PermissionCheck(Permission.UPLOAD_FILE)
    public R<UploadRequestResponseVo> uploadRequest(@Validated @RequestBody FileUploadDto dto) {
        UploadRequestResponseVo result = fileService.uploadRequest(dto);
        return R.ok(result);
    }

    @Operation(summary = "上传图片", parameters = {
            @Parameter(name = "folderId"),
            @Parameter(name = "name"),
            @Parameter(name = "size")
    })
    @PostMapping(value = "/upload")
    @PermissionCheck(Permission.UPLOAD_FILE)
    public R<Long> upload(@RequestPart("file") MultipartFile multipartFile, @Validated FileUploadDto dto) {
        Long id = fileService.upload(multipartFile, dto);
        return R.ok(id);
    }

    @PostMapping("/folder")
    @Operation(summary = "创建文件夹")
    @PermissionCheck(Permission.CREATE_FOLDER)
    public R<String> mkdir(@Validated @RequestBody FolderDto dto) {
        fileService.mkdir(dto);
        return R.ok();
    }

    @Operation(summary = "文件上传完成")
    @PostMapping("/upload/complete/{id}")
    @PermissionCheck(Permission.UPLOAD_FILE)
    public R<String> uploadComplete(@PathVariable Long id) {
        fileService.uploadComplete(id);
        return R.ok();
    }

    @Operation(summary = "分页获取文件和文件夹", parameters = {
            @Parameter(name = "folderId"),
            @Parameter(name = "pageNumber"),
            @Parameter(name = "pageSize")
    })
    @PermissionCheck(Permission.GET_FILE_LIST)
    @GetMapping("/page")
    public R<PageVo<FileItemVo>> pageFiles(FilePageQueryDto dto) {
        PageVo<FileItemVo> page = fileService.pageFiles(dto);
        return R.ok(page);
    }

    @Operation(summary = "查看文件详情")
    @GetMapping("/detail/{id}")
    @PermissionCheck(Permission.GET_FILE_LIST)
    public R<FileDetailVo> fileDetail(@PathVariable Long id) {
        FileDetailVo detail = fileService.getDetail(id);
        return R.ok(detail);
    }

    @Operation(summary = "删除文件和文件夹")
    @DeleteMapping("/")
    @PermissionCheck(Permission.DELETE_FILE)
    public R<String> fileDelete(@RequestBody FileDeleteDto dto) {
        fileService.deleteFile(dto);
        return R.ok();
    }
}
