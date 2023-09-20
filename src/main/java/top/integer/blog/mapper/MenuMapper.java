package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.integer.blog.model.entity.Menu;

/**
 * 路由菜单 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}
