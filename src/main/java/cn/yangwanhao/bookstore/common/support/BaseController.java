package cn.yangwanhao.bookstore.common.support;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

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

    protected void getLoginUser() {
        logger.info("获取登录用户信息");
        // TODO
    }

    protected Long getLoginUserId() {
        logger.info("获取登录用户的id");
        // TODO
        return null;
    }

}
