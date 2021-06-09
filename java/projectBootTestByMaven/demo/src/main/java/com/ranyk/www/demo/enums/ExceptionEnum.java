package com.ranyk.www.demo.enums;

import lombok.Getter;

/**
 * @CLASS_NAME: ExceptionEnum.java
 * @description: 异常信息枚举
 * 
 * @author: ranYk
 * @version: V1.0
 */
@Getter
public enum ExceptionEnum{
    
    /**
     * 文件压缩异常头枚举
     */
    EXCEPTION_ZIP_HEAD("文件压缩异常头枚举","压缩文件时发生异常,异常信息为: ");

    /**
     * 枚举对象含义
     */
    private final String name;
    /**
     * 枚举对象值
     */
    private final String value;

    /**
     * 全参构造函数
     * 
     * @param name 枚举对象含义
     * @param value 枚举对象值
     */
    ExceptionEnum(String name, String value){
        this.name = name;
        this.value = value;
    }
}