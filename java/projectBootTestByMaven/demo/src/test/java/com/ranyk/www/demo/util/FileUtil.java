package com.ranyk.www.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FileUtil {
    


    /**
     * 获取 File 文件对象内容的 byte[] 数组
     * 
     * @param file File 文件对象
     * @return 获取到的文件类容 byte[] 数组
     */
    public static byte[] getContent(File file) {
        try {
            return getContent(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }


    /**
     * 获取对应的文件输入流的类容
     * @param is InputStream 输入流对象
     * @return 获取到的输入流对象的 byte[] 数组
     */
    public static byte[] getContent(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                int len = is.read(buffer);
                if (len == -1) {
                    break;
                }
                baos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }


    /**
     * 将对应 ByteArrayInputStream 对象,写入指定的文件中
     * @param bais 文件内容 ByteArrayInputStream 对象
     * @param fos  需要将内容保存到的文件输出流 FileOutputStream 对象
     */
    public static void writeFile(ByteArrayInputStream bais, FileOutputStream fos) {
        try {
            int temp;
            byte[] bt = new byte[1024 * 10];
            while ((temp = bais.read(bt)) != -1) {
                fos.write(bt, 0, temp);
            }
        } catch(Exception e) {
            log.error("在写入文件时发生异常,异常信息为: {}", e.getMessage());
        } finally {
            try {
                bais.close();
            } catch(Exception e){
                log.error("在写入文件后关闭 ByteArrayInputStream 对象时,发生异常,异常信息为:  {}", e.getMessage());
            }
            try {
                fos.close();
            } catch (Exception e) {
                log.error("在写入文件后关闭 FileOutputStream 对象时,发生异常,异常信息为:  {}", e.getMessage());
            }
            
        }
    }


    public static void writeFile(byte[] source, String path) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(source);
            FileOutputStream fos = new FileOutputStream(new File(path));
            writeFile(bais, fos);
        } catch (Exception e) {
            log.error("将对应的文件写入指定位置的文件中发生异常,异常信息为: {}", e.getMessage());
        }
    }
    
}
