package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.entity.Category;
import cn.yangwanhao.bookstore.entity.CategoryExample;
import cn.yangwanhao.bookstore.mapper.CategoryMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomCategoryMapper;
import cn.yangwanhao.bookstore.service.GoodsCategoryService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.GoodsCategoryTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/27 18:07
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private CustomCategoryMapper customCategoryMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public Integer addCategory(String name, Integer parentId) {
        Category category = new Category();
        category.setName(name);
        category.setParentId(parentId);
        // 是否是父节点:1是0否(新增的节点一定不是父节点)
        category.setIsParent(GlobalConstant.NO);
        // 获取最后一条记录的sort并+1
        category.setSort(customCategoryMapper.selectLastOneCategory().getSort() + 1);
        // 如果有父节点id，看看父节点的isParent是否为1;如果不是，改为1
        if (parentId != 0) {
            Category parentCategory = categoryMapper.selectByPrimaryKey(parentId);
            if (parentCategory.getIsParent() == GlobalConstant.NO) {
                parentCategory.setIsParent(GlobalConstant.YES);
                categoryMapper.updateByPrimaryKeySelective(parentCategory);
            }
        }
        return categoryMapper.insertSelective(category);
    }

    @Override
    public Integer removeCategory(Integer categoryId) {
        // 如果该分类下还有商品,不能删除
        Boolean categoryHasGoods = goodsService.categoryHasGoods(categoryId);
        if (categoryHasGoods) {
            throw new GlobalException(ErrorCodeEnum.I5007002);
        }
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        // 如果该节点有父节点，且父节点只有它一个子节点，那么父节点变为非父节点
        if (category.getParentId() != null) {
            CategoryExample example = new CategoryExample();
            CategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(category.getParentId());
            int count = categoryMapper.countByExample(example);
            if (count <= 1) {
                Category parentCategory = new Category();
                parentCategory.setId(category.getParentId());
                parentCategory.setIsParent(0);
                categoryMapper.updateByPrimaryKeySelective(parentCategory);
            }
        }
        // 如果该节点有子节点，那么递归删除所有子节点以及孙子节点
        if (category.getIsParent() == 1) {
            List<Category> categoryList = categoryMapper.selectByExample(new CategoryExample());
            TreeSet<Integer> ids = (TreeSet<Integer>) getChildId(categoryList, categoryId);
            // 判断子节点是否有商品
            for (Integer id: ids) {
                if (goodsService.categoryHasGoods(id)) {
                    throw new GlobalException(ErrorCodeEnum.I5007002);
                }
            }
            customCategoryMapper.deleteCategoryByIds(ids);
        }
        // 删除这个节点
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public Integer modifyCategory(Integer id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<GoodsCategoryTreeVo> listCategoryTree(Integer pid) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(pid);
        List<Category> parentCategoryList = categoryMapper.selectByExample(example);
        List<GoodsCategoryTreeVo> list = new ArrayList<>();
        for (Category category : parentCategoryList) {
            GoodsCategoryTreeVo vo = new GoodsCategoryTreeVo();
            vo.setId(category.getId());
            vo.setName(category.getName());
            vo.setParentId(category.getParentId());
            vo.setIsParent(category.getIsParent() == GlobalConstant.YES);
            vo.setSort(category.getSort());
            list.add(vo);
        }
        return list;
    }

    /**
     * Description: 通过一个父id递归找出所有的子节点和孙子节点
     * @param categoryList 要遍历的List
     * @param id 最上层的父id
     * @return 所有的子节点和孙子节点
     * @author 杨万浩
     * @createDate 2019/11/27 20:14
     */
    private Set<Integer> getChildId(List<Category> categoryList, Integer id) {
        Set<Integer> set = new TreeSet<>();
        for (Category category : categoryList) {
            if (category.getParentId().equals(id)) {
                set.add(category.getId());
                set.addAll(getChildId(categoryList, category.getId()));
            }
        }
        return set;
    }

    /**
     * Description: 通过一个节点查找它的所有子孙节点并形成树状结构
     * @param categoryList 总的集合
     * @param parentCategory 父节点
     * @return
     * @author 杨万浩
     * @createDate 2019/11/28 19:23
     */
    /*private List<GoodsCategoryTreeVo> getChildren(List<Category> categoryList, Category parentCategory) {
        List<GoodsCategoryTreeVo> resultList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getParentId().equals(parentCategory.getId())) {
                GoodsCategoryTreeVo vo = new GoodsCategoryTreeVo();
                vo.setId(category.getId());
                vo.setText(category.getName());
                vo.setChildren(getChildren(categoryList, category));
                resultList.add(vo);
            }
        }
        return resultList;
    }*/
}
