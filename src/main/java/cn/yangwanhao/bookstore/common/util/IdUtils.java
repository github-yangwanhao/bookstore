package cn.yangwanhao.bookstore.common.util;

import cn.yangwanhao.bookstore.common.support.SnowFlakeIdWorker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/28 11:46
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdUtils {

    /**
     * Description: 获取UUID
     * @return 生成的UUID字符串
     * @author 杨万浩
     * @createDate 2019/10/17 11:57
     */
    public synchronized static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Description: 雪花算法获取id
     * @return id
     * @author 杨万浩
     * @createDate 2019/10/28 11:55
     */
    public static Long getSnowFlakeId() {
        SnowFlakeIdWorker worker = new SnowFlakeIdWorker(0, 0);
        return worker.getNextId();
    }

}
