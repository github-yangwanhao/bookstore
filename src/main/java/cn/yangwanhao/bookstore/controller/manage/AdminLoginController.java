package cn.yangwanhao.bookstore.controller.manage;

import cn.yangwanhao.bookstore.common.beans.Captcha;
import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.LoginTypeEnum;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.HttpUtils;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 杨万浩
 * @description LoginController类
 * @date 2020/3/3 13
 */
@Controller
@RequestMapping("/admin/login")
public class AdminLoginController extends BaseController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping("/login")
    public String checkLogin(HttpServletRequest request,String loginName, String password, String captcha) {
        if (!checkCaptcha(request, captcha)) {
            request.getSession().setAttribute("errorMsg", "验证码错误或已过期");
            logger.error(loginName + " " + "验证码错误或已过期");
            return "redirect:/admin/page/login";
        }
        LoginUserVo vo = userService.getPasswordByLoginNameAndLoginType(loginName, LoginTypeEnum.MANAGER.getCode());
        // 用户不存在
        if (vo == null) {
            request.getSession().setAttribute("errorMsg", "用户名或密码错误");
            logger.error(loginName + " " + "用户名不存在");
            return "redirect:/admin/page/login";
        }
        // 账户被锁定
        if (vo.getIsLocked() == GlobalConstant.YES) {
            request.getSession().setAttribute("errorMsg", "账户被锁定,请明天再来或联系管理员");
            logger.error(loginName + " " + "账户被锁定,请明天再来或联系管理员");
            return "redirect:/admin/page/login";
        }
        // 密码错误
        if (!passwordEncoder.matches(password, vo.getPassword())) {
            // 密码错误次数+1
            userService.addPwdErrorCount(vo.getId());
            request.getSession().setAttribute("errorMsg", "用户名或密码错误,您今天还有" + (4-vo.getPwdErrorCount()) + "次机会");
            logger.error(loginName + " " + "密码错误");
            // 已经错了四次(这是第五次错误),锁定账号
            if (vo.getPwdErrorCount() == 4) {
                userService.lockAccount(vo.getId());
            }
            return "redirect:/admin/page/login";
        }
        // 登陆成功
        // 清零密码错误次数
        userService.resetPwdErrorCount(vo.getId());
        userService.updateLastLoginIp(vo.getId(), HttpUtils.getIpAddr(request));
        // 设置用户信息session
        request.getSession().setAttribute("loginUser", vo);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10 * 60);
        // 删除错误信息session
        request.getSession().removeAttribute("errorMsg");
        return loginSuccess(request, vo);
    }


    @RequestMapping("/success")
    public String loginSuccess(HttpServletRequest request, LoginUserVo vo) {
        // 登陆成功后删除验证码
        String key = null;
        for (Cookie cookie : request.getCookies()) {
            if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                key = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
            }
        }
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.delete(key);
        }
        logger.info(vo.getLoginname() + " " + "登陆成功");
        return "redirect:/admin/page/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUser");
        return "redirect:/admin/page/login";
    }

    private Boolean checkCaptcha(HttpServletRequest request, String requestCaptcha) {
        String key = "";
        // 取出cookie 以及 存放在cookie中的key
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                key = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
                break;
            }
        }
        // 如果key是null,说明验证码已经过期
        if (StringUtils.isBlank(key)) {
            return false;
        }
        // 拿到redis中的验证码
        Captcha captcha = null;
        try {
            String json = stringRedisTemplate.opsForValue().get(key);
            // 如果从redis中拿到的captcha是null,说明验证码已经过期
            if (StringUtils.isBlank(json)) {
                return false;
            }
            captcha = objectMapper.readValue(json, Captcha.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 如果输入的验证码不为空但是位数不对,机会-1
        if (requestCaptcha.length() != GlobalConstant.Captcha.CAPTCHA_SIZE) {
            chanceDecrement(key, captcha);
            return false;
        }
        // 如果输入的验证码和redis中拿到的验证码不相同
        if (!requestCaptcha.equals(captcha.getCode())) {
            // 机会-1
            chanceDecrement(key, captcha);
            return false;
        }
        // 验证码正确,机会也要-1
        chanceDecrement(key, captcha);
        return true;
    }

    private void chanceDecrement(String key, Captcha captcha) {
        // 机会-1
        captcha.setChances(captcha.getChances()-1);
        // 如果-1后的机会等于0
        if (captcha.getChances() <= 0) {
            // 删除redis中的验证码
            stringRedisTemplate.delete(key);
        }
        // 将redis中的验证码机会-1
        try {
            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(captcha), 0);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
