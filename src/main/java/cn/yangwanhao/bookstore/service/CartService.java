package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.vo.CartGoodsListVo;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 14:52
 */

public interface CartService {

    /**
     * Description: 查看当前用户购物车
     * @param loginUserId 当前登录人id
     * @return vo
     * @author 杨万浩
     * @createDate 2019/12/9 14:57
     */
    List<CartGoodsListVo> getCart(Long loginUserId);

    /**
     * Description: 购物车添加一个商品
     * @param goodsId 商品id
     * @param goodsNum 商品数量
     * @param loginUserId 当前登录人id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/9 17:33
     */
    Integer addCartGoods(Long goodsId, Integer goodsNum, Long loginUserId);

    /**
     * Description: 购物车删除一件商品
     * @param goodsId 商品id
     * @param loginUserId 当前登录人id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/9 18:23
     */
    Integer removeCartGoods(Long goodsId, Long loginUserId);

    /**
     * Description: 购物车某件商品数量+1/-1
     * @param goodsId 商品id
     * @param operate 1:+1/2:-1
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/9 20:45
     */
    Integer modifyCartGoods(Long goodsId, Integer operate, Long loginUserId);

    /**
     * Description: 管理员上架某商品,购物车中即将商品表为已上架
     * @param goodsId 商品id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 10:25
     */
    Integer onShelfGoods(Long goodsId);

    /**
     * Description: 管理员下架某商品,购物车中将商品标为已下架
     * @param goodsId 商品id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 10:25
     */
    Integer offShelfGoods(Long goodsId);

    /**
     * Description: 管理员删除了某个商品,购物车中将商品标记为已删除
     * @param goodsId 商品id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 13:48
     */
    Integer manageDeleteGoods(Long goodsId);

}
