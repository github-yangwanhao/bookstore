package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.dto.CartGoodsDto;
import cn.yangwanhao.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨万浩
 * @description PortalCartController类
 * @date 2020/3/9 08
 */
@Controller
@RequestMapping("/store/cart")
public class PortalCartController extends BaseController {

    @Autowired
    private CartService cartService;

    @RequestMapping("addGoods")
    @ResponseBody
    public ResponseMessage<Integer> addGoodsToCart(@RequestBody CartGoodsDto dto, HttpServletRequest request) {
        return ResponseMessage.handleResult(cartService.addCartGoods(dto.getGoodsId(), dto.getGoodsNum(), super.getLoginUserId(request)));
    }

}
