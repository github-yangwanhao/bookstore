package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.service.GoodsCategoryService;
import cn.yangwanhao.bookstore.vo.CategoryMenuTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * @author 杨万浩
 * @description IndexController类
 * @date 2020/3/8 15
 */
@Controller
public class PortalIndexController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping({"/", "/index", "/index.html"})
    public String toIndex(Model model) throws IOException {
        List<CategoryMenuTreeVo> list = goodsCategoryService.listCategoryMenuTree();
        model.addAttribute("list", list);
        return "mall/index";
    }

}
