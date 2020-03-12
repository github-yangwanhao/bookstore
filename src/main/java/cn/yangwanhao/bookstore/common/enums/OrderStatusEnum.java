package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/24 17:24
 */

public enum  OrderStatusEnum {
    /**
     * 待付款
     */
    WAIT_TO_PAY(1, "待付款"),

    /**
     * 已取消(超时未付款)
     */
    CANCELED_PAY_EXPIRED(2, "已取消(超时未付款)"),

    /**
     * 已取消(支付前取消)
     */
    CANCELED_BEFORE_PAID(3, "已取消(支付前取消)"),

    /**
     * 已取消(支付后取消)
     */
    CANCELED_AFTER_PAID(4, "已取消(支付后取消)"),

    /**
     * 待发货
     */
    WAIT_TO_SHIPMENTS(5, "待发货"),

    /**
     * 已发货
     */
    ALREADY_SHIPMENTS(6, "已发货"),

    /**
     * 已完成
     */
    FINISHED(7, "已完成")
    ;

    @Getter
    private Integer status;

    @Getter
    private String desc;

    public static OrderStatusEnum getByStatus(Integer status) {
        for (OrderStatusEnum e : values()) {
            if (e.getStatus().equals(status)) {
                return e;
            }
        }
        return null;
    }

    OrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
