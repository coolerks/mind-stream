package top.integer.blog.file.chain;

import top.integer.blog.file.FileManager;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.properties.ObjectStorage;

public interface FileManagerHandler {
    FileManager getFileManager(ObjectStorage objectStorage, FilesMapper filesMapper, FolderMapper folderMapper);
}
