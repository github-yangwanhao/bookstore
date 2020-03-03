package cn.yangwanhao.bookstore.controller.manage;

import cn.yangwanhao.bookstore.common.support.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杨万浩
 * @description PageController类
 * @date 2020/3/3 12
 */
@Controller
@RequestMapping("/admin/page")
public class AdminPageController extends BaseController {

    @RequestMapping("/login")
    public String toAdminLogin() {
        return "/admin/login";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "/admin/index";
    }

}
