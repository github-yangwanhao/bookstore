package cn.yangwanhao.bookstore.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/23 15:17
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class HttpUtils {

    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
    private static final String UNKNOWN = "unknown";
    private static final String COMMA = ",";
    private static final int MAX_IP_LENGTH = 15;

    /**
     * Description: 从请求中获取ip
     * @param request request
     * @return ip Address
     * @author 杨万浩
     * @CreateDate 2019/10/23 15:22
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader(X_FORWARDED_FOR);
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IP.equals(ipAddress) || LOCALHOST_IP_16.equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取IP地址, 出现异常={}", e.getMessage(), e);
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > MAX_IP_LENGTH && ipAddress.indexOf(COMMA) > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
        }
        return ipAddress;
    }

    public static Boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("accept").contains("application/json")
                || (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

}
