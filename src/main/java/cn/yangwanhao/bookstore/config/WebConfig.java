package cn.yangwanhao.bookstore.config;

import cn.yangwanhao.bookstore.interceptor.AdminLoginInterceptor;
import cn.yangwanhao.bookstore.interceptor.PortalLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
                .excludePathPatterns("/store/page/register")
                .excludePathPatterns("/store/goods/search")
                .excludePathPatterns("/store/login/login")
                .excludePathPatterns("/store/user/register")
                .excludePathPatterns("/")
                .excludePathPatterns("/index")
                .excludePathPatterns("/index.html")
        ;
    }

    /**
     * 重新跨域支持方法
     * CorsRegistry  开启跨域注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //addMapping 添加可跨域的请求地址
        registry.addMapping("/**")
                //设置跨域 域名权限 规定由某一个指定的域名+端口能访问跨域项目
                .allowedOrigins("*")
                //是否开启cookie跨域
                .allowCredentials(false)
                //规定能够跨域访问的方法类型
                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                //添加验证头信息  token
                //.allowedHeaders()
                //预检请求存活时间 在此期间不再次发送预检请求
                .maxAge(3600);
    }

}
