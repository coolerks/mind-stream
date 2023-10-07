package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.integer.blog.model.entity.Files;

import java.util.List;

/**
 * 文件 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface FilesMapper extends BaseMapper<Files> {

    void complete(@Param("complete") List<Long> complete);
}
