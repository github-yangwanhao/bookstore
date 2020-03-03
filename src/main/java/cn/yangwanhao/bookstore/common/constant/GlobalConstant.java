package cn.yangwanhao.bookstore.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *
 * 系统常量类
 *
 * @author 杨万浩
 * @date 2019/10/14 10:22
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalConstant {

    /**
     * 是
     */
    public static final int YES = 1;
    /**
     * 否
     */
    public static final int NO = 0;

    /**
     * 标准随机码源，由 0-9 && a-z 组成
     */
    public static final char [] STANDARD_SOURCE = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    /**
     * 数字随机码源，由 0-9 组成
     */
    public static final char [] NUMBER_SOURCE = {'0','1','2','3','4','5','6','7','8','9'};

    /**
     * 字母随机码源，由 a-z 组成
     */
    public static final char [] LETTER_SOURCE = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static final String INIT_LOGIN_IP = "0.0.0.0";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class RedisPrefixKey {

        // redis中验证码key值前缀
        public static final String CAPTCHA_PREFIX = "captcha:";
        // redis中字典表缓存key值前缀
        public static final String DICTIONARY_PREFIX = "dictionary:";
    }

    public static final class Captcha {
        /**
         * 验证码图片宽度
         */
        public static final int WIDTH = 160;

        /**
         * 验证码图片高度
         */
        public static final int HEIGHT = 40;

        /**
         * 验证码干扰线数
         */
        public static final int LINE_COUNT = 20;

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
        public static final String CAPTCHA_PREFIX = "captcha:";

        /**
         * 存放验证码uuid key 值的cookie名字
         */
        public static final String CAPTCHA_COOKIE_NAME = "captcha";

        /**
         * 生成验证码的源
         */
        public static final char [] CAPTCHA_SOURCE = NUMBER_SOURCE;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Number {
        public static  final int THOUSAND_INT = 1000;
        public static  final int HUNDRED_INT = 100;
        public static  final int ZERO_INT = 0;
        public static  final int ONE_INT = 1;
        public static  final int TWO_INT = 2;
        public static  final int THREE_INT = 3;
        public static  final int FOUR_INT = 4;
        public static  final int FIVE_INT = 5;
        public static  final int SIX_INT = 6;
        public static  final int SEVEN_INT = 7;
        public static  final int EIGHT_INT = 8;
        public static  final int NINE_INT = 9;
        public static  final int TEN_INT = 10;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Symbol{
        public static final String COMMA = ",";
        public static final String SPOT = ".";
        public final static String UNDER_LINE = "_";
        public final static String PER_CENT = "%";
        public final static String SHORT_LINE = "-";
        public final static String SPACE = " ";
        public static final String SLASH = "/";
        public static final String COLON = ":";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class GenderName {
        public static final String GENDER_MAN = "先生";
        public static final String GENDER_WOMAN = "女士";
    }

}
