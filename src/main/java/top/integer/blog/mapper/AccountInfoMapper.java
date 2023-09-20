package top.integer.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import top.integer.blog.model.entity.AccountInfo;

/**
 * 账户信息 映射层。
 *
 * @author moyok
 * @since 0.1
 */
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

}
