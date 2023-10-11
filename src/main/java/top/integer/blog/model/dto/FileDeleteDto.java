package top.integer.blog.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FileDeleteDto {
    private List<Long> folderId;
    private List<Long> fileId;
}
