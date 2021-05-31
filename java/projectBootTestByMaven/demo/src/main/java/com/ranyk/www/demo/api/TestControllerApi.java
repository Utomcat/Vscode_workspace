package com.ranyk.www.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ranyk
 * @description: 测试请求Api 
 * @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestControllerApi {

    /**
     * index 请求处理器
     * @return 返回字符串
     */
    @GetMapping("index")
    public String index(){
        log.error("{}", "项目启动,调用index请求处理器");
        return "Hello World!";
    }
    
}
