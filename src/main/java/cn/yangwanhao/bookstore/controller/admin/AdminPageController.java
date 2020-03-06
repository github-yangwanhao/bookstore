package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.support.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "admin/login";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "admin/index";
    }

    @RequestMapping("/category")
    public String toCategory(@RequestParam("parentId") Long parentId, Model model) {
        model.addAttribute("path", "category");
        model.addAttribute("parentId", parentId);
        return "admin/category";
    }

}
