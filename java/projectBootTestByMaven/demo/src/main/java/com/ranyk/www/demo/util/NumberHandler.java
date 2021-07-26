package com.ranyk.www.demo.util;

import java.util.regex.Pattern;

/**
 * @author ranYk
 * @description 数字处理工具类
 * 
 * @date 201-07-26
 * @version V1.0
 */
public class NumberHandler {

    /**
     * 整数匹配正则表达式
     */
    private static final String INTEGER_REGEX = "^[-\\+]?[\\d]*$";

    /**
     * 私有化工具类的无参构造函数
     */
    private NumberHandler(){

    }


    /**
     * 判断传入的字符串是否为数字
     * 
     * @param str 需要判断的字符串
     * @return 如果是字符串则返回 true; 否则返回 false; 如: str = "123456", 应该返回 true; 如果 str = "-123456" 应该也返回 true; 如果 str ="这是一个数字1234" 该返回 false;
     */
    public static boolean isNumber(String str) {
        var pattern = Pattern.compile(INTEGER_REGEX);
        var isNum = pattern.matcher(str);
        return isNum.matches();
    }

}
