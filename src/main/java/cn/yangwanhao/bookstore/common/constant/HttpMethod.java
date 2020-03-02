package cn.yangwanhao.bookstore.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/23 11:14
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpMethod {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
}
