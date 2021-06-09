package com.ranyk.www.demo.enums;

import org.springframework.util.StringUtils;

import lombok.Getter;

/**
 * @CLASS_NAME: CompressedSuffixEnum.java
 * @description: 压缩文件后缀枚举类
 * @author ranYk
 * @version V1.0
 */
@Getter
public enum CompressedSuffixEnum {

    /**
     * zip压缩格式后缀
     */
    ZIP("ZIP格式","zip"),
    /**
     * rar压缩格式后缀
     */
    RAR("RAR格式","rar");

    /**
     * 枚举对象的含义
     */
    private final String name;
    /**
     * 枚举对象的值
     */
    private final String value;


    /**
     * 全参构造函数
     * 
     * @param name 枚举对象的含义
     * @param value 枚举对象的值
     */
    CompressedSuffixEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 判断传入的文件是否是压缩文件格式
     * 
     * @param fileName 需要判断的文件名
     * @return 如果是压缩文件,则返回 true; 否则返回 false; 默认返回 false; 当传入的文件名称为空时也返回 false;
     */
    public static boolean judgeZipFileFormat(String fileName) {
        if(!StringUtils.hasText(fileName)){
            return false;
        }
        for(CompressedSuffixEnum value : CompressedSuffixEnum.values()){
            if(fileName.contains(value.value)){
                return true;
            }
        }
        return false;
    }

}
