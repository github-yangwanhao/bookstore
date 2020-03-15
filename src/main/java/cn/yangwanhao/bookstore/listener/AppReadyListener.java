package cn.yangwanhao.bookstore.listener;

import cn.yangwanhao.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 杨万浩
 * @description AppReadyListener类
 * @date 2020/3/14 20
 */
@Component
@Slf4j
public class AppReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("------------------------------项目启动成功------------------------------");
        log.info("------------------------------开始清理账户锁定------------------------------");
        userService.unlockAccountBeforeToday();
        log.info("------------------------------完成清理账户锁定------------------------------");
    }
}
