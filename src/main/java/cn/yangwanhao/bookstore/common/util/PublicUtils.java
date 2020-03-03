package cn.yangwanhao.bookstore.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/29 20:45
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublicUtils {

    /**
     * Description: 判断对象是否不为空
     * @param pObj 对象
     * @return true/false
     * @author 杨万浩
     * @createDate 2019/12/17 20:00
     */
    public static boolean isNotEmpty(Object pObj) {
        // 如果是Null,返回false
        if (pObj == null) {
            return false;
        }
        // 如果是空串,返回false
        if (pObj == "") {
            return false;
        }
        if (pObj instanceof String) {
            // 字符串的长度是否为0
            return ((String) pObj).length() != 0;
        } else if (pObj instanceof Collection) {
            // 集合是否为空
            return !((Collection) pObj).isEmpty();
        } else if (pObj instanceof Map) {
            // Map的长度是否为空
            return ((Map) pObj).size() != 0;
        }
        return true;
    }

    /**
     * Description: 判断对象是否为空
     * @param pObj 要判断的对象
     * @return true/false
     * @author 杨万浩
     * @createDate 2019/12/19 9:46
     */
    public static boolean isEmpty(Object pObj) {
        return !isNotEmpty(pObj);
    }

}
