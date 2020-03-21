package cn.yangwanhao.bookstore.listener;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author 杨万浩
 * @description RedisKeyExpirationListener类
 * @date 2020/3/13 21
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private OrderService orderService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        log.info("------------------redis key 失效; key = " + expiredKey);
        if (expiredKey.startsWith(GlobalConstant.RedisPrefixKey.ORDER_PREFIX)) {
            // 获取订单orderNO
            String orderNo = expiredKey.substring(expiredKey.lastIndexOf(":")+1);
            // 将待支付的订单改为已取消(超时未支付)
            orderService.orderPaidTimeout(orderNo);
        }
    }
}
