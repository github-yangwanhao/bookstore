package cn.yangwanhao.bookstore.common.enums;

/**
 *
 * 性别枚举类：0女1男
 *
 * @author 杨万浩
 * @date 2019/10/14 09:52
 * @version 1.0.0
 */

public enum GenderEnum {
    /**
     * 男
     */
    MAN(1, "男"),
    /**
     * 女
     */
    WOMAN(0, "女");

    private final Integer key;
    private final String value;

    private GenderEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
