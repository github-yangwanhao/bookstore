package cn.yangwanhao.bookstore.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 杨万浩
 * @description PicNameUtils类
 * @date 2020/3/7 17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PicNameUtils {

    public static String getThumbnailName(String originalName) {

        // 原图名称 aaa.jpg
        // 缩略图名称 aaa_80x90.jpg
        String addStr = "_80x80";
        String prefix = StringUtils.substringBeforeLast(originalName, ".");
        if (prefix.endsWith(addStr)) {
            return originalName;
        }
        String extension = StringUtils.substringAfterLast(originalName, ".");
        StringBuffer sb = new StringBuffer();
        sb.append(prefix)
                .append(addStr)
                .append(".")
                .append(extension)
        ;
        return sb.toString();
    }

    public static String getOriginalName(String thumbnailName) {
        // 缩略图名称 aaa_80x90.jpg
        // 原图名称 aaa.jpg
        String addStr = "_80x80";
        // aaa_80x90
        String prefix = StringUtils.substringBeforeLast(thumbnailName, ".");
        if (!prefix.endsWith(addStr)) {
            return thumbnailName;
        }
        // jpg
        String extension = StringUtils.substringAfterLast(thumbnailName, ".");
        prefix = prefix.substring(0, prefix.length()-addStr.length());
        StringBuffer sb = new StringBuffer();
        return sb.append(prefix).append(".").append(extension).toString();
    }


}
