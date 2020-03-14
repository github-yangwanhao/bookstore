package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.common.util.PublicUtils;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.CustomerAddressService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.CartGoodsListVo;
import cn.yangwanhao.bookstore.vo.CustomerAddressListVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import cn.yangwanhao.bookstore.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨万浩
 * @description PortalPageController类
 * @date 2020/3/8 15
 */
@Controller
@RequestMapping("/store/page")
@Slf4j
public class PortalPageController extends BaseController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/login")
    public ModelAndView toLogin(@RequestParam(value = "history", required = false) String history) {
        if (StringUtils.isBlank(history) || "/store/page/login".equals(history) || "/store/login/logout".equals(history)) {
            history = "/index";
        }
        ModelAndView modelAndView = new ModelAndView("mall/login");
        modelAndView.addObject("history", history);
        log.info("history: " + history);
        return modelAndView;
    }

    @RequestMapping("/register")
    public String toRegister() {
        return "mall/register";
    }

    @RequestMapping("/cart")
    public String toCart(HttpServletRequest request, Model model) {
        List<CartGoodsListVo> vos = cartService.getCart(super.getLoginUserId(request));
        // 购物车中商品总数量
        // 购物车中商品总价格
        Integer cartTotalGoodsNum = 0;
        Double cartTotalPrice = 0D;
        List<CartGoodsListVo> effective = new ArrayList<>();
        List<CartGoodsListVo> expired = new ArrayList<>();
        for (CartGoodsListVo vo: vos) {
            if (vo.getGoodsStatus().equals(GoodsStatusEnum.NORMAL.getValue())) {
                GoodsVo goodsInfo = goodsService.getGoodsInfo(vo.getGoodsId());
                if (goodsInfo.getStock() < vo.getGoodsNum()) {
                    vo.setGoodsTitle(vo.getGoodsTitle() + "(商品库存不足)");
                }
                effective.add(vo);
                cartTotalGoodsNum += vo.getGoodsNum();
                cartTotalPrice = BigDecimalUtils.add(String.valueOf(cartTotalPrice), String.valueOf(vo.getGoodsTotalPrice())).doubleValue();
            } else {
                expired.add(vo);
            }
        }
        model.addAttribute("effective", effective);
        model.addAttribute("expired", expired);
        model.addAttribute("cartTotalGoodsNum", cartTotalGoodsNum);
        model.addAttribute("cartTotalPrice", cartTotalPrice);
        return "mall/cart";
    }

    @RequestMapping("/order-settle")
    public String toOrderSettle(Model model, HttpServletRequest request) {
        Long loginUserId = super.getLoginUserId(request);
        // 获取收货地址列表
        List<CustomerAddressListVo> addressList = customerAddressService.listUserAddresses(loginUserId);
        if (PublicUtils.isEmpty(addressList)) {
            throw new GlobalException(ErrorCodeEnum.O5009015);
        }
        // 获取购物车商品列表
        List<CartGoodsListVo> cartGoodsList = cartService.getCart(loginUserId);
        Integer cartTotalGoodsNum = 0;
        Double cartTotalPrice = 0D;
        List<CartGoodsListVo> effective = new ArrayList<>();
        for (CartGoodsListVo vo: cartGoodsList) {
            if (vo.getGoodsStatus().equals(GoodsStatusEnum.NORMAL.getValue())) {
                effective.add(vo);
                cartTotalGoodsNum += vo.getGoodsNum();
                cartTotalPrice = BigDecimalUtils.add(String.valueOf(cartTotalPrice), String.valueOf(vo.getGoodsTotalPrice())).doubleValue();
            }
        }
        model.addAttribute("addressList", addressList);
        model.addAttribute("cartGoodsList", effective);
        model.addAttribute("cartTotalGoodsNum", cartTotalGoodsNum);
        model.addAttribute("cartTotalPrice", cartTotalPrice);
        return "mall/order-settle";
    }

    @RequestMapping("/personal")
    public String toPersonal(Model model, HttpServletRequest request) throws ParseException {
        Long loginUserId = super.getLoginUserId(request);
        UserInfoVo vo = userService.getUserInfoById(loginUserId);
        List<CustomerAddressListVo> vos = customerAddressService.listUserAddresses(loginUserId);
        model.addAttribute("path", "personal");
        model.addAttribute("vo", vo);
        model.addAttribute("vos", vos);
        return "mall/personal";
    }

}
