package cn.yangwanhao.bookstore.common.exception;

import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;


/**
 * 通用全局异常
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/17 13:47
 */

@EqualsAndHashCode(callSuper = false)
@Slf4j
@Data
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 5495940819633398938L;

    protected Integer code;

    protected String message;

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        log.error("<== "+this.getClass().getName()+", code:" + this.code + ", message:" + this.getMessage());
    }

    public GlobalException(ErrorCodeEnum e) {
        super(e.getMsg());
        this.code = e.getCode();
        this.message = e.getMsg();
        log.error("<== "+this.getClass().getName()+", code:" + this.code + ", message:" + this.getMessage());
    }
}
