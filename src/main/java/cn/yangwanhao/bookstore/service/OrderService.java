package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.dto.OrderDto;
import cn.yangwanhao.bookstore.vo.OrderVo;

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

}
