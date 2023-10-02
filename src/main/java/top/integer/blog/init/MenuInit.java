package top.integer.blog.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.integer.blog.annotation.Version;
import top.integer.blog.constant.MenuConstant;
import top.integer.blog.mapper.MenuMapper;
import top.integer.blog.model.entity.Menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class MenuInit {
    private final int version;
    private final MenuMapper mapper;


    @Version(0)
    private void version1() {
        List<Menu> rootMenus = MenuConstant.rootMenu();
        List<Menu> userMenus = MenuConstant.userMenus();
        this.mapper.insertBatch(rootMenus);
        this.mapper.insertBatch(userMenus);
        log.info("新增根菜单和用户菜单成功");
    }



    private void invoke(Method m) {
        try {
            m.setAccessible(true);
            m.invoke(MenuInit.this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuInit(MenuMapper menuMapper) {
        this.version = 0;
        this.mapper = menuMapper;
        Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Version.class))
                .filter(m -> m.getAnnotation(Version.class).value() > version)
                .distinct()
                .forEach(this::invoke);
    }
}
