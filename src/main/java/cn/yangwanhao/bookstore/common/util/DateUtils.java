package cn.yangwanhao.bookstore.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/6 11:05
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    /**
     * Description: 得到两个日期中间的天数差
     * @param date1 日期1
     * @param date2 日期2
     * @return 天数差
     * @author 杨万浩
     * @createDate 2019/12/6 11:07
     */
    public static int getDayDiff(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return Math.abs(days);
    }

    /**
     * Description: 计算日期n天后的日期
     * @param date 日期
     * @param num n
     * @return date
     * @author 杨万浩
     * @createDate 2019/12/6 11:12
     */
    public static Date getNextNumDay(Date date, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, num);
        return cal.getTime();
    }

    /**
     * Description: 计算从现在开始的n天后的日期
     * @param num n
     * @return date
     * @author 杨万浩
     * @createDate 2019/12/6 11:11
     */
    public static Date getNextNumDay(int num) {
        return getNextNumDay(new Date(), num);
    }


}
