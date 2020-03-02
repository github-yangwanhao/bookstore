package cn.yangwanhao.bookstore.common.enums;

/**
 * 全局"是否"枚举类 1是0否
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/17 14:04
 */

public enum BooleanEnum {

    /**
     * 1 是
     */
    TRUE(1, "是"),
    /**
     * 0 否
     */
    FALSE(0, "否");

    private final int key;
    private final String value;

    BooleanEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}