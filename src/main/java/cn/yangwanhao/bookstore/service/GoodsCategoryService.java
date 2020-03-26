package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.entity.Category;
import cn.yangwanhao.bookstore.vo.CategoryListVo;
import cn.yangwanhao.bookstore.vo.CategoryMenuTreeVo;
import cn.yangwanhao.bookstore.vo.CategoryTreeVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/27 18:06
 */

public interface GoodsCategoryService {

    /**
     * Description: 添加一个分类
     * @param name 分类名字
     * @param parentId 父节点id
     * @return 1成功0失败
     * @author 杨万浩
     * @createDate 2019/11/27 18:04
     */
    Integer addCategory(String name, Integer parentId);

    /**
     * Description: 删除一个分类
     * @param categoryId 分类id
     * @return 1成功0失败
     * @author 杨万浩
     * @createDate 2019/11/27 19:21
     */
    Integer removeCategory(Integer categoryId);

    /**
     * Description: 删除多个分类
     * @param categoryIds ids
     * @return
     * @author 杨万浩
     * @date 2020/3/6 15:25
     */
    Integer removeCategories(Integer[] categoryIds);

    /**
     * Description: 修改一个分类
     * @param id 分类id
     * @param name 分类描述
     * @return 1成功0失败
     * @author 杨万浩
     * @createDate 2019/11/28 9:43
     */
    Integer modifyCategory(Integer id, String name);

    /**
     * Description: 查看分类的树状节点
     * @param pid pid
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return 树状节点json
     * @author 杨万浩
     * @createDate 2019/11/28 16:40
     */
    PageInfo<CategoryListVo> listCategory(Integer pid, Integer pageNum, Integer pageSize);

    /**
     * Description: 通过id拿到数据
     * @param id id
     * @return
     * @author 杨万浩
     * @date 2020/3/6 15:49
     */
    Category getById(Integer id);

    /**
     * Description: 树状结构
     * @return
     * @author 杨万浩
     * @date 2020/3/6 16:38
     */
    List<CategoryTreeVo> listCategoryTree() throws IOException;

    /**
     * Description: 首页分类菜单
     * @return
     * @author 杨万浩
     * @date 2020/3/26 9:01
     */
    List<CategoryMenuTreeVo> listCategoryMenuTree() throws IOException;

}
