package top.integer.blog.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.MenuConstant;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.model.entity.Menu;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MenuInit implements Init {
    private final MenuMapper mapper;



    @Version(0)
    private void version1() {
        List<Menu> rootMenus = MenuConstant.rootMenu();
        List<Menu> userMenus = MenuConstant.userMenus();
        this.mapper.insertBatch(rootMenus);
        this.mapper.insertBatch(userMenus);
        log.info("新增根菜单和用户菜单成功");
    }

}
