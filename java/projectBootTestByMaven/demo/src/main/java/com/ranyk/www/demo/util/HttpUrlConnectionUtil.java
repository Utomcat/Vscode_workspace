package com.ranyk.www.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

import com.ranyk.www.demo.enums.RequestMethodEnum;
import com.ranyk.www.demo.enums.ResponseCodeEnum;
import com.ranyk.www.demo.exception.CustomRunTimeException;

import lombok.extern.slf4j.Slf4j;

/**
 * @CLASS_NAME: HttpUrlConnectionUtil.java
 * @description: HttpUrlConnection 网络请求工具类
 * 
 * @author: ranYk
 * @version: V1.0
 */
@Slf4j
public class HttpUrlConnectionUtil {

    private HttpUrlConnectionUtil() {

    }

    /**
     * 使用 java原生 HttpURLConnection 发送 Get 请求
     * 
     * @param httpurl 请求 URL
     * @return 返回响应结果字符串,默认返回 未知响应! 字符串,其他返回正常的响应结果字符串
     */
    public static String doGet(String httpUrl) {
        // 定义连接对象
        HttpURLConnection connection = null;
        // 定义响应接收字符对象
        BufferedReader br = null;
        // 定义返回字符串
        String result = "未知响应!";
        try {
            // 获取 HttpURLConnection 连接对象
            connection = getConnection(RequestMethodEnum.GET, httpUrl, null);
            // 发送请求
            connection.connect();
            // 判断连接的响应状态,如果为 200 则响应正常
            if (ResponseCodeEnum.SUCCESS_CODE.getValue().equals(connection.getResponseCode())) {
                // 获取响应输入流字符对象
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                // 创建响应内容接收对象
                StringBuffer sbf = new StringBuffer();
                // 创建响应内容临时存放字符串对象
                String temp = null;
                // 循环从响应流字符对象中获取响应内容,将内容临时赋值给临时字符串对象
                while ((temp = br.readLine()) != null) {
                    // 将临时响应内容放进响应接收对象
                    sbf.append(temp);
                }
                // 将响应内容转为字符串赋给返回对象
                result = sbf.length() > 0 ? sbf.toString() : result;
            }
        }
        // 捕获异常
        catch (Exception exception) {
            log.error("{}", "发送指定URL时发生异常,异常信息如下: ");
            StackTraceElement[] statcks = exception.getStackTrace();
            for (StackTraceElement stack : statcks) {
                log.error("{}", stack.toString());
            }
        }
        // 最终关闭操作
        finally {
            // 关闭响应接收字符对象
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            // 关闭连接对象
            if (null != connection) {
                try {
                    connection.disconnect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        // 返回响应结果
        return result;
    }

    /**
     * 使用 java原生 HttpURLConnection 发送 Post 请求
     * 
     * @param httpUrl 请求 URL 地址
     * @param param   请求参数
     * @return 返回响应结果字符串,默认返回 未知响应! 字符串,其他返回正常的响应结果字符串
     */
    public static String sendPost(String httpUrl, Map<String, Object> param) {
        // 定义连接对象
        HttpURLConnection connection = null;
        // 定义响应接收字符对象
        BufferedReader br = null;
        // 定义返回字符串
        String result = "未知响应!";
        try {
            // 获取连接对象
            connection = getConnection(RequestMethodEnum.POST, httpUrl, param);
            // 通过连接对象获取一个输入流，向远程读取
            if (ResponseCodeEnum.SUCCESS_CODE.getValue().equals(connection.getResponseCode())) {
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                result = sbf.length() > 0 ? sbf.toString() : result;
            }
        } catch (Exception e) {
            log.error("{}", "发送指定URL时发生异常,异常信息如下: ");
            StackTraceElement[] statcks = e.getStackTrace();
            for (StackTraceElement stack : statcks) {
                log.error("{}", stack.toString());
            }
        } // 最终关闭操作
        finally {
            // 关闭响应接收字符对象
            if (null != br) {
                try {
                    br.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            // 关闭连接对象
            if (null != connection) {
                try {
                    connection.disconnect();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获得 HttpURLConnection 连接对象
     * 
     * @param method  请求方式枚举
     * @param httpUrl 请求 url
     * @param param   请求参数,没有请求参数传 null,如: get 方法没有参数可以传入空
     * @return 返回得到的 HttpURLConnection 连接对象
     * @throws CustomRunTimeException 抛出自定义运行时异常
     */
    private static HttpURLConnection getConnection(RequestMethodEnum method, String httpUrl, Map<String, Object> param)
            throws CustomRunTimeException {
        try {
            URL url = new URL(httpUrl);
            // 定义连接对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            switch (method) {
                case GET:
                    // 设置连接的请求方式 GET 方式
                    connection.setRequestMethod(RequestMethodEnum.GET.getValue());
                    // 设置请求连接超时时间,单位 毫秒
                    connection.setConnectTimeout(15000);
                    // 设置响应超时时间,单位 毫秒
                    connection.setReadTimeout(60000);
                    return connection;
                case POST:
                    // 设置连接请求方式
                    connection.setRequestMethod(RequestMethodEnum.POST.getValue());
                    // 设置连接主机服务器超时时间：15000毫秒
                    connection.setConnectTimeout(15000);
                    // 设置读取主机服务器返回数据超时时间：60000毫秒
                    connection.setReadTimeout(60000);
                    // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
                    connection.setDoOutput(true);
                    // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
                    connection.setDoInput(true);
                    // 遍历参数 Map 对象,将参数设置进请求中
                    for (Entry<String, Object> entity : param.entrySet()) {
                        connection.setRequestProperty(String.valueOf(entity.getKey()),
                                String.valueOf(entity.getValue()));
                    }
                    return connection;
                default:
                    throw new CustomRunTimeException("未知请求方法!");
            }
        } catch (Exception e) {
            throw new CustomRunTimeException(e);
        }

    }

}
