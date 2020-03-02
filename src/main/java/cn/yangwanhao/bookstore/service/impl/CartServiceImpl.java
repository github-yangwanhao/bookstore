package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.entity.Cart;
import cn.yangwanhao.bookstore.entity.CartExample;
import cn.yangwanhao.bookstore.mapper.CartMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomCartMapper;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public List<CartGoodsListVo> getCart(Long loginUserId) {
        List<CartGoodsListVo> vos = customCartMapper.selectCart(loginUserId);
        vos.forEach(vo ->{
            // 设置封面
            vo.setGoodsCoverImageUrl(StringUtils.isBlank(vo.getGoodsCoverImageUrl()) ? "":vo.getGoodsCoverImageUrl().split(GlobalConstant.Symbol.COMMA)[0]);
            // 设置价格
            vo.setPriceDouble(BigDecimalUtils.movePointLeft(vo.getPrice().toString(), 2).doubleValue());
            // 设置是否删除
            vo.setGoodsIsDeleted(vo.getGoodsStatus().equals(GoodsStatusEnum.DELETED.getValue()) ? 1:0);
            // 设置是否下架
            vo.setGoodsIsOffTheShelves(vo.getGoodsStatus().equals(GoodsStatusEnum.OFF_THE_SHELF.getValue()) ? 1:0);
        });
        return vos;
    }

    @Override
    public Integer addCartGoods(Long goodsId, Integer goodsNum, Long loginUserId) {
        Cart cart = new Cart();
        cart.setUserId(loginUserId);
        cart.setGoodsId(goodsId);
        cart.setGoodsNum(goodsNum);
        cart.setGoodsIsDeleted(0);
        cart.setGoodsIsOffTheShelves(0);
        cart.setGoodsVersion(goodsService.getGoodsInfo(goodsId).getGoodsVersion());
        return cartMapper.insertSelective(cart);
    }

    @Override
    public Integer removeCartGoods(Long goodsId, Long loginUserId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(loginUserId);
        criteria.andGoodsIdEqualTo(goodsId);
        return cartMapper.deleteByExample(cartExample);
    }

    @Override
    public Integer modifyCartGoods(Long goodsId, Integer operate, Long loginUserId) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserIdEqualTo(loginUserId);
        criteria.andGoodsIdEqualTo(goodsId);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if (operate == 1) {
            // +1操作,如果不存在则执行新增操作
            if (carts == null || carts.size() == 0) {
                return addCartGoods(goodsId, 1, loginUserId);
            }
        } else {
            if (carts == null || carts.size() == 0) {
                // -1操作,如果不存在抛出异常
                throw new GlobalException(ErrorCodeEnum.C5003003);
            } else if (carts.get(0).getGoodsNum() == 1) {
                // -1操作,如果当前值为1则抛出异常
                throw new GlobalException(ErrorCodeEnum.C5009007);
            }
        }
        // 执行+1或者-1操作
        return customCartMapper.updateCartGoodsNum(goodsId, operate, loginUserId);
    }

    @Override
    public Integer onShelfGoods(Long goodsId) {
        return customCartMapper.onShelfGoods(goodsId);
    }

    @Override
    public Integer offShelfGoods(Long goodsId) {
        return customCartMapper.offShelfGoods(goodsId);
    }

    @Override
    public Integer manageDeleteGoods(Long goodsId) {
        return customCartMapper.manageDeleteGoods(goodsId);
    }
}
