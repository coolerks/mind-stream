package top.integer.blog.model.vo.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class FileItemVo {
    private Long id, parentId;
    private String name, fullPath, compressPath;
    private Boolean folder;
    private LocalDateTime createTime;
    @JsonIgnore
    private Integer policy;
}


