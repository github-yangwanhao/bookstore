package cn.yangwanhao.bookstore.handler;

import cn.yangwanhao.bookstore.common.exception.GlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杨万浩
 * @description ExceptionHandler类
 * @date 2020/3/2 19
 */

@ControllerAdvice
public class ExceptionHandler {

    @ExceptionHandler(GlobalException.class)//可以直接写@ExceptionHandler,不指明异常类，会自动映射
    public ModelAndView customGenericExceptionHnadler(GlobalException exception){ //还可以声明接收其他任意参数
        ModelAndView modelAndView = new ModelAndView("generic_error");
        modelAndView.addObject("errCode",exception.getCode();
        modelAndView.addObject("errMsg",exception.getMessage());
        return modelAndView;
    }

}
