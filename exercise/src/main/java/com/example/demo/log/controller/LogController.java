package com.example.demo.log.controller;

import com.example.demo.log.service.LogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.zhzh.logspringboot.annotations.LogForController;

import javax.annotation.Resource;

@RestController
public class LogController {
    @Resource
    private LogService logService;

    @LogForController
    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    public String testLog(String input){
        logService.sleep(input);
        return "testLog =========== " + input;
    }
}
