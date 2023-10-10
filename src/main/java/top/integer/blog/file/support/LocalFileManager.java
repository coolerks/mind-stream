package top.integer.blog.file.support;

import top.integer.blog.enums.FileStorageStrategy;
import top.integer.blog.file.AbstractFileManager;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;
import top.integer.blog.properties.ObjectStorage;

import java.io.InputStream;

public class LocalFileManager extends AbstractFileManager {
    @Override
    public UploadRequestResponseVo requestUpload(String path, String redirectUrl, Integer timeout) {
        return null;
    }

    @Override
    public boolean upload(String path, InputStream inputStream) {
        return false;
    }

    @Override
    public boolean deleteFile(String path) {
        return false;
    }

    @Override
    public boolean fileExist(String path) {
        return false;
    }

    @Override
    public boolean isSupport(FileStorageStrategy storageStrategy) {
        return storageStrategy == FileStorageStrategy.LOCAL;
    }


    @Override
    public boolean isSupport(int code) {
        return FileStorageStrategy.LOCAL.code == code;
    }

    @Override
    public ObjectStorage getObjectStorage() {
        return null;
    }

    public LocalFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        super(objectStorage, filesMapper, folderMapper);
    }
}
