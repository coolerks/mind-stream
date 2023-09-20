package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.integer.blog.model.entity.Permissions;

/**
 * 权限 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface PermissionsMapper extends BaseMapper<Permissions> {

}
