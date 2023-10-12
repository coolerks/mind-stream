package top.integer.blog.operation;

public interface VariableOperation {
    /**
     * 获取值
     *
     * @param name key
     * @param type 类型
     * @return 值
     */
    <T> T getValue(String name, Class<T> type);

    /**
     * 设置值
     * @param name key
     * @param value 值
     * @param type 类型
     * @param <T> 类型
     */
    <T> void setValue(String name, Object value, Class<T> type);

    default String getValue(String name) {
        return getValue(name, String.class);
    }

    default void setValue(String name, Object o) {
        setValue(name, o, o.getClass());
    }

    default Integer getValueInt(String name) {
        return getValue(name, Integer.class);
    }

    default Long getValueLong(String name) {
        return getValue(name, Long.class);
    }

    default Double getValueDouble(String name) {
        return getValue(name, Double.class);
    }

    default Boolean getValueBool(String name) {
        return getValue(name, Boolean.class);
    }

}
