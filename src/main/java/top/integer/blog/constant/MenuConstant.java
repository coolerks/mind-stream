package top.integer.blog.constant;

import top.integer.blog.annotation.Version;
import top.integer.blog.model.entity.Menu;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单常量类
 */
public class MenuConstant {
    public static List<Menu> fileMenus() {
        LocalDateTime now = LocalDateTime.now();
        Menu allFiles = Menu.builder().id(10L).parentId(4L)
                .menuName("全部文件").description("全部文件").menuUri("/file/list")
                .isShow(true).icon("").order(1)
                .createTime(now).updateTime(now)
                .build();
        Menu imageManager = Menu.builder().id(11L).parentId(4L)
                .menuName("图片管理").description("管理图片").menuUri("/file/image")
                .isShow(true).icon("").order(2)
                .createTime(now).updateTime(now)
                .build();
        return List.of(allFiles, imageManager);
    }

    @Version(1)
    public static List<Menu> userMenus() {
        LocalDateTime now = LocalDateTime.now();
        Menu users = Menu.builder().id(8L).parentId(5L)
                .menuName("用户管理").description("全部用户").menuUri("/users/list")
                .isShow(true).icon("").order(1)
                .createTime(now).updateTime(now)
                .build();
        Menu roles = Menu.builder().id(9L).parentId(5L)
                .menuName("角色管理").description("全部角色").menuUri("/users/role")
                .isShow(true).icon("").order(2)
                .createTime(now).updateTime(now)
                .build();
        return List.of(users, roles);
    }

    @Version(1)
    public static List<Menu> rootMenu() {
        LocalDateTime now = LocalDateTime.now();
        Menu dashboard = Menu.builder().id(1L).parentId(0L)
                .menuName("仪表盘").description("仪表盘信息").menuUri("/dashboard")
                .isShow(true).icon("Dashboard").order(1)
                .createTime(now).updateTime(now)
                .build();
        Menu article = Menu.builder().id(2L).parentId(0L)
                .menuName("文章").description("文章菜单").menuUri("/article")
                .isShow(true).icon("FileText").order(2)
                .createTime(now).updateTime(now)
                .build();
        Menu comment = Menu.builder().id(3L).parentId(0L)
                .menuName("评论").description("评论菜单").menuUri("/comment")
                .isShow(true).icon("Comment").order(3)
                .createTime(now).updateTime(now)
                .build();
        Menu file = Menu.builder().id(4L).parentId(0L)
                .menuName("附件").description("附件管理").menuUri("/file")
                .isShow(true).icon("Snippets").order(4)
                .createTime(now).updateTime(now)
                .build();
        Menu user = Menu.builder().id(5L).parentId(0L)
                .menuName("用户").description("用户管理").menuUri("/users")
                .isShow(true).icon("User").order(5)
                .createTime(now).updateTime(now)
                .build();
        Menu theme = Menu.builder().id(6L).parentId(0L)
                .menuName("主题").description("主题管理").menuUri("/theme")
                .isShow(true).icon("Experiment").order(6)
                .createTime(now).updateTime(now)
                .build();
        Menu manager = Menu.builder().id(7L).parentId(0L)
                .menuName("管理").description("管理").menuUri("/manager")
                .isShow(true).icon("Setting").order(7)
                .createTime(now).updateTime(now)
                .build();
        return List.of(dashboard, article, comment, file, user, theme, manager);
    }

    private MenuConstant() {

    }
}
