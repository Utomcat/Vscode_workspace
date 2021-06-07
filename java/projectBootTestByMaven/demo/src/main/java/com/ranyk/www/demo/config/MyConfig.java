package com.ranyk.www.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @CLASS_NAME: MyConfig.java
 * @description: 
 * 
 * @author ranYk
 * @version V1.0
 */
@Data
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MyConfig {
    
    @Value("${myConfig.roomUrl}")
    private String roomUrl;    

    @Value("${myConfig.roomImgUrl}")
    private String roomImgUrl;

}
