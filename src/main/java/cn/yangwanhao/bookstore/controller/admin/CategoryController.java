package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.entity.Category;
import cn.yangwanhao.bookstore.service.GoodsCategoryService;
import cn.yangwanhao.bookstore.vo.CategoryListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨万浩
 * @description CategoryController类
 * @date 2020/3/4 14
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * Description:
     * @param
     * @return
     * @author 杨万浩
     * @date 2020/3/5 9:53
     */
    @RequestMapping("/list/categories/{pid}")
    @ResponseBody
    public ResponseMessage<PageInfo<CategoryListVo>> listCategories(@PathVariable("pid") Integer pid, @RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        return ResponseMessage.handleResult(goodsCategoryService.listCategory(pid, pageNum, pageSize));
    }

    @RequestMapping("/save/category")
    @ResponseBody
    public ResponseMessage<Integer> saveCategory(@RequestBody Category category) {
        return ResponseMessage.handleResult(goodsCategoryService.addCategory(category.getName(), category.getParentId()));
    }

    @RequestMapping("/update/category")
    @ResponseBody
    public ResponseMessage<Integer> updateCategory(@RequestBody Category category) {
        return ResponseMessage.handleResult(goodsCategoryService.modifyCategory(category.getId(), category.getName()));
    }

    @RequestMapping("/delete/categories")
    @ResponseBody
    public ResponseMessage<Integer> deleteCategories(@RequestBody Integer[] categoriesIds) {
        return ResponseMessage.handleResult(goodsCategoryService.removeCategories(categoriesIds));
    }

    @RequestMapping("/get/category/{id}")
    @ResponseBody
    public ResponseMessage<Category> getById(@PathVariable("id") Integer id) {
        return ResponseMessage.handleResult(goodsCategoryService.getById(id));
    }

}
