package com.ranyk.www.demo.enums;

import lombok.Getter;

/**
 * @CLASS_NAME: RequestMethodEnum.java
 * @description: HttpUrlConnection 网络请求方式枚举类
 * 
 * @author: ranYk
 * @version: V1.0
 */
@Getter
public enum RequestMethodEnum {
    /**
     * GET 请求方式
     */
    GET("GET 请求方式", "GET"),
    /**
     * POST 请求方式
     */
    POST("POST 请求方式", "POST"),
    /**
     * HEAD 请求方式
     */
    HEAD("HEAD 请求方式", "HEAD"),
    /**
     * OPTIONS 请求方式
     */
    OPTIONS("OPTIONS 请求方式", "OPTIONS"),
    /**
     * PUT 请求方式
     */
    PUT("PUT 请求方式", "PUT"),
    /**
     * DELETE 请求方式
     */
    DELETE("DELETE 请求方式", "DELETE"),
    /**
     * TRACE 请求方式
     */
    TRACE("TRACE 请求方式", "TRACE");

    /**
     * 枚举名称
     */
    private final String name;
    /**
     * 枚举值
     */
    private final String value;

    /**
     * 枚举全参构造方法
     * 
     * @param name  枚举名称
     * @param value 枚举值
     */
    RequestMethodEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

}