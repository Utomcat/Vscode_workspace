package com.ranyk.www.demo.exception;

/**
 * @CLASS_NAME: CustomRunTimeException.java
 * @decription: 自定义运行时异常类
 * 
 * @author ranYk
 * @version V1.0
 */
public class CustomRunTimeException extends RuntimeException {
    /**
     * 自定义异常,无参构造异常
     */
    public CustomRunTimeException() {
        super();
    }

    /**
     * 自定义异常,通过传入异常信息
     * 
     * @param msg 异常信息
     */
    public CustomRunTimeException(String msg) {
        super(msg);
    }

    /**
     * 自定义异常,通过传入异常的信息和异常原因
     * 
     * @param message 异常信息
     * @param cause   异常原因
     */
    public CustomRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 自定义异常,通过传入 异常原因
     * 
     * @param cause 异常的原因
     */
    public CustomRunTimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 自定义异常,通过传入的参数指定异常的信息头,信息内容,报错行，以及报错文件
     * 
     * @param exceptionHead 异常信息头
     * @param msg           异常信息
     * @param lineNum       异常发生行号行号
     * @param fileName      异常发生的文件
     */
    public CustomRunTimeException(String exceptionHead, String msg, int lineNum, String fileName) {
        super(exceptionHead + " " + msg + " 报错行为: " + lineNum + " 报错文件为: " + fileName);
    }
}
