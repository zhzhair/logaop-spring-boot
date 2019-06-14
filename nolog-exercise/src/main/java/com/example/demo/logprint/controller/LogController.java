package com.example.demo.logprint.controller;

import com.example.demo.config.annotation.NotAutoLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    public String testLog(String input){
        return "testLog =========== " + input;
    }

    @NotAutoLog
    @RequestMapping(value = "/testNotAutoLog",method = {RequestMethod.GET})
    public String testNotAutoLog(String input){
        return "testNotAutoLog =========== " + input;
    }
}
