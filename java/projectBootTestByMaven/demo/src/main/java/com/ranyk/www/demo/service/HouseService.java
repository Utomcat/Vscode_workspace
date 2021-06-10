package com.ranyk.www.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;
import com.ranyk.www.demo.config.MyConfig;
import com.ranyk.www.demo.exception.CustomException;
import com.ranyk.www.demo.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @CLASS_NAME: HouseService.java
 * @description: 房屋业务类
 * 
 * @author ranYk
 * @version V1.0
 */
@Slf4j
@Service
public class HouseService {

    @Autowired
    private MyConfig myConfig;

    /**
     * 获取房屋信息业务,模拟金禾接口的业务实现
     * 
     * @param houseRequest 请求参数封装对象
     * @return 返回获取到的房屋信息字符串
     * @throws CustomException 抛出自定义异常
     */
    public String getHouseInfoByBuildId(String buildId) throws CustomException {
        log.info("获取到的请求对象为 ==> {}", JSON.toJSONString(buildId));
        log.info("获取到的房屋请求返回的字符串的值为:  {}", myConfig.getRoomUrl());
        File roomInfo = null;
        try {
            roomInfo = ResourceUtils.getFile(myConfig.getRoomUrl());
        } catch (FileNotFoundException e1) {
            throw new CustomException("获取的文件不存在!");
        }
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(roomInfo), "gbk"))) {
            var sbf = new StringBuilder();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                sbf.append(temp);
            }
            return getStr(sbf.toString());
        } catch (IOException e) {
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * 获取房屋图片信息地址,模拟金禾接口的业务实现
     * 
     * @param houseRequest 请求参数封装对象
     * @return 返回对应存放文件的地址字符串
     */
    public String getRoomImgPath(String buildId) throws CustomException {
        log.info("获取到的请求对象为 ==> {}", JSON.toJSONString(buildId));
        log.info("获取到的房屋请求返回的字符串的值为:  {}", myConfig.getRoomImgUrl());
        File readFile = null;
        try {
            readFile = ResourceUtils.getFile(myConfig.getRoomImgUrl());
        } catch (FileNotFoundException e) {
            log.error("获取的文件不存在!");
            throw new CustomException(e.getMessage());
        }
        String zipPath = "E:\\gxftp\\" + LocalDateTime.now().getNano() + ".zip";
        var zipFile = new File(zipPath);
        FileUtil.compressSingleFile(readFile, zipFile);
        log.info("压缩文件的路径为: {}", zipFile.getAbsolutePath());
        return getResultPathStrForJh(zipFile.getAbsolutePath());
    }

    /**
     * 获取操作系统的字符集的字符串
     * 
     * @param str 需要进行字符集转换的原字符串
     * @return 返回转换后的字符串
     * @throws CustomException 抛出自定义异常
     */
    private String getStr(String str) throws CustomException {
        byte[] result = str.getBytes();
        try {
            return new String(result, System.getProperty("file.encoding"));
        } catch (UnsupportedEncodingException e) {
            throw new CustomException("装换字符串字符集发生异常,异常信息为: " + e.getMessage());
        }
    }

    /**
     * 压缩文件地址转换方法,模拟金禾的返回结果
     * 
     * @param path 压缩包原路径
     * @return 返回转换后的地址
     */
    private String getResultPathStrForJh(String path) {
        var sb = new StringBuilder("<string xmlns=\"http://tempuri.org/\">");
        sb.append(path.replace("E:\\gxftp\\", "ftp://172.16.24.225/")).append("</string>");
        return sb.toString();
    }

}
