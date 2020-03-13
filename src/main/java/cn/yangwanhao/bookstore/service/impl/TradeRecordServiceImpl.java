package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.dto.TradeRecordDto;
import cn.yangwanhao.bookstore.entity.TradeRecord;
import cn.yangwanhao.bookstore.mapper.TradeRecordMapper;
import cn.yangwanhao.bookstore.service.TradeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 杨万浩
 * @description TradeServiceImpl类
 * @date 2020/3/13 17
 */
@Service
public class TradeRecordServiceImpl implements TradeRecordService {

    @Resource
    private TradeRecordMapper tradeRecordMapper;

    @Override
    public Integer saveTradeRecord(TradeRecordDto dto) {
        TradeRecord record = new TradeRecord();
        // record.setId() 自增
        record.setUserId(dto.getUserId());
        record.setOrderId(dto.getOrderId());
        record.setOrderNo(dto.getOrderNo());
        record.setTradeType(dto.getTradeType());
        record.setMoney(dto.getMoney());
        record.setAlipayTransactionNum(dto.getAliPayTradeNo());
        record.setCreateTime(new Date());
        return tradeRecordMapper.insertSelective(record);
    }
}
