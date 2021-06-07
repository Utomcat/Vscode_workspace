package com.ranyk.www.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @class_name: HouseReqest.java
 * @description: 房屋请求参数
 * 
 * @author ranYk
 * @version V1.0
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
 public class HouseReqest {
    
    /**
     * 房屋所属楼栋ID
     */
    private String buildId;


}
