package cn.yangwanhao.bookstore.common.properties;


import cn.yangwanhao.bookstore.common.constant.GlobalConstant;

/**
 * 验证码公共配置
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/17 14:42
 */

public class GlobalCaptchaProperties {

    /**
     * 生成验证码的源
     */
    public static final char [] GENERATE_CAPTCHA_SOURCE = GlobalConstant.NUMBER_SOURCE;

    /**
     * 验证码位数
     */
    public static final int CAPTCHA_SIZE = 4;

    /**
     * 验证码有效次数
     */
    public static final int CAPTCHA_VALID_CHANCES = 5;

    /**
     * 验证码有效时间(单位：分钟)
     */
    public static final int CAPTCHA_VALID_TIME = 5;

    /**
     * Redis中验证码前缀
     */
    public static final String CAPTCHA_PREFIX = GlobalConstant.RedisPrefixKey.CAPTCHA_PREFIX;

    /**
     * 存放验证码uuid key 值的cookie名字
     */
    public static final String CAPTCHA_COOKIE_NAME = "captcha";

}
