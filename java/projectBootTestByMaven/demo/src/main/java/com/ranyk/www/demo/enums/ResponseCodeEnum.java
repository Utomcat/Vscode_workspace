package com.ranyk.www.demo.enums;

import lombok.Getter;

/**
 * @CLASS_NAME: HttpUtil.java
 * @description: 网络请求工具类
 * 
 * @author: ranYk
 * @version: V1.0
 */
@Getter
public enum ResponseCodeEnum {

    /**
     * 请求响应成功状态码
     */
    SUCCESS_CODE("成功状态码",200),
    /**
     * 服务器响应错误码
     */
    SERVER_RESPONSE_ERROR("服务器响应错误码",500);
    
    /**
     * 方法名枚举对象含义
     */
    private final String name;
    /**
     * 方法名枚举对象值属性
     */
    private final Integer value;

    /**
     * 全参构造函数
     *
     * @param name  方法名枚举对象含义
     * @param value 方法名枚举对象值
     */
    ResponseCodeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }


}
