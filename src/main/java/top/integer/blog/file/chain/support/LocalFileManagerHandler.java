package top.integer.blog.file.chain.support;

import top.integer.blog.enums.FileStorageStrategy;
import top.integer.blog.file.FileManager;
import top.integer.blog.file.chain.FileManagerHandler;
import top.integer.blog.file.support.LocalFileManager;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.properties.ObjectStorage;

public class LocalFileManagerHandler implements FileManagerHandler {
    private final FileManagerHandler next;

    public LocalFileManagerHandler() {
        this.next = new TencentCosFileManagerHandler();
    }

    @Override
    public FileManager getFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        if (objectStorage.getMode() == FileStorageStrategy.LOCAL) {
            return new LocalFileManager(objectStorage, filesMapper, folderMapper);
        }
        return next.getFileManager(objectStorage, filesMapper, folderMapper);
    }
}
