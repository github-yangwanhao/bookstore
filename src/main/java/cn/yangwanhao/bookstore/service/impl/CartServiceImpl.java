package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.common.util.PicNameUtils;
import cn.yangwanhao.bookstore.entity.Cart;
import cn.yangwanhao.bookstore.entity.CartExample;
import cn.yangwanhao.bookstore.mapper.CartMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomCartMapper;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 14:53
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CustomCartMapper customCartMapper;
    @Resource
    private CartMapper cartMapper;
    @Value("${pic.host}")
    private String picHost;

    @Override
    public List<CartGoodsListVo> getCart(Long loginUserId) {
        List<CartGoodsListVo> vos = customCartMapper.selectCart(loginUserId);
        vos.forEach(vo ->{
            // 设置价格
            vo.setPriceDouble(BigDecimalUtils.movePointLeft(vo.getPrice().toString(), 2).doubleValue());
            // 设置商品标题
            if (vo.getGoodsStatus().equals(GoodsStatusEnum.NORMAL.getValue())) {
                if (vo.getGoodsTitle().length() > 28) {
                    vo.setGoodsTitle(vo.getGoodsTitle().substring(0,28) + "...");
                }
            } else {
                if (vo.getGoodsTitle().length() > 20) {
                    vo.setGoodsTitle(vo.getGoodsTitle().substring(0,20) + "...");
                }
            }
            // 设置封面
            vo.setGoodsCoverImageUrl(picHost + (StringUtils.isBlank(vo.getGoodsCoverImageUrl()) ? "": PicNameUtils.getThumbnailName(vo.getGoodsCoverImageUrl())));
            // 设置是否删除
            vo.setGoodsIsDeleted(vo.getGoodsStatus().equals(GoodsStatusEnum.DELETED.getValue()) ? 1:0);
            // 设置是否下架
            vo.setGoodsIsOffTheShelves(vo.getGoodsStatus().equals(GoodsStatusEnum.OFF_THE_SHELF.getValue()) ? 1:0);
            // 设置商品单品总价格
            vo.setGoodsTotalPrice(BigDecimalUtils.mul(String.valueOf(vo.getPriceDouble()),
                    String.valueOf(vo.getGoodsNum())).doubleValue());
        });
        return vos;
    }

    @Override
    public Integer addCartGoods(Long goodsId, Integer goodsNum, Long loginUserId) {
        Cart cart = new Cart();
        // 先查询购物车中是否有这个商品
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(loginUserId);
        criteria.andGoodsIdEqualTo(goodsId);
        List<Cart> carts = cartMapper.selectByExample(example);
        // 如果有 商品数量++
        if (carts.size() == 1) {
            cart.setId(carts.get(0).getId());
            cart.setGoodsNum(goodsNum + carts.get(0).getGoodsNum());
            return cartMapper.updateByPrimaryKeySelective(cart);
        }
        // 如果没有 则插入
        cart.setUserId(loginUserId);
        cart.setGoodsId(goodsId);
        cart.setGoodsNum(goodsNum);
        cart.setGoodsIsDeleted(0);
        cart.setGoodsIsOffTheShelves(0);
        cart.setGoodsVersion(goodsService.getGoodsInfo(goodsId).getGoodsVersion());
        return cartMapper.insertSelective(cart);
    }

    @Override
    public Integer removeCartGoods(Long cartId, Long loginUserId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(loginUserId);
        criteria.andIdEqualTo(cartId);
        return cartMapper.deleteByExample(cartExample);
    }

    @Override
    public Integer modifyCartGoods(Long cartId, Integer num, Long loginUserId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(loginUserId);
        criteria.andIdEqualTo(cartId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if (carts == null || carts.size() == 0) {
            throw new GlobalException(ErrorCodeEnum.C5003003);
        }
        return customCartMapper.updateCartGoodsNum(cartId, num, loginUserId);
    }

    @Override
    public Integer onShelfGoods(Long[] goodsIds) {
        return customCartMapper.onShelfGoods(goodsIds);
    }

    @Override
    public Integer offShelfGoods(Long[] goodsIds) {
        return customCartMapper.offShelfGoods(goodsIds);
    }

    @Override
    public Integer manageDeleteGoods(Long goodsId) {
        return customCartMapper.manageDeleteGoods(goodsId);
    }
}
