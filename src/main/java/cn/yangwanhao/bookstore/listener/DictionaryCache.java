package cn.yangwanhao.bookstore.listener;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.RedisLockEnum;
import cn.yangwanhao.bookstore.service.DictionaryService;
import cn.yangwanhao.bookstore.vo.DictionaryVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/28 16:20
 */

/*@Component
@Slf4j
public class DictionaryCache implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DictionaryService dictionaryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //redis锁防并发
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(RedisLockEnum.LOCK_DICTIONARY.getKey(), RedisLockEnum.LOCK_DICTIONARY.getValue(), RedisLockEnum.LOCK_DICTIONARY.getTime(), RedisLockEnum.LOCK_DICTIONARY.getTimeUnit());
        if (!flag) {
            return;
        }
        try {
            log.info("--------------------清除字典表缓存开始--------------------");
            // 清除老数据
            // 删除所有以前缀开头 + "*"的数据
            Set<String> keys = stringRedisTemplate.keys(GlobalConstant.RedisPrefixKey.DICTIONARY_PREFIX + "*");
             if (keys != null) {
                 for (String redisKey : keys) {
                     stringRedisTemplate.delete(redisKey);
                 }
             }
            log.info("--------------------清除字典表缓存结束--------------------");
            log.info("--------------------初始化字典表至缓存开始--------------------");
            List<DictionaryVo> list = dictionaryService.listDictionaries();
            // key是 dictionary:+id
            // value json串
            for (DictionaryVo vo : list) {
                stringRedisTemplate.opsForValue().set(GlobalConstant.RedisPrefixKey.DICTIONARY_PREFIX+vo.getId().toString(), objectMapper.writeValueAsString(vo));
            }
            log.info("--------------------初始化字典表至缓存结束--------------------");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            // 删除锁
            stringRedisTemplate.delete(RedisLockEnum.LOCK_DICTIONARY.getKey());
        }
    }

}*/
