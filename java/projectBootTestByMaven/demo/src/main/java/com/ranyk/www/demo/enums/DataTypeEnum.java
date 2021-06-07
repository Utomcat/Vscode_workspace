package com.ranyk.www.demo.enums;

import lombok.Getter;

/**
 * CLASS_NAME: DataTypeEnum<br/>
 * Description: 数据类型枚举<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Getter
public enum DataTypeEnum {

    /**
     * 基本类型 int 包装类 Integer 类型枚举
     */
    INTEGER_ENUM("Integer 类型枚举", "java.lang.Integer");

    /**
     * 枚举类型名称
     */
    private final String name;
    /**
     * 枚举类型值,形如: Integer 为 java.lang.Integer
     */
    private final String value;

    /**
     * 全参构造函数
     *
     * @param name  枚举类型名称
     * @param value 枚举类型值
     */
    DataTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
