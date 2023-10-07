package top.integer.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.integer.blog.file.FileManager;
import top.integer.blog.file.FileManagerFactory;
import top.integer.blog.file.chain.support.LocalFileManagerHandler;
import top.integer.blog.mapper.FilesMapper;
import top.integer.blog.mapper.FolderMapper;
import top.integer.blog.properties.FileStorageProperties;
import top.integer.blog.properties.ObjectStorage;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FileStorageProperties.class)
public class ObjectStorageConfig {
    private final FileStorageProperties fileStorageProperties;
    private final FilesMapper filesMapper;
    private final FolderMapper folderMapper;

    @Bean
    public List<FileManager> initFileManagers() {
        FileManagerFactory fileManagerFactory = new FileManagerFactory(new LocalFileManagerHandler());
        return fileStorageProperties.getPolices().stream()
                .map(it -> fileManagerFactory.getFileManager(it, filesMapper, folderMapper))
                .toList();
    }
}
