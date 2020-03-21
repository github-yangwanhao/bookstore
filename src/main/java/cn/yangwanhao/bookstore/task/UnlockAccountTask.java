package cn.yangwanhao.bookstore.task;

import cn.yangwanhao.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 解锁账户任务，每天凌晨执行
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/4 15:59
 */

@Component
@Slf4j
@Lazy(false)
public class UnlockAccountTask {

    @Resource
    private UserService userService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void unlockAccount() {
        log.info("解锁账户,清零密码输错次数");
        userService.unlockAccount();
    }

}
