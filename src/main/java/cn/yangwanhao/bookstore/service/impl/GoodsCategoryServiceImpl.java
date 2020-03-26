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
import cn.yangwanhao.bookstore.vo.CategoryListVo;
import cn.yangwanhao.bookstore.vo.CategoryMenuTreeVo;
import cn.yangwanhao.bookstore.vo.CategoryTreeVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/27 18:07
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private CustomCategoryMapper customCategoryMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper objectMapper;

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
        int result = categoryMapper.insertSelective(category);
        if (result == 1) {
            // 删除redis
            log.info("从redis中删除分类树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_TREE);
            log.info("从redis中删除分类菜单树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_MENU_TREE);
        }
        return result;
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
    public Integer removeCategories(Integer[] categoryIds) {
        int count = 0;
        for(Integer id : categoryIds) {
            count += removeCategory(id);
        }
        if (count >= 1) {
            // 删除redis
            log.info("从redis中删除分类树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_TREE);
            log.info("从redis中删除分类菜单树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_MENU_TREE);
        }
        return count;
    }

    @Override
    public Integer modifyCategory(Integer id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        int result = categoryMapper.updateByPrimaryKeySelective(category);
        if (result >= 1) {
            // 删除redis
            log.info("从redis中删除分类树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_TREE);
            log.info("从redis中删除分类菜单树缓存");
            stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.CATEGORY_MENU_TREE);
        }
        return result;
    }

    @Override
    public PageInfo<CategoryListVo> listCategory(Integer pid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryListVo> list = customCategoryMapper.listCategories(pid);
        return new PageInfo<>(list);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryTreeVo> listCategoryTree() throws IOException {
        // redis缓存 ①查询缓存 有则返回 无则进行下一步
        String value = stringRedisTemplate.opsForValue().get(GlobalConstant.RedisPrefixKey.CATEGORY_TREE);
        if (StringUtils.isNotBlank(value)) {
            List<CategoryTreeVo> result = objectMapper.readValue(value, new TypeReference<List<CategoryTreeVo>>() {
            });
            log.info("从缓存中查询得到分类树");
            return result;
        }
        final String prefix = "├─";
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(0);
        List<Category> rootCategories = categoryMapper.selectByExample(example);
        List<Category> allCategories = categoryMapper.selectByExample(new CategoryExample());
        List<CategoryTreeVo> resultVoList = new ArrayList<>();
        setNameList(allCategories, rootCategories, prefix, resultVoList);
        // 查询完成 添加缓存
        String json = objectMapper.writeValueAsString(resultVoList);;
        stringRedisTemplate.opsForValue().set(GlobalConstant.RedisPrefixKey.CATEGORY_TREE, json);
        log.info("向缓存中加入分类树");
        return resultVoList;
    }

    @Override
    public List<CategoryMenuTreeVo> listCategoryMenuTree() throws IOException {
        // redis缓存 ①查询缓存 有则返回 无则进行下一步
        String value = stringRedisTemplate.opsForValue().get(GlobalConstant.RedisPrefixKey.CATEGORY_MENU_TREE);
        if (StringUtils.isNotBlank(value)) {
            List<CategoryMenuTreeVo> result = objectMapper.readValue(value, new TypeReference<List<CategoryMenuTreeVo>>() {
            });
            log.info("从缓存中查询得到分类菜单树");
            return result;
        }
        List<Category> all = categoryMapper.selectByExample(new CategoryExample());
        List<CategoryMenuTreeVo> list = buildTree(all);
        // 查询完成 添加缓存
        String json = objectMapper.writeValueAsString(list);;
        stringRedisTemplate.opsForValue().set(GlobalConstant.RedisPrefixKey.CATEGORY_MENU_TREE, json);
        log.info("向缓存中加入分类菜单树");
        return list;
    }

    private List<CategoryMenuTreeVo> buildTree(List<Category> all) {
        List<CategoryMenuTreeVo> menu = new ArrayList<>();
        for (Category c : all) {
            if (c.getParentId() == 0) {
                CategoryMenuTreeVo vo = new CategoryMenuTreeVo();
                vo.setId(c.getId());
                vo.setName(c.getName());
                vo.setChildren(buildChildrenTree(all, c));
                menu.add(vo);
            }
        }
        return menu;
    }

    private List<CategoryMenuTreeVo> buildChildrenTree(List<Category> all, Category root) {
        List<CategoryMenuTreeVo> children = new ArrayList<>();
        for (Category c : all) {
            if (c.getParentId().equals(root.getId())) {
                CategoryMenuTreeVo child = new CategoryMenuTreeVo();
                child.setId(c.getId());
                child.setName(c.getName());
                child.setChildren(buildChildrenTree(all, c));
                children.add(child);
            }
        }
        return children;
    }

    private void setNameList(List<Category> allData, List<Category> rootList, String prefix, List<CategoryTreeVo> result) {
        // 半角空格
        final String blankSpaceStr = "　";
        for (Category root : rootList) {
            CategoryTreeVo vo = new CategoryTreeVo();
            vo.setId(root.getId());
            vo.setName(prefix + root.getName());
            result.add(vo);
            setNameList(allData, getChildren(allData, root), blankSpaceStr + prefix, result);
        }
    }

    /**
     * Description: 通过一个节点查找它的所有子孙节点并形成树状结构
     * @param allData 总的集合
     * @param parentCategory 父节点
     * @return
     * @author 杨万浩
     * @createDate 2019/11/28 19:23
     */
    private List<Category> getChildren(List<Category> allData, Category parentCategory) {
        List<Category> resultList = new ArrayList<>();
        for (Category category : allData) {
            if (category.getParentId().equals(parentCategory.getId())) {
                resultList.add(category);
            }
        }
        return resultList;
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

}
