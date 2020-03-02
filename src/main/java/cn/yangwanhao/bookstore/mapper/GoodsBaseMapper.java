package cn.yangwanhao.bookstore.mapper;

import cn.yangwanhao.bookstore.entity.GoodsBase;
import cn.yangwanhao.bookstore.entity.GoodsBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsBaseMapper {
    int countByExample(GoodsBaseExample example);

    int deleteByExample(GoodsBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsBase record);

    int insertSelective(GoodsBase record);

    List<GoodsBase> selectByExample(GoodsBaseExample example);

    GoodsBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsBase record, @Param("example") GoodsBaseExample example);

    int updateByExample(@Param("record") GoodsBase record, @Param("example") GoodsBaseExample example);

    int updateByPrimaryKeySelective(GoodsBase record);

    int updateByPrimaryKey(GoodsBase record);
}