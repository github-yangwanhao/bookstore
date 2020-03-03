package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.entity.OrderGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomOrderGoodsMapper {

    /**
     * Description: 批量插入OrderGoods
     * @param list order_goods_list
     * @return
     * @author 杨万浩
     * @createDate 2019/12/26 15:55
     */
    Integer insertBatchOrderGoods(List<OrderGoods> list);

}