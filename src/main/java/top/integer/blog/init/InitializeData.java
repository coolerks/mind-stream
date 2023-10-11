package top.integer.blog.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializeData implements InitializingBean {
    private List<Init> initComponent;
    private static final int targetVersion = 10;

    @Autowired
    public void setInitComponent(List<Init> initComponent) {
        this.initComponent = initComponent;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // todo 读取数据库中的版本号
        int currentVersion = 4;
        for (Init init : initComponent) {
            init.doInit(currentVersion);
        }
        // 修改数据库中的版本号
        currentVersion = targetVersion;
    }
}
