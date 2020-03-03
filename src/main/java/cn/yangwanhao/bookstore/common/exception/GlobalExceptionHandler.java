package cn.yangwanhao.bookstore.common.exception;

import cn.yangwanhao.bookstore.common.exception.GlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杨万浩
 * @description ExceptionHandler类
 * @date 2020/3/2 19
 */

/*@ControllerAdvice
public class GlobalExceptionHandler {

    *//**
     * Description: 全局异常处理
     * @param exception exception
     * @return ModelAndView 500页面
     * @author 杨万浩
     * @date 2020/3/2 19:50
     *//*
    @ExceptionHandler(GlobalException.class)
    public ModelAndView customGenericExceptionHandler(GlobalException exception){
        exception.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("/error/error_5xx");
        modelAndView.addObject("code",exception.getCode());
        modelAndView.addObject("message",exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView allExceptionHandler(Exception exception){
        exception.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("/error/error_5xx");
        modelAndView.addObject("code", 500);
        modelAndView.addObject("message", "网络出错了,请稍后再试");
        return modelAndView;
    }

}*/
