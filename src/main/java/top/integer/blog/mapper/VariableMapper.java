package top.integer.blog.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.integer.blog.model.entity.Variable;

/**
 * 环境变量
 */
@Mapper
public interface VariableMapper extends BaseMapper<Variable> {
}
