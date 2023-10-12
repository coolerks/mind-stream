package top.integer.blog.model.entity;

import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "环境变量")
@Table(value = "ums_variable")
public class Variable {
    private Long id;
    private String name;
    private String value;
    private String type;
}
