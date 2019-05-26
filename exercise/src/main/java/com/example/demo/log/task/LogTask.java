package com.example.demo.log.task;

import com.example.demo.log.service.LogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.zhzh.logspringboot.annotations.LogForTimeConsumer;

import javax.annotation.Resource;

@LogForTimeConsumer(description = "方法的日志输出", milliseconds = 1000L)
@Component
public class LogTask {
    @Resource
    private LogService logService;

    @LogForTimeConsumer(description = "每天7点到22点5秒钟执行一次")//记录方法执行时间
    @Scheduled(cron = "0/5 * 0-22 * * ?")//
    public void circle(){
        logService.sleep("每天7点到22点5秒钟执行一次");
    }

    @LogForTimeConsumer(description = "每天22点40分执行该定时任务",resultNeed = true)
//    @Scheduled(cron = "0 40 22 * * ?")//
    @Scheduled(cron = "0/5 * 0-22 * * ?")//
    public Object fixedTimes(){
        return logService.sleep("每天22点40分执行该定时任务");
    }
}
