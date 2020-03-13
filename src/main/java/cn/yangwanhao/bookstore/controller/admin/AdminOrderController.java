package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.vo.OrderGoodsListVo;
import cn.yangwanhao.bookstore.vo.OrderListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨万浩
 * @description AdminOrderController类
 * @date 2020/3/13 10
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    @ResponseBody
    public ResponseMessage<PageInfo<OrderListVo>> orderList(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        return ResponseMessage.success(orderService.adminOrderList(pageNum, pageSize));
    }

    @RequestMapping("/order-goods/{orderNo}")
    @ResponseBody
    public ResponseMessage<List<OrderGoodsListVo>> getOrderGoods(@PathVariable("orderNo") String orderNo) {
        return ResponseMessage.success(orderService.selectOrderItem(orderNo));
    }

    @RequestMapping("/start")
    @ResponseBody
    public ResponseMessage<Integer> getOrderGoods(@RequestBody Integer[] ids) {
        return ResponseMessage.handleResult(orderService.orderStart(ids));
    }

}
