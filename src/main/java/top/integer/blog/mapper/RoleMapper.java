package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.integer.blog.model.entity.Role;
import top.integer.blog.model.vo.role.RoleDetailVo;

/**
 * 角色 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    RoleDetailVo getRoleDetailById(@Param("id") Long id);
}
