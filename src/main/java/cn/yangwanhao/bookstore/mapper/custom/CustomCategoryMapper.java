package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.entity.Category;
import cn.yangwanhao.bookstore.vo.CategoryListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/27 18:13
 */

@Mapper
@Repository
public interface CustomCategoryMapper {

    /**
     * Description: 获取最后一条记录
     * @return category
     * @author 杨万浩
     * @createDate 2019/11/27 18:15
     */
    Category selectLastOneCategory();

    /**
     * Description: 通过id集合删除几条数据
     * @param set idsSet
     * @return
     * @author 杨万浩
     * @createDate 2019/11/27 20:18
     */
    Integer deleteCategoryByIds(@Param("set") Set<Integer> set);

    /**
     * Description: 根据pid查找
     * @param pid pid
     * @return
     * @author 杨万浩
     * @date 2020/3/6 9:58
     */
    List<CategoryListVo> listCategories(Integer pid);

}
