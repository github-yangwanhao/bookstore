package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.dto.GoodsListDto;
import cn.yangwanhao.bookstore.entity.GoodsBase;
import cn.yangwanhao.bookstore.vo.GoodsListVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 17:54
 */

@Mapper
@Repository
public interface CustomGoodsMapper {

    /**
     * Description: 通过id查看商品信息
     * @param goodsId 商品id
     * @return vo
     * @author 杨万浩
     * @createDate 2019/12/9 17:55
     */
    GoodsVo selectGoods(@Param("goodsId") Long goodsId);

    /**
     * Description: 向GoodsBase中插入一条数据并获取生成的id
     * @param goodsBase goodsBase
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 11:07
     */
    Integer insertGoodsBaseGenerateId(GoodsBase goodsBase);

    /**
     * Description: 查看商品列表
     * @param dto dto
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 20:29
     */
    List<GoodsListVo> selectGoodsList(GoodsListDto dto);

    /**
     * Description: 根据分类id查找一个商品
     * @param categoryId 分类id
     * @return A GoodsBase
     * @author 杨万浩
     * @date 2020/2/26 17:54
     */
    GoodsBase selectOneGoodsByCategory(@Param("categoryId") Integer categoryId);

}