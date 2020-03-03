package cn.yangwanhao.bookstore;

import cn.yangwanhao.bookstore.common.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "cn.yangwanhao.bookstore.mapper")
public class BookstoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }

}
