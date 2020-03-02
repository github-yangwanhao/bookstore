package cn.yangwanhao.bookstore.mapper;

import cn.yangwanhao.bookstore.entity.TradeRecord;
import cn.yangwanhao.bookstore.entity.TradeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TradeRecordMapper {
    int countByExample(TradeRecordExample example);

    int deleteByExample(TradeRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TradeRecord record);

    int insertSelective(TradeRecord record);

    List<TradeRecord> selectByExample(TradeRecordExample example);

    TradeRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByExample(@Param("record") TradeRecord record, @Param("example") TradeRecordExample example);

    int updateByPrimaryKeySelective(TradeRecord record);

    int updateByPrimaryKey(TradeRecord record);
}