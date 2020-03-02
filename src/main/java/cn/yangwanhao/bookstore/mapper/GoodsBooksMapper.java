package cn.yangwanhao.bookstore.mapper;

import cn.yangwanhao.bookstore.entity.GoodsBooks;
import cn.yangwanhao.bookstore.entity.GoodsBooksExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsBooksMapper {
    int countByExample(GoodsBooksExample example);

    int deleteByExample(GoodsBooksExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsBooks record);

    int insertSelective(GoodsBooks record);

    List<GoodsBooks> selectByExample(GoodsBooksExample example);

    GoodsBooks selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsBooks record, @Param("example") GoodsBooksExample example);

    int updateByExample(@Param("record") GoodsBooks record, @Param("example") GoodsBooksExample example);

    int updateByPrimaryKeySelective(GoodsBooks record);

    int updateByPrimaryKey(GoodsBooks record);
}