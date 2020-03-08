package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 15:34
 */

@Mapper
@Repository
public interface CustomCartMapper {
    
    /**
     * Description: 查询用户购物车
     * @param loginUserId 当前登录人id
     * @return vo
     * @author 杨万浩
     * @createDate 2019/12/9 15:42
     */
    List<CartGoodsListVo> selectCart(@Param("loginUserId") Long loginUserId);

    /**
     * Description: 购物车某件商品数量+1/-1
     * @param goodsId 商品id
     * @param operate 1:+1/2:-1
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 9:47
     */
    Integer updateCartGoodsNum(@Param("goodsId") Long goodsId, @Param("operate") Integer operate, @Param("loginUserId") Long loginUserId);

    /**
     * Description: 管理员上架某商品,购物车中即将商品表为已上架
     * @param ids 商品ids
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 13:40
     */
    Integer onShelfGoods(@Param("ids") Long[] ids);

    /**
     * Description: 管理员下架某商品,购物车中将商品标为已下架
     * @param ids 商品ids
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 13:40
     */
    Integer offShelfGoods(@Param("ids") Long[] ids);

    /**
     * Description: 管理员删除了某个商品,购物车中将商品标记为已删除
     * @param goodsId 商品id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 13:48
     */
    Integer manageDeleteGoods(@Param("goodsId") Long goodsId);

}
