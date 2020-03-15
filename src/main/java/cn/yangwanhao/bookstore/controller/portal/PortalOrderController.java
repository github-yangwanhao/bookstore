package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.enums.OrderStatusEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.properties.AliPaySandBoxProperties;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.common.util.PublicUtils;
import cn.yangwanhao.bookstore.dto.OrderDto;
import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.CustomerAddressService;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import cn.yangwanhao.bookstore.vo.CustomerAddressVo;
import cn.yangwanhao.bookstore.vo.OrderListVo;
import cn.yangwanhao.bookstore.vo.OrderVo;
import com.alipay.api.AlipayApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private CustomerAddressService customerAddressService;
    @Resource
    private ObjectMapper objectMapper;
    @Autowired
    private AliPayController aliPayController;

    @RequestMapping("/save/{addressId}")
    public String saveOrder(@PathVariable("addressId") Long addressId, HttpServletRequest request) {
        Long loginUserId = super.getLoginUserId(request);
        CustomerAddressVo address = customerAddressService.getUserAddress(addressId, loginUserId);
        if (address == null) {
            throw new GlobalException(ErrorCodeEnum.U5003006);
        }
        // 获取购物车商品列表
        List<CartGoodsListVo> cartGoodsList = cartService.getCart(loginUserId);
        if (PublicUtils.isEmpty(cartGoodsList)) {
            throw new GlobalException(ErrorCodeEnum.C5003007);
        }
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

    @RequestMapping("/list")
    public String orderList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                            HttpServletRequest request, Model model) {
        Long loginUserId = super.getLoginUserId(request);
        PageInfo<OrderListVo> page = orderService.portalOrderList(pageNum, pageSize, loginUserId);
        model.addAttribute("page", page);
        return "mall/my-orders";
    }

    @RequestMapping("/cancel/{orderNo}")
    @ResponseBody
    public ResponseMessage<Integer> cancelOrder(@PathVariable("orderNo") String orderNo, HttpServletRequest request) throws JsonProcessingException, AlipayApiException {
        Order order = orderService.getOrderByOrderNo(orderNo);
        if (PublicUtils.isEmpty(order)) {
            throw new GlobalException(ErrorCodeEnum.O5003008, orderNo);
        }
        if (!order.getUserId().equals(super.getLoginUserId(request))) {
            throw new GlobalException(ErrorCodeEnum.O5003008, orderNo);
        }
        // 待付款的订单直接取消
        if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_TO_PAY.getStatus())) {
            Integer result = orderService.cancelNotPaidOrder(orderNo);
            return ResponseMessage.handleResult(result);
        }
        // 付款后未发货的订单取消
        if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_TO_SHIPMENTS.getStatus())) {
            return aliPayController.refund(orderNo);
        }
        return null;
    }

    @RequestMapping("/complete/{orderNo}")
    @ResponseBody
    public ResponseMessage<Integer> completeOrder(@PathVariable("orderNo") String orderNo, HttpServletRequest request) {
        return ResponseMessage.handleResult(orderService.completeOrder(orderNo, super.getLoginUserId(request)));
    }

}
