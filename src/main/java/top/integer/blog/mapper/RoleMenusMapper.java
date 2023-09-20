package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.integer.blog.model.entity.RoleMenus;

/**
 * 菜单-角色 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface RoleMenusMapper extends BaseMapper<RoleMenus> {

}
