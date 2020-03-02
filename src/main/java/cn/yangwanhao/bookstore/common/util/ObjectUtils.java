package cn.yangwanhao.bookstore.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 杨万浩
 * @description ObjectUtils类
 * @date 2020/2/24 14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }

}
