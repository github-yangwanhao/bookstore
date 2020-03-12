package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}