package cn.yangwanhao.bookstore.common.beans;

import cn.yangwanhao.bookstore.common.util.PublicUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/26 15:17
 */

@Data
public class ResponseMessage<E> implements Serializable {

    private static final long serialVersionUID = -1862380104329983969L;

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 500;
    private static final String SUCCESS_MESSAGE = "操作成功";
    private static final String ERROR_MESSAGE = "操作失败";

    private Integer code;

    private String message;

    private E data;

    ResponseMessage() {

    }

    ResponseMessage(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    ResponseMessage(Integer code, String message) {
        this(code, message, null);
    }

    public static <E>ResponseMessage<E> success() {
        return setMessage(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <E>ResponseMessage<E> success(E result) {
        return setMessage(SUCCESS_CODE, SUCCESS_MESSAGE, result);
    }

    public static <E>ResponseMessage<E> error() {
        return setMessage(ERROR_CODE, ERROR_MESSAGE);
    }

    public static <E>ResponseMessage<E> error(Integer code, String message) {
        return setMessage(code, message);
    }

    public static <E>ResponseMessage<E> error(E result) {
        return setMessage(ERROR_CODE, ERROR_MESSAGE, result);
    }

    private static <E> ResponseMessage<E> setMessage(Integer code, String message) {
        return new ResponseMessage<>(code, message);
    }

    private static <E> ResponseMessage<E> setMessage(Integer code, String message, E result) {
        return new ResponseMessage<>(code, message, result);
    }

    public static <E>ResponseMessage<E> handleResult(E result) {
        boolean flag = isFlag(result);
        if (flag) {
            return ResponseMessage.success(result);
        } else {
            return ResponseMessage.error(result);
        }
    }

    private static boolean isFlag(Object result) {
        if (result instanceof Integer) {
            return (Integer) result > 0;
        } else if (result instanceof Boolean) {
            return (Boolean) result;
        } else {
            return PublicUtils.isNotEmpty(result);
        }
    }

}
