package top.integer.blog.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "cross")
@Data
@ToString
public class CrossProperties {
    private List<Item> list;

    @Data
    @ToString
    public static class Item {
        private String path;
        private List<String> origin;
    }
}
