package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/30 11:09
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigDecimalUtils {

    /**
     * Description: 加法计算
     * @param var1 被加数
     * @param var2 加数
     * @return 和
     * @author 杨万浩
     * @createDate 2019/11/30 11:41
     */
    public static BigDecimal add(String var1, String var2) {
        if (!ValidateUtils.isNumber(var1) || !ValidateUtils.isNumber(var2)) {
            throw new GlobalException(ErrorCodeEnum.U5002003);
        }
        BigDecimal num1 = new BigDecimal(var1);
        BigDecimal num2 = new BigDecimal(var2);
        return num1.add(num2);
    }

    /**
     * Description: 减法运算
     * @param var1 被减数
     * @param var2 减数
     * @return 差
     * @author 杨万浩
     * @createDate 2019/11/30 11:47
     */
    public static BigDecimal sub(String var1, String var2) {
        if (!ValidateUtils.isNumber(var1) || !ValidateUtils.isNumber(var2)) {
            throw new GlobalException(ErrorCodeEnum.U5002003);
        }
        BigDecimal num1 = new BigDecimal(var1);
        BigDecimal num2 = new BigDecimal(var2);
        return num1.subtract(num2);
    }

    /**
     * Description: 乘法运算
     * @param var1 因数
     * @param var2 因数
     * @return 积
     * @author 杨万浩
     * @createDate 2019/11/30 11:49
     */
    public static BigDecimal mul(String var1, String var2) {
        if (!ValidateUtils.isNumber(var1) || !ValidateUtils.isNumber(var2)) {
            throw new GlobalException(ErrorCodeEnum.U5002003);
        }
        BigDecimal num1 = new BigDecimal(var1);
        BigDecimal num2 = new BigDecimal(var2);
        return num1.multiply(num2);
    }

    /**
     * Description: 除法运算
     * @param var1 被除数
     * @param var2 除数
     * @param bit 小数点保留几位(取整可传0或null)
     * @return 商
     * @author 杨万浩
     * @createDate 2019/11/30 11:52
     */
    public static BigDecimal div(String var1, String var2, Integer bit) {
        if (!ValidateUtils.isNumber(var1) || !ValidateUtils.isNumber(var2)) {
            throw new GlobalException(ErrorCodeEnum.U5002003);
        }
        if (bit == null) {
            bit = 0;
        }
        BigDecimal num1 = new BigDecimal(var1);
        BigDecimal num2 = new BigDecimal(var2);
        return num1.divide(num2, bit, RoundingMode.HALF_UP);
    }

    /**
     * Description: 将小数点左移n位
     * @param num 数字
     * @param n 小数点左移几位
     * @return num * 10^n
     * @author 青鲤
     * @createDate 2019/12/16 14:55
     */
    public static BigDecimal movePointLeft(String num, int n) {
        return new BigDecimal(num).movePointLeft(n);
    }

    /**
     * Description: 将小数点右移n位
     * @param num 数字
     * @param n 小数点右移几位
     * @return num * 10^-n
     * @author 青鲤
     * @createDate 2019/12/16 14:55
     */
    public static BigDecimal movePointRight(String num, int n) {
        return new BigDecimal(num).movePointRight(n);
    }

}
