package top.integer.blog.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.integer.blog.properties.FileStorageProperties;
import top.integer.blog.properties.ObjectStorage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FileManagers implements InitializingBean {
    private final List<FileManager> fileManagers;
    private final FileStorageProperties fileStorageProperties;
    @Getter
    private ObjectStorage objectStorage;
    private FileManager currentFileManager;

    public FileManager getFileManager() {
        if (objectStorage == null) {
            throw new UnsupportedOperationException("未找到相应的对象存储配置");
        }
        if (currentFileManager == null) {
            synchronized (this) {
                if (currentFileManager == null) {
                    this.currentFileManager = getFileManager(objectStorage);
                }
            }
        }
        return currentFileManager;
    }

    public ObjectStorage getObjectStorage(int policy) {
        return getFileManager(policy).getObjectStorage();
    }

    public FileManager getFileManager(int code) {
        return fileManagers.stream()
                .filter(it -> it.isSupport(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到相应的文件管理器"));
    }

    public FileManager getFileManager(ObjectStorage objectStorage) {
        return fileManagers.stream()
                .filter(it -> it.isSupport(this.objectStorage.getMode()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未找到相应的文件管理器"));
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        this.objectStorage = fileStorageProperties.getPolices().stream()
                .filter(it -> it.getMode() == fileStorageProperties.getMode())
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("无法找到当前适合的文件管理器"));
        getFileManager();
    }

    /**
     * 删除数据库中无效的记录
     */
    @Scheduled(fixedRate = 60 * 1000 * 5)
    public void deleteInvalidFile() {
        fileManagers.forEach(FileManager::deleteInvalidFile);
    }
}
