package com.ranyk.www.demo.enums;

import lombok.Getter;

/**
 * CLASS_NAME: MethodNameEnum<br/>
 * Description: 方法名枚举类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Getter
public enum MethodNameEnum {

    /**
     * set 方法
     */
    SET_METHOD("属性设置 set 方法", "set"),
    /**
     * get 方法
     */
    GET_METHOD("属性获取 get 方法", "get");

    /**
     * 方法名枚举对象含义
     */
    private final String name;
    /**
     * 方法名枚举对象值属性
     */
    private final String value;

    /**
     * 全参构造函数
     *
     * @param name  方法名枚举对象含义
     * @param value 方法名枚举对象值
     */
    MethodNameEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
