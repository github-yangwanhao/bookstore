package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GenderEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/19 10:21
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PubUtils {

    /**
     * Description: 获取脱敏姓名(姓氏 + 性别)
     * @param realname 真名
     * @param gender 性别
     * @return 脱敏姓名
     * @author 青鲤
     * @createDate 2019/12/19 10:26
     */
    public static String getMaskName(String realname, int gender) {
        return realname.substring(0,1) + (gender == GenderEnum.MAN.getKey() ? GlobalConstant.GenderName.GENDER_MAN :GlobalConstant.GenderName.GENDER_WOMAN);
    }

    /**
     * Description: 获取脱敏手机号(前3 + **** + 后4)
     * @param phone 手机号
     * @return 脱敏手机号
     * @author 青鲤
     * @createDate 2019/12/19 10:36
     */
    public static String getMaskPhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static Integer[] convertStringToInteger(String[] strs) {
        Integer[] result = new Integer[strs.length];
        for (int i=0; i<strs.length; i++) {
            if (!ValidateUtils.isNumber(strs[i])) {
                throw new GlobalException(ErrorCodeEnum.U5002003);
            }
            result[i] = Integer.parseInt(strs[i]);
        }
        return result;
    }

    public static Long[] convertStringToLong(String[] strs) {
        Long[] result = new Long[strs.length];
        for (int i=0; i<strs.length; i++) {
            if (!ValidateUtils.isNumber(strs[i])) {
                throw new GlobalException(ErrorCodeEnum.U5002003);
            }
            result[i] = Long.parseLong(strs[i]);
        }
        return result;
    }

}
