package cn.yangwanhao.bookstore.controller.portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杨万浩
 * @description PortalPageController类
 * @date 2020/3/8 15
 */
@Controller
@RequestMapping("/store/page")
@Slf4j
public class PortalPageController {

    @RequestMapping("/login")
    public ModelAndView toLogin(@RequestParam(value = "history", required = false) String history) {
        if (StringUtils.isBlank(history) || "/store/page/login".equals(history)) {
            history = "/index";
        }
        ModelAndView modelAndView = new ModelAndView("mall/login");
        modelAndView.addObject("history", history);
        log.info("history: " + history);
        return modelAndView;
    }

    @RequestMapping("/register")
    public String toRegister() {
        return "/mall/register";
    }

}
