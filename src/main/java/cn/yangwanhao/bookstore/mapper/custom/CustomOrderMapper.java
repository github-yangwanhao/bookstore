package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomOrderMapper {

    /**
     * Description: 插入order，返回生成的id
     * @param order order
     * @return
     * @author 青鲤
     * @createDate 2019/12/26 15:47
     */
    Integer insertOrderBaseGenerateId(Order order);
}