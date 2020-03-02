package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/28 15:28
 */

public enum RedisLockEnum {

    /**
     * 初始化字典表的
     */
    LOCK_DICTIONARY("lock:key:dictionary", "1", 60L, TimeUnit.SECONDS)
    ;

    @Getter
    private String key;
    @Getter
    private String value;
    @Getter
    private Long time;
    @Getter
    private TimeUnit timeUnit;

    RedisLockEnum(String key, String value, Long time, TimeUnit timeUnit) {
        this.key = key;
        this.value = value;
        this.time = time;
        this.timeUnit = timeUnit;
    }

}
