package top.integer.blog.file.chain.support;

import top.integer.blog.enums.FileStorageStrategy;
import top.integer.blog.file.FileManager;
import top.integer.blog.file.chain.FileManagerHandler;
import top.integer.blog.file.support.TencentCosFileManager;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.properties.ObjectStorage;

public class TencentCosFileManagerHandler implements FileManagerHandler {
    private FileManager next;


    @Override
    public FileManager getFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        if (objectStorage.getMode() == FileStorageStrategy.TENCENT_CLOUD) {
            return new TencentCosFileManager(objectStorage, filesMapper, folderMapper);
        }
        throw new UnsupportedOperationException();
    }
}
