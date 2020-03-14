package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.dto.TradeRecordDto;
import cn.yangwanhao.bookstore.entity.TradeRecord;
import cn.yangwanhao.bookstore.mapper.TradeRecordMapper;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    private OrderService orderService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer paidSuccess(TradeRecordDto dto) {
        TradeRecord record = new TradeRecord();
        // record.setId() 自增
        record.setUserId(dto.getUserId());
        record.setOrderId(dto.getOrderId());
        record.setOrderNo(dto.getOrderNo());
        record.setTradeType(dto.getTradeType());
        record.setMoney(dto.getMoney());
        record.setAlipayTransactionNum(dto.getAliPayTradeNo());
        record.setCreateTime(new Date());
        int result = 0;
        result += tradeRecordMapper.insertSelective(record);
        // 修改订单状态
        result += orderService.customerPaidOrder(dto.getOrderNo());
        // 删除key
        stringRedisTemplate.delete(GlobalConstant.RedisPrefixKey.ORDER_PREFIX + dto.getOrderNo());
        return result;
    }
}
