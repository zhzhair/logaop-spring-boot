package com.example.demo.logprint.task;

import com.example.demo.config.annotation.LogForTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TaskTest {

    @LogForTask(description = "每天7点到23点5秒钟执行一次",milliseconds = 2000)
    @Scheduled(cron = "0/2 * 7-23 * * ?")
    public void circle(){
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @LogForTask(description = "每天22点40分执行该定时任务")
    @Scheduled(cron = "0 40 22 * * ?")
    public void fixedTimes(){
        try {
            Thread.sleep(new Random().nextInt(6000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
