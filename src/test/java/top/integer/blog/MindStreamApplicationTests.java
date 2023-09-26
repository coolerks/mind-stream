package top.integer.blog;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.integer.blog.mapper.AccountInfoMapper;
import top.integer.blog.mapper.AccountUserMapper;
import top.integer.blog.model.def.AccountInfoDef;
import top.integer.blog.model.def.AccountUserDef;
import top.integer.blog.model.vo.MenusVo;
import top.integer.blog.model.vo.account.info.AccountItemVo;
import top.integer.blog.service.MenuService;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.FutureTask;

@SpringBootTest
class MindStreamApplicationTests {
    @Autowired
    RedisTemplate<String, Object> template;
    @Autowired
    AccountInfoMapper mapper;
    @Autowired
    AccountUserMapper accountUserMapper;
    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
        AccountUserDef u = AccountUserDef.ACCOUNT_USER;
        AccountInfoDef i = AccountInfoDef.ACCOUNT_INFO;
        QueryWrapper wrapper = QueryWrapper.create()
                .select(u.ID, u.USERNAME, u.EMAIL, i.NICKNAME, u.STATUS, u.LAST_LOGIN_TIME)
                .from(u)
                .join(i).on(u.ID.eq(i.ID));
        List<AccountItemVo> accountItemVos = mapper.selectListByQueryAs(wrapper, AccountItemVo.class);
        System.out.println("userItemVos = " + accountItemVos);
        Page<AccountItemVo> page = new Page<>(1, 10);
        Page<AccountItemVo> page2 = mapper.paginateAs(page, wrapper, AccountItemVo.class);
        System.out.println("(page == page2) = " + (page == page2));
        System.out.println("page = " + page);
        System.out.println("page2 = " + page2);

    }

    @Test
    void userMenus() {
        List<MenusVo> menusVos = menuService.userMenu();
        System.out.println("menusVos = " + menusVos);
    }


}
