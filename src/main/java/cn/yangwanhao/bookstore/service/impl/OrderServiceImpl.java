package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.enums.OrderStatusEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.common.util.IdUtils;
import cn.yangwanhao.bookstore.common.util.PubUtils;
import cn.yangwanhao.bookstore.dto.OrderDto;
import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.entity.OrderGoods;
import cn.yangwanhao.bookstore.mapper.OrderGoodsMapper;
import cn.yangwanhao.bookstore.mapper.OrderMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomOrderGoodsMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomOrderMapper;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/24 17:15
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;
    @Resource
    private CustomOrderMapper customOrderMapper;
    @Resource
    private CustomOrderGoodsMapper customOrderGoodsMapper;
    @Resource
    private GoodsService goodsService;

    @Override
    public Integer createOrder(OrderDto orderDto) {
        int result = 0;
        Long[] goodsIds = PubUtils.convertStringToLong(orderDto.getGoodsId().split(","));
        Integer[] goodsNums = PubUtils.convertStringToInteger(orderDto.getGoodsNum().split(","));
        // 如果参数长度不相等 抛出参数异常
        if (goodsIds.length != goodsNums.length) {
            throw new GlobalException(ErrorCodeEnum.G500101);
        }

        // 获取商品价格
        Long[] goodsPrices = new Long[goodsIds.length];
        for (int i=0; i<goodsIds.length; i++) {
            GoodsVo goodsVo = goodsService.getGoodsInfo(goodsIds[i]);
            goodsPrices[i] = goodsVo.getPriceLong();
        }

        // 计算订单总价格
        long totalPrice = 0L;
        for (int i=0; i<goodsPrices.length; i++) {
            totalPrice += BigDecimalUtils.mul(goodsNums[i].toString(), goodsPrices[i].toString()).longValue();
        }

        // 计算得到的总价和前端传来的不一致
        if (totalPrice != orderDto.getTotalPrice()) {
            throw new GlobalException(ErrorCodeEnum.O5009008);
        }

        // 插入order表
        Order order = new Order();
        // 雪花算法自增
        order.setOrderNo(IdUtils.getSnowFlakeId().toString());
        order.setUserId(orderDto.getUserId());
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatusEnum.WAIT_TO_PAY.getStatus());
        order.setIsDeleted(GlobalConstant.NO);
        order.setCreateTime(new Date());
        // 插入Order表，返回生成的id
        result += customOrderMapper.insertOrderBaseGenerateId(order);

        List<OrderGoods> orderGoodsList = new ArrayList<>(goodsIds.length);
        for (int i=0; i<goodsIds.length; i++) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(goodsIds[i]);
            orderGoods.setGoodsNum(goodsNums[i]);
            orderGoods.setGoodsPrice(goodsPrices[i]);
            // 单价 * 数量
            orderGoods.setGoodsTotalPrice(BigDecimalUtils.mul(goodsNums[i].toString(), goodsPrices[i].toString()).longValue());
            orderGoods.setGoodsStatus(GoodsStatusEnum.NORMAL.getValue());
            orderGoodsList.add(orderGoods);
        }
        // 批量插入订单商品表
        result += customOrderGoodsMapper.insertBatchOrderGoods(orderGoodsList);
        return result;
    }
}