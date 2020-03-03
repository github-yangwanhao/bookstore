package cn.yangwanhao.bookstore.controller.common;

import cn.yangwanhao.bookstore.common.beans.Captcha;
import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.util.CaptchaUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨万浩
 * @description CaptchaController类
 * @date 2020/3/3 12
 */
@Controller
@RequestMapping("/common/captcha")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/showPic")
    public void showCheckCode(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // 如果有验证码先删除刚刚的验证码
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            String oldKey = null;
            for (Cookie cookie : cookies) {
                if (GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME.equals(cookie.getName())) {
                    oldKey = GlobalConstant.Captcha.CAPTCHA_PREFIX + cookie.getValue();
                }
            }
            if (StringUtils.isNotBlank(oldKey)) {
                stringRedisTemplate.delete(oldKey);
            }
        }
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Controller", "no-cache");
        response.setDateHeader("Expires", 0);
        //创建内存图形
        CaptchaUtil.CaptchaImg captchaImg = CaptchaUtil.getCaptcha();
        String uuid = UUID.randomUUID().toString();
        String key = GlobalConstant.Captcha.CAPTCHA_PREFIX + uuid;
        // 存入redis
        String captchaJson = objectMapper.writeValueAsString(new Captcha(captchaImg.getCode(), GlobalConstant.Captcha.CAPTCHA_VALID_CHANCES));
        stringRedisTemplate.opsForValue().set(key, captchaJson, GlobalConstant.Captcha.CAPTCHA_VALID_TIME, TimeUnit.MINUTES);
        // 新建cookie
        Cookie cookie = new Cookie(GlobalConstant.Captcha.CAPTCHA_COOKIE_NAME, uuid);
        // 设置时长
        cookie.setMaxAge(GlobalConstant.Captcha.CAPTCHA_VALID_TIME * 60);
        cookie.setPath("/");
        // 发送cookie
        response.addCookie(cookie);
        // 响应图片
        try (ServletOutputStream out = response.getOutputStream()) {
            captchaImg.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
