package top.integer.blog.file;

import top.integer.blog.enums.FileStorageStrategy;
import top.integer.blog.model.vo.file.upload.UploadRequestResponseVo;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public interface FileManager {
    /**
     * 请求上传文件
     *
     * @param path        文件路径
     * @param redirectUrl 上传成功后回调
     * @param timeout     过期时间，单位为分钟
     * @return 上传参数
     */
    UploadRequestResponseVo requestUpload(String path, String redirectUrl, Integer timeout);


    /**
     * 文件上传
     *
     * @param path        全路径
     * @param inputStream 流
     */
    boolean upload(String path, InputStream inputStream);

    /**
     * 删除文件
     *
     * @param path 完整路径
     * @return 是否删除成功
     */
    boolean deleteFile(String path);

    /**
     * 创建文件夹
     *
     * @param path 路径
     * @return 成功/失败
     */
    boolean mkdir(String path);


    /**
     * 检查文件是否存在
     *
     * @param path 文件路径
     * @return 是否存在
     */
    boolean fileExist(String path);

    /**
     * 是否支持当前方式
     *
     * @param storageStrategy 存储方式
     * @return 是/否
     */
    boolean isSupport(FileStorageStrategy storageStrategy);

    /**
     * 是否支持当前方式
     *
     * @param code 策略
     * @return 是/否
     */
    boolean isSupport(int code);

    /**
     * 删除数据库中无效的记录
     */
    void deleteInvalidFile();


    static String getFilePath(String name) {
        String[] split = name.split("\\.");
        return new SimpleDateFormat("yyyy/MM/dd/").format(new Date()) +
                UUID.randomUUID().toString().replace("-", "") + "." + split[split.length - 1];
    }

}
