package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

/**
 * @author 杨万浩
 * @description TradeRecordTypeEnum枚举类
 * @date 2020/3/13 17
 */
public enum TradeRecordTypeEnum {

    /**
     * 支付
     */
    PAY(1, "支付"),
    /**
     * 退款
     */
    RETURN(0, "退款")
    ;

    @Getter
    private Integer type;
    @Getter
    private String desc;

    TradeRecordTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
