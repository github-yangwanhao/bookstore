package cn.yangwanhao.bookstore.common.annotation.validator;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/10 19:38
 */

@Component
@Aspect
public class WriteLogAop {

    // TODO 记录日志

    @Pointcut("@annotation(cn.yangwanhao.bookstore.common.annotation.WriteLog)")
    public void doLog() {
    }

}
