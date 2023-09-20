package top.integer.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.integer.blog.properties.CrossProperties;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(CrossProperties.class)
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final CrossProperties crossProperties;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        final String[] method = new String[]{"GET", "POST", "DELETE", "PUT", "OPTIONS"};
        List<CrossProperties.Item> list = crossProperties.getList();
        if (list != null) {
            Map<String, Set<String>> pathMap = list.stream()
                    .collect(Collectors.toMap(CrossProperties.Item::getPath, item -> {
                        Set<String> originSet = new HashSet<>(1);
                        originSet.addAll(item.getOrigin());
                        return originSet;
                    }, (a, b) -> {
                        a.addAll(b);
                        return a;
                    }));
            for (Map.Entry<String, Set<String>> item : pathMap.entrySet()) {
                String[] origins = item.getValue().toArray(new String[]{});
                registry.addMapping(item.getKey()).allowedOrigins(origins).allowedMethods(method);
            }
        }
    }
}
