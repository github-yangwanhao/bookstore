package cn.yangwanhao.bookstore.common.support;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseController
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/14 14:21
 */

@Slf4j
public class BaseController {

    protected final Logger logger = log;

    protected LoginUserVo getLoginUser(HttpServletRequest request) {
        logger.info("获取登录用户信息");
        LoginUserVo vo = (LoginUserVo) request.getSession().getAttribute(GlobalConstant.ADMIN_LOGIN_SESSION_KEY);
        if (vo == null) {
            vo = (LoginUserVo) request.getSession().getAttribute("portalLoginUser");
        }
        return vo;
    }

    protected Long getLoginUserId(HttpServletRequest request) {
        logger.info("获取登录用户的id");
        return getLoginUser(request).getId();
    }

}
