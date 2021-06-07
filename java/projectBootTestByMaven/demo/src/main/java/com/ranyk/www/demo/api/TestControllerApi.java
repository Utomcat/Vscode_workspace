package com.ranyk.www.demo.api;

import javax.annotation.Resource;

import com.ranyk.www.demo.exception.CustomException;
import com.ranyk.www.demo.request.HouseReqest;
import com.ranyk.www.demo.service.HouseService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author: ranyk
 * @description: 测试请求Api 
 * @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestControllerApi {

    @Resource
    private HouseService houseService;

    /**
     * index 请求处理器
     * @return 返回字符串
     */
    @GetMapping("index")
    public String index(){
        log.error("{}", "项目启动,调用index请求处理器");
        return "Hello World!";
    }



    @PostMapping(value="getRoomInfo")
    public String getRoomInfo(@RequestBody HouseReqest houseRequest) {        
        try {
            return houseService.getHouseInfoByBuildId(houseRequest);
        } catch (CustomException e) {
            return "发生异常,异常信息: " + e.getMessage();
        }
    }


    @PostMapping(value="getRoomImgPath")
    public String getRoomImgPath(@RequestBody HouseReqest houseRequest) {
        return houseService.getRoomImgPath(houseRequest);
    }
    



    
    
}
