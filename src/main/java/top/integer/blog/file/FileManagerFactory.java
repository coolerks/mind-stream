package top.integer.blog.file;

import top.integer.blog.file.chain.FileManagerHandler;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.properties.ObjectStorage;

public class FileManagerFactory implements FileManagerHandler {
    FileManagerHandler next;
    @Override
    public FileManager getFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper) {
        return next.getFileManager(objectStorage, filesMapper, folderMapper);
    }

    public FileManagerFactory(FileManagerHandler next) {
        this.next = next;
    }
}
