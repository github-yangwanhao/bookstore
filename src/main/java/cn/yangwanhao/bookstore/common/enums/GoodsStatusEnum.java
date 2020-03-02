package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 16:26
 */

public enum GoodsStatusEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),

    /**
     * 已下架
     */
    OFF_THE_SHELF(0, "已下架"),

    /**
     * 已删除
     */
    DELETED(2, "已删除")
    ;

    GoodsStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Getter
    private Integer value;

    @Getter
    private String desc;

}
