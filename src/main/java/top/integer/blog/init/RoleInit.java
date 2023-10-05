package top.integer.blog.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.RoleConstant;
import top.integer.blog.mapper.RoleMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleInit implements Init {
    private final RoleMapper roleMapper;

    @Version(0)
    private void version0() {
        roleMapper.insertBatch(RoleConstant.initRole());
        log.info("初始化角色");
    }
}
