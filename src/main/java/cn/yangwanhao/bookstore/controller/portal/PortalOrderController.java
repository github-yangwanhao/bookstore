package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.dto.OrderDto;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import cn.yangwanhao.bookstore.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨万浩
 * @description PortalOrderController类
 * @date 2020/3/12 10
 */
@Controller
@RequestMapping("/store/order")
public class PortalOrderController extends BaseController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/save/{addressId}")
    public String saveOrder(@PathVariable("addressId") Long addressId, HttpServletRequest request) {
        Long loginUserId = super.getLoginUserId(request);
        // 获取购物车商品列表
        List<CartGoodsListVo> cartGoodsList = cartService.getCart(loginUserId);
        Long cartTotalPrice = 0L;
        StringBuffer goodsIds = new StringBuffer();
        StringBuffer goodsNums = new StringBuffer();
        for (CartGoodsListVo vo: cartGoodsList) {
            if (vo.getGoodsStatus().equals(GoodsStatusEnum.NORMAL.getValue())) {
                goodsIds.append(vo.getGoodsId());
                goodsIds.append(",");
                goodsNums.append(vo.getGoodsNum());
                goodsNums.append(",");
                long goodsTotalPriceLong = BigDecimalUtils.mul(String.valueOf(vo.getPrice()), String.valueOf(vo.getGoodsNum())).longValue();
                cartTotalPrice = BigDecimalUtils.add(String.valueOf(cartTotalPrice),
                        String.valueOf(goodsTotalPriceLong)).longValue();
            }
        }
        OrderDto dto = new OrderDto();
        dto.setGoodsId(goodsIds.toString());
        dto.setGoodsNum(goodsNums.toString());
        dto.setTotalPrice(cartTotalPrice);
        dto.setUserId(loginUserId);
        dto.setAddressId(addressId);
        String orderNo = orderService.createOrder(dto);
        return "redirect:/store/order/detail/"+orderNo;
    }

    @RequestMapping("/detail/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, HttpServletRequest request, Model model) {
        OrderVo vo = orderService.getOrderDetail(orderNo, super.getLoginUserId(request));
        model.addAttribute("vo", vo);
        return "mall/order-detail";
    }

}
