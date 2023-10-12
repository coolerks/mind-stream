package top.integer.blog.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import top.integer.blog.mapper.VariableMapper;
import top.integer.blog.model.def.VariableDef;
import top.integer.blog.model.entity.Variable;
import top.integer.blog.operation.VariableOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class VariableService implements VariableOperation, InitializingBean {
    private final VariableMapper mapper;
    private final ObjectMapper objectMapper;
    private final Map<String, Object> map = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getValue(String name, Class<T> type) {
        if (map.containsKey(name)) {
            return (T) map.get(name);
        }
        map.put(name, null);
        return null;
    }

    @Override
    @SneakyThrows
    public <T> void setValue(String name, Object value, Class<T> type) {
        VariableDef v = VariableDef.VARIABLE;
        QueryWrapper wrapper = QueryWrapper.create()
                .from(v)
                .where(v.NAME.eq(name));
        Variable.VariableBuilder builder = Variable.builder()
                .name(name)
                .type(type.getName());
        Variable va = this.mapper.selectOneByQuery(wrapper);
        if (type == Integer.class || type == Long.class || type == String.class || type == Double.class || type == Boolean.class) {
            builder.value(value.toString());
            map.put(name, value.toString());
        } else {
            String s = objectMapper.writeValueAsString(value);
            builder.value(s);
            map.put(name, value);
        }
        Variable variable = builder.build();
        if (va != null) {
            this.mapper.updateByQuery(variable, QueryWrapper.create().from(v).where(v.ID.eq(va.getId())));
        } else {
            this.mapper.insert(variable);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Variable> variables = this.mapper.selectAll();
        for (Variable variable : variables) {
            String type = variable.getType();
            String name = variable.getName();
            String value = variable.getValue();
            if (String.class.getName().equals(type)) {
                map.put(name, value);
            } else if (Boolean.class.getName().equals(type)) {
                map.put(name, Boolean.valueOf(value));
            } else if (Integer.class.getName().equals(type)) {
                map.put(name, Integer.valueOf(value));
            } else if (Long.class.getName().equals(type)) {
                map.put(name, Long.valueOf(value));
            } else if (Double.class.getName().equals(type)) {
                map.put(name, Double.valueOf(value));
            } else {
                try {
                    Class<?> clazz = Class.forName(type);
                    Object o = objectMapper.readValue(value, clazz);
                    map.put(name, o);
                } catch (Exception ignored) {

                }
            }
        }
    }
}
