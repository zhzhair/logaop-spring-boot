package com.example.demo.logprint.controller;

import com.example.demo.config.annotation.NotAutoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口文档地址：http://localhost:8080/swagger-ui.html#!/
 */
@Api(description = "接口日志输出案例")
@RestController
public class LogController {

    @ApiOperation(value = "默认自动打印日志", notes = "输出日志案例")
    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    public String testLog(String input){
        return "testLog =========== " + input;
    }

    @ApiOperation(value = "取消打印日志", notes = "接口排除打印日志案例：比如输入输出参数为不需要打印日志的图片、视频或其他大文件")
    @NotAutoLog
    @RequestMapping(value = "/testNotAutoLog",method = {RequestMethod.GET})
    public String testNotAutoLog(String input){
        return "testNotAutoLog =========== " + input;
    }
}
