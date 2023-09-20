package top.integer.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
@EnableAspectJAutoProxy
@EnableCaching
@EnableTransactionManagement
public class MindStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindStreamApplication.class, args);
    }

}

