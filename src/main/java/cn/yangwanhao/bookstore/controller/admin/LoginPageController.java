package cn.yangwanhao.bookstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description LoginPageController类
 *
 * @author 杨万浩
 * @date 2020/4/11 18
 */
@Controller
public class LoginPageController {

    @RequestMapping({"/admin", "/admin/login","/admin/page/login"})
    public String toAdminLogin() {
        return "admin/login";
    }

}
