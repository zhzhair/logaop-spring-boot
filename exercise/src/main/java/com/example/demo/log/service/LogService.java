package com.example.demo.log.service;

import org.springframework.stereotype.Service;
import pers.zhzh.logspringboot.annotations.LogForTimeConsumer;
import pers.zhzh.logspringboot.constant.ResultType;

import java.util.Random;

@Service
public class LogService {

    public String sleep(String input){
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sleep" + input;
    }

    @LogForTimeConsumer(description = "有入参出参的方法记录日志",milliseconds = 2000L,resultNeed = true,resultType = ResultType.JSONOBJECT)
    public String logServiceTest(String input){
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"logServiceTest\":\"" + input + "\"}";
    }
}
