package cn.yangwanhao.bookstore.interceptor;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.util.HttpUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 杨万浩
 * @description PortalLoginInterceptor类
 * @date 2020/3/9 08
 */
@Component
public class PortalLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (null == request.getSession().getAttribute(GlobalConstant.PORTAL_LOGIN_SESSION_KEY)) {
            if (HttpUtils.isAjaxRequest(request)) {
                String queryString = request.getQueryString();
                response.setHeader("SESSIONSTATUS", "TIMEOUT");
                response.setHeader("CONTEXTPATH", "/store/page/login?history=" + request.getRequestURI()+"?" + queryString);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                /*PrintWriter writer = response.getWriter();
                writer.print(request.getRequestURI());
                writer.flush();
                writer.close();*/
            } else {
                response.sendRedirect(request.getContextPath() + "/store/page/login?history=" + request.getRequestURI());
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
