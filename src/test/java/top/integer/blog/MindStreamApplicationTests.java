package top.integer.blog;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
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

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

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



class User2 {
    Integer age;
    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAge2(String age) {

    }
}

class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Getter<User, String> getter = User::getUsername;
        Method method = getter.getClass().getMethods()[0];
        System.out.println("method.getName() = " + method.getName());
        User user = new User();
//        getter.get(user);
//        Getter<User, String> getter = new Getter<User, String>() {
//            @Override
//            public String get(User instance) {
//                return instance.getUsername();
//            }
//        };
//
//        Getter<User, String> getter2 = i -> {return i.getUsername();};
//        Getter<User, String> getter3 = i -> i.getUsername();
//        Getter<User, String> getter4 = User::getUsername;
    }
}

//    public String getUsername() {
//        return this.username;
//    }

class User {
    String username;
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
@FunctionalInterface
interface Getter<T, V> {

    V get(T instance) ;

}
