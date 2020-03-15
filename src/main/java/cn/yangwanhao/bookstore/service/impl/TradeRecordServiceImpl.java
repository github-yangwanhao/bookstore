package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.TradeRecordTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.PublicUtils;
import cn.yangwanhao.bookstore.dto.TradeRecordDto;
import cn.yangwanhao.bookstore.entity.TradeRecord;
import cn.yangwanhao.bookstore.entity.TradeRecordExample;
import cn.yangwanhao.bookstore.mapper.TradeRecordMapper;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 杨万浩
 * @description TradeServiceImpl类
 * @date 2020/3/13 17
 */
@Service
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public TradeRecord getPaidRecordByOrderNo(String orderNo) {
        TradeRecordExample example = new TradeRecordExample();
        TradeRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andTradeTypeEqualTo(TradeRecordTypeEnum.PAY.getType());
        List<TradeRecord> tradeRecords = tradeRecordMapper.selectByExample(example);
        if (PublicUtils.isEmpty(tradeRecords) || tradeRecords.size() == 0) {
            throw new GlobalException(ErrorCodeEnum.O5003009, orderNo);
        }
        return tradeRecords.get(0);
    }

    @Override
    public Integer refundSuccess(TradeRecordDto dto) {
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
        result += orderService.cancelPaidOrder(dto.getOrderNo());
        return result;
    }
}
