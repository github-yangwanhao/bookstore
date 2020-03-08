package cn.yangwanhao.bookstore.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 杨万浩
 * @description IndexController类
 * @date 2020/3/8 15
 */
@Controller
public class PortalIndexController {

    @RequestMapping({"/", "/index", "/index.html"})
    public String toIndex() {
        return "mall/index";
    }

}
