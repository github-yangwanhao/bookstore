package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.beans.UserLoginLog;
import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.LoginTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.HttpUtils;
import cn.yangwanhao.bookstore.dto.PortalUserLoginDto;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 杨万浩
 * @description PortalLoginController类
 * @date 2020/3/9 09
 */
@Controller
@RequestMapping("/store/login")
@Slf4j
public class PortalLoginController {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage<Boolean> login(@RequestBody PortalUserLoginDto dto, HttpServletRequest request) {
        LoginUserVo vo = userService.getPasswordByLoginNameAndLoginType(dto.getUsername(), LoginTypeEnum.CUSTOMER.getCode());
        if (vo == null) {
            throw new GlobalException(ErrorCodeEnum.U5009001);
        }
        if (vo.getIsLocked() == GlobalConstant.YES) {
            throw new GlobalException(ErrorCodeEnum.U5009011);
        }
        // 密码错误
        if (!passwordEncoder.matches(dto.getPassword(), vo.getPassword())) {
            // 密码错误次数+1
            userService.addPwdErrorCount(vo.getId());
            log.error(dto.getUsername() + " " + "密码错误");
            // 已经错了四次(这是第五次错误),锁定账号
            if (vo.getPwdErrorCount() == 4) {
                userService.lockAccount(vo.getId());
            }
            throw new GlobalException(500, "用户名或密码错误,您今天还有" + (4-vo.getPwdErrorCount()) + "次机会");
        }
        // 登陆成功
        String ipAddr = HttpUtils.getIpAddr(request);
        userService.loginSuccess(vo.getId(), ipAddr);
        // 设置用户信息session
        request.getSession().setAttribute(GlobalConstant.PORTAL_LOGIN_SESSION_KEY, vo);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60);
        UserLoginLog log = new UserLoginLog(null, vo.getId(), vo.getLoginname(), ipAddr, new Date());
        mongoTemplate.insert(log);
        return ResponseMessage.success(true);
    }

    @RequestMapping("/success")
    public String success(String uri) {
        return "redirect:" + uri;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(GlobalConstant.PORTAL_LOGIN_SESSION_KEY);
        return "redirect:/";
    }

}
