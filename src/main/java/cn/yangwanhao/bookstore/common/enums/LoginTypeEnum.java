package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

/**
 * 登陆类型枚举类
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/8 19:07
 */

public enum LoginTypeEnum {

    /**
     * 顾客
     */
    CUSTOMER(0, "顾客"),

    /**
     * 后台管理员工
     */
    MANAGER(1, "后台管理员工")
    ;

    @Getter
    private Integer code;

    @Getter
    private String value;

    LoginTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
