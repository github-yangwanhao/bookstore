package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.service.GoodsCategoryService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.CategoryTreeVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import cn.yangwanhao.bookstore.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author 杨万浩
 * @description PageController类
 * @date 2020/3/3 12
 */
@Controller
@RequestMapping("/admin/page")
public class AdminPageController extends BaseController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

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

    @RequestMapping("/goods")
    public String toGoods(Model model) {
        model.addAttribute("path", "newbee_mall_goods");
        return "admin/goods";
    }

    @RequestMapping("/goods-edit")
    public String toGoodsEdit(Model model) throws IOException {
        List<CategoryTreeVo> list = goodsCategoryService.listCategoryTree();
        model.addAttribute("path", "newbee_mall_goods");
        model.addAttribute("list", list);
        return "admin/goods_edit";
    }

    @RequestMapping("/goods-edit/{id}")
    public String toGoodsEdit(@PathVariable("id") Long id, Model model) throws IOException {
        List<CategoryTreeVo> list = goodsCategoryService.listCategoryTree();
        GoodsVo vo = goodsService.getGoodsInfo(id);
        model.addAttribute("path", "newbee_mall_goods");
        model.addAttribute("list", list);
        model.addAttribute("vo", vo);
        return "admin/goods_edit";
    }

    @RequestMapping("/orderList")
    public String orderList(Model model) {
        model.addAttribute("path", "orders");
        return "admin/order";
    }

    @RequestMapping("/profile")
    public String toProfile(HttpServletRequest request, Model model) throws ParseException {
        UserInfoVo vo = userService.getUserInfoById(super.getLoginUserId(request));
        model.addAttribute("path", "profile");
        model.addAttribute("vo", vo);
        return  "admin/profile";
    }

}
