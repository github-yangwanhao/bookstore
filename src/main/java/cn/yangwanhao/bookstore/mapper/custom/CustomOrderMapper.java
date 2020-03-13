package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.vo.OrderGoodsListVo;
import cn.yangwanhao.bookstore.vo.OrderListVo;
import cn.yangwanhao.bookstore.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomOrderMapper {

    /**
     * Description: 插入order，返回生成的id
     * @param order order
     * @return
     * @author 杨万浩
     * @createDate 2019/12/26 15:47
     */
    Integer insertOrderBaseGenerateId(Order order);

    /**
     * Description: 订单详情
     * @param orderNo no
     * @param loginUserId userId
     * @return
     * @author 杨万浩
     * @date 2020/3/12 14:33
     */
    OrderVo getOrderDetail(@Param("orderNo") String orderNo, @Param("loginUserId") Long loginUserId);

    /**
     * Description: 订单详情
     * @param orderNo no
     * @return
     * @author 杨万浩
     * @date 2020/3/12 14:33
     */
    Order getOrderByOrderNo(@Param("orderNo") String orderNo);

    /**
     * Description: 顾客查看订单列表
     * @param loginUserId userId
     * @return
     * @author 杨万浩
     * @date 2020/3/13 9:25
     */
    List<OrderListVo> portalListOrder(@Param("loginUserId") Long loginUserId);

    /**
     * Description: 管理查看订单列表
     * @return
     * @author 杨万浩
     * @date 2020/3/13 10:26
     */
    List<OrderListVo> adminListOrder();

    /**
     * Description: 查看订单商品列表
     * @param orderNo orderNo
     * @return
     * @author 杨万浩
     * @date 2020/3/13 11:19
     */
    List<OrderGoodsListVo> listOrderGoods(String orderNo);
    
    /**
     * Description: 根据id查找订单
     * @param ids ids
     * @return 
     * @author 杨万浩
     * @date 2020/3/13 11:45
     */
    List<Order> selectOrders(@Param("ids") Integer[] ids);

    /**
     * Description: 订单发货
     * @param ids
     * @return
     * @author 杨万浩
     * @date 2020/3/13 12:31
     */
    Integer startOrder(@Param("ids") Integer[] ids);

    /**
     * Description: 顾客付款后修改订单状态和支付时间
     * @param orderNo orderNo
     * @return
     * @author 杨万浩
     * @date 2020/3/13 20:18
     */
    Integer customerPaidOrder(@Param("orderNo") String orderNo);
}