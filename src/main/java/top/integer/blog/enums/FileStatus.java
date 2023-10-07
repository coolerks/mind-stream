package top.integer.blog.enums;

public enum FileStatus {


    NOT_UPLOADED(0, "未上传"),
    UPLOADED(1, "已上传");

    public final int code;
    public final String status;

    FileStatus(int code, String status) {
        this.code = code;
        this.status = status;
    }
}
