package top.integer.blog.enums;

/**
 * 文件存储方式
 */
public enum FileStorageStrategy {
    /**
     * 本地存储、阿里云、腾讯云
     */
    LOCAL(0, "本地"),
    ALI_YUN(1, "阿里云"),
    TENCENT_CLOUD(2, "腾讯云"),
    TENCENT_PUBLIC_CLOUD(3, "腾讯云公共接口"),
    NETDISK(4, "网盘");
    public final int code;
    public final String mode;

    FileStorageStrategy(int code, String mode) {
        this.code = code;
        this.mode = mode;
    }
}
