package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.dto.OrderDto;

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
     * @author 青鲤
     * @createDate 2019/12/24 17:19
     */
    Integer createOrder(OrderDto orderDto);

}
