package com.ranyk.www.demo.exception;

/**
 * @CLASS_NAME: CustomException.java
 * @decription: 自定义异常类
 * 
 * @author ranYk
 * @version V1.0
 */
public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(String message, Throwable cause) {

        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

}
