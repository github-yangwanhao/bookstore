package cn.yangwanhao.bookstore.common.annotation;

import java.lang.annotation.*;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/10 19:34
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WriteLog {

    // TODO 记录日志

    boolean doLog();

}
