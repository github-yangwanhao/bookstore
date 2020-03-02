package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.constant.ValidatePattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/30 11:13
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile(ValidatePattern.NUMBER_REGEX);

    private static final Pattern PHONE_PATTERN = Pattern.compile(ValidatePattern.MOBILE_PHONE);

    private static final Pattern MAIL_PATTERN = Pattern.compile(ValidatePattern.EMAIL);


    /**
     * Description: 字符串是否是数字
     * @param numStr 要判断的字符串
     * @return 是true/否false
     * @author 杨万浩
     * @createDate 2019/11/30 11:28
     */
    public static Boolean isNumber(String numStr) {
        return NUMBER_PATTERN.matcher(numStr).matches();
    }

    /**
     * Description: 手机号格式是否正确（先支持13,15,16,17,18,19开头的手机号码）
     * @param phoneStr 手机号
     * @return 手机号格式是否正确 true正确 false不正确
     * @author 杨万浩
     * @createDate 2019/10/17 11:44
     */
    public static Boolean isMobileNumber(String phoneStr) {
        return StringUtils.isBlank(phoneStr) && PHONE_PATTERN.matcher(phoneStr).matches();
    }

    /**
     * Description: 邮箱格式是否正确
     * @param mailStr 输入的邮箱
     * @return true正确 false不正确
     * @author 杨万浩
     * @createDate 2019/10/17 11:45
     */
    public static boolean isEmail(String mailStr) {
        return StringUtils.isBlank(mailStr) && MAIL_PATTERN.matcher(mailStr).matches();
    }

}
