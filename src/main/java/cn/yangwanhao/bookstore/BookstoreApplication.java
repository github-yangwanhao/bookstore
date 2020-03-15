package cn.yangwanhao.bookstore;

import cn.yangwanhao.bookstore.common.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "cn.yangwanhao.bookstore.mapper")
@EnableScheduling
public class BookstoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }

}
