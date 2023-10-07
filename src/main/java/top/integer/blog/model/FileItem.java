package top.integer.blog.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileItem {
    private Long id;
    private String path, redirectUrl;
    private Integer timeout;
}
