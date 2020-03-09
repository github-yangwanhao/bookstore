package cn.yangwanhao.bookstore.config;

import cn.yangwanhao.bookstore.interceptor.AdminLoginInterceptor;
import cn.yangwanhao.bookstore.interceptor.PortalLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private PortalLoginInterceptor portalLoginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/page/login")
                .excludePathPatterns("/admin/login/login")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**")
        ;

        registry.addInterceptor(portalLoginInterceptor)
                .addPathPatterns("/store/**")
                .excludePathPatterns("/store/page/login")
                .excludePathPatterns("/store/goods/search")
                .excludePathPatterns("/store/login/login")
                .excludePathPatterns("/")
                .excludePathPatterns("/index")
                .excludePathPatterns("/index.html")
        ;
    }
}
