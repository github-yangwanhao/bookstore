package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.vo.GoodsCategoryTreeVo;

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
     * @return 树状节点json
     * @author 杨万浩
     * @createDate 2019/11/28 16:40
     */
    List<GoodsCategoryTreeVo> listCategoryTree(Integer pid);

}
