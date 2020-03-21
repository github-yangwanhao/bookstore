package cn.yangwanhao.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author 杨万浩
 * @description ScheduledTaskConfig类
 * @date 2020/3/16 14
 */
@Configuration
public class ScheduledTaskConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(2);
        taskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }
}
