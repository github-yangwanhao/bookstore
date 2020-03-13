package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.dto.OrderDto;
import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.vo.OrderGoodsListVo;
import cn.yangwanhao.bookstore.vo.OrderListVo;
import cn.yangwanhao.bookstore.vo.OrderVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/24 17:15
 */

public interface OrderService {

    /**
     * Description: 创建订单
     * @param orderDto dto
     * @return
     * @author 杨万浩
     * @createDate 2019/12/24 17:19
     */
    String createOrder(OrderDto orderDto);

    /**
     * Description: 获取订单详情
     * @param orderNo no
     * @param loginUserId loginUserId
     * @return
     * @author 杨万浩
     * @date 2020/3/12 14:31
     */

    OrderVo getOrderDetail(String orderNo, Long loginUserId);
    /**
     * Description: 获取订单详情
     * @param orderNo no
     * @return
     * @author 杨万浩
     * @date 2020/3/12 14:31
     */
    Order getOrderByOrderNo(String orderNo);

    /**
     * Description: 顾客个人订单列表
     * @param pageNum num
     * @param pageSize size
     * @param userId userId
     * @return
     * @author 杨万浩
     * @date 2020/3/13 9:23
     */
    PageInfo<OrderListVo> portalOrderList(Integer pageNum, Integer pageSize, Long userId);
    
    /**
     * Description: 管理员订单列表
     * @param pageNum num
     * @param pageSize size
     * @return
     * @author 杨万浩
     * @date 2020/3/13 10:19
     */
    PageInfo<OrderListVo> adminOrderList(Integer pageNum, Integer pageSize);

    /**
     * Description: 查看订单商品列表
     * @param orderNo no
     * @return
     * @author 杨万浩
     * @date 2020/3/13 11:17
     */
    List<OrderGoodsListVo> selectOrderItem(String orderNo);

    /**
     * Description: 订单发货
     * @param ids ids
     * @return
     * @author 杨万浩
     * @date 2020/3/13 11:39
     */
    Integer orderStart(Integer[] ids);
    
    /**
     * Description: 顾客付款后修改订单状态和支付时间
     * @param orderNo orderNo
     * @return 
     * @author 杨万浩
     * @date 2020/3/13 20:14
     */
    Integer customerPaidOrder(String orderNo);

}
