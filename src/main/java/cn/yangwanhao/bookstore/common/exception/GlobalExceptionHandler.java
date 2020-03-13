package cn.yangwanhao.bookstore.common.exception;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨万浩
 * @description ExceptionHandler类
 * @date 2020/3/2 19
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * Description: 全局异常处理
     * @param exception exception
     * @return ModelAndView 500页面
     * @author 杨万浩
     * @date 2020/3/2 19:50
     */
    @ExceptionHandler(GlobalException.class)
    public Object customGenericExceptionHandler(GlobalException exception,  HttpServletRequest request) throws JsonProcessingException {
        exception.printStackTrace();
        if (HttpUtils.isAjaxRequest(request)) {
            ResponseMessage<Object> message = ResponseMessage.error(exception);
            System.err.println(message);
            return objectMapper.writeValueAsString(message);
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/error_5xx");
            modelAndView.addObject("message", exception.getMessage());
            return modelAndView;
        }
    }

    @ExceptionHandler(Exception.class)
    public Object allExceptionHandler(Exception exception, HttpServletRequest request) throws JsonProcessingException {
        exception.printStackTrace();
        if (HttpUtils.isAjaxRequest(request)) {
            ResponseMessage<Object> message = ResponseMessage.error(exception);
            System.err.println(message);
            return objectMapper.writeValueAsString(message);
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/error_5xx");
            modelAndView.addObject("message", exception.getMessage());
            return modelAndView;
        }
    }

}
