package top.integer.blog.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.integer.blog.constant.VariableConstant;
import top.integer.blog.operation.VariableOperation;

import java.util.List;

@Component
public class InitializeData implements InitializingBean {
    private List<Init> initComponent;
    private static final int targetVersion = 5;
    private VariableOperation variableOperation;

    @Autowired
    public void setVariableMapper(VariableOperation variableOperation) {
        this.variableOperation = variableOperation;
    }

    @Autowired
    public void setInitComponent(List<Init> initComponent) {
        this.initComponent = initComponent;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        int currentVersion = variableOperation.getValueInt(VariableConstant.VERSION);
        for (Init init : initComponent) {
            init.doInit(currentVersion);
        }
        if (currentVersion != targetVersion) {
            variableOperation.setValue(VariableConstant.VERSION, targetVersion);
        }
    }
}
