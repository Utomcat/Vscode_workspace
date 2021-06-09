package com.ranyk.www.demo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ranyk.www.demo.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileTestUtil {
    /**
     * 定义的字节数组大小
     */
    private final static int BUFFER_SIZE = 2 * 1024;

    private FileTestUtil() {

    }

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
     * 
     * @param is InputStream 输入流对象
     * @return 获取到的输入流对象的 byte[] 数组
     */
    public static byte[] getContent(InputStream is) {
        var baos = new ByteArrayOutputStream();
        try {
            var buffer = new byte[1024];
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
     * 
     * @param bais 文件内容 ByteArrayInputStream 对象
     * @param fos  需要将内容保存到的文件输出流 FileOutputStream 对象
     */
    public static void writeFile(ByteArrayInputStream bais, FileOutputStream fos) {
        try {
            int temp;
            var bt = new byte[1024 * 10];
            while ((temp = bais.read(bt)) != -1) {
                fos.write(bt, 0, temp);
            }
        } catch (Exception e) {
            log.error("在写入文件时发生异常,异常信息为: {}", e.getMessage());
        } finally {
            try {
                bais.close();
            } catch (Exception e) {
                log.error("在写入文件后关闭 ByteArrayInputStream 对象时,发生异常,异常信息为:  {}", e.getMessage());
            }
            try {
                fos.close();
            } catch (Exception e) {
                log.error("在写入文件后关闭 FileOutputStream 对象时,发生异常,异常信息为:  {}", e.getMessage());
            }

        }
    }

    /**
     * 写文件信息
     * 
     * @param source
     * @param path
     */
    public static void writeFile(byte[] source, String path) {
        try {
            var bais = new ByteArrayInputStream(source);
            var fos = new FileOutputStream(new File(path));
            writeFile(bais, fos);
        } catch (Exception e) {
            log.error("将对应的文件写入指定位置的文件中发生异常,异常信息为: {}", e.getMessage());
        }
    }

    /**
     * 压缩文件
     * 
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流对象
     * @param keepDirStructure 是否需要保持原有的文件结构
     * @throws CustomException 出现异常抛出自定义的异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean keepDirStructure) throws CustomException {
        try (var zos = new ZipOutputStream(out)) {
            var sourceFile = new File(srcDir);
            if (!sourceFile.exists() && sourceFile.createNewFile()) {
                log.error("文件不存在,并进行创建成功!");
            }
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
        } catch (Exception e) {
                    throw new CustomException("压缩文件时发生异常,异常信息为: "  + e.getMessage() + " 报错行为: " + e.getStackTrace()[0].getLineNumber() + " 报错文件为: " + e.getStackTrace()[0].getFileName());
        }
    }

    /**
     * 压缩多个文件
     * 
     * @param srcFile 需要压缩的文件对象 List 集合
     * @param out     压缩文件输出流对象
     * @throws CustomException 出现异常,抛出自定义异常
     */
    public static void toZip(List<File> srcFile, OutputStream out) throws CustomException {
        try (var zos = new ZipOutputStream(out)) {
            for (File file : srcFile) {
                var buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(file.getName()));
                int len;
                var fis = new FileInputStream(file);
                while ((len = fis.read()) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            throw new CustomException("压缩文件时发生异常,异常信息为: " + e.getMessage() + " 报错行为: "
                    + e.getStackTrace()[0].getLineNumber() + " 报错文件为: " + e.getStackTrace()[0].getFileName());
        }
    }

    /**
     * 
     * 压缩文件方法
     * 
     * @param file             需要压缩的文件对象,可以是一个文件目录,也可以是单个具体的文件
     * @param zos              压缩输出流对象
     * @param name             压缩的实体对象名
     * @param keepDirStructure 当存在多级文件目录时,是否需要保留文件结构
     * @throws CustomException 出现异常时,抛出有关异常
     */
    public static void compress(File file, ZipOutputStream zos, String name, boolean keepDirStructure)
            throws CustomException {

        // 2. 判断传入的文件对象是文件,是文件则直接进行压缩
        if (file.isFile()) {
            compressFile(file, zos, name);
        }
        // 3. 当传入的文件对象不是文件,则需进行遍历传入的文件夹,对其进行文件夹压缩
        else {
            compressDir(file, zos, name, keepDirStructure);
        }

    }

    /**
     * 压缩文件
     * 
     * @param file 需要压缩的文件,只能是文件,不能是文件夹
     * @param zos  压缩文件输出流对象
     * @param name 压缩实体的名称
     * @throws CustomException 抛出自定义异常
     */
    private static void compressFile(File file, ZipOutputStream zos, String name) throws CustomException {
        // 1. 判断传入的File对象是否是文件
        if (!file.isFile()) {
            throw new CustomException("进行文件压缩时,传入的文件对象不是一个文件!");
        }
        // 2. 创建一个byte数组,用于后面的循环中每次获取文件中内容的大小
        var buf = new byte[BUFFER_SIZE];
        // 3. 获取文件的输入流对象
        try (var fis = new FileInputStream(file)) {
            // 4. 向文件压缩输出流中添加一个压缩实体对象
            zos.putNextEntry(new ZipEntry(name));
            // 5. 声明单次读取文件内容的长度变量
            int len;
            // 6. 复制文件到压缩输出流中
            while ((len = fis.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // 7. 关闭压缩实体对象
            zos.closeEntry();
        } catch (Exception e) {
            log.error("直接压缩文件失败,发生的异常信息为: {} ,报错行数为： {}", e.getMessage(), e.getStackTrace()[0].getLineNumber());
            throw new CustomException("压缩文件时发生异常!");
        }
    }

    /**
     * 压缩目录
     * 
     * @param file             需要压缩的文件目录路径,必须是文件目录
     * @param zos              压缩文件输出流对象
     * @param name             压缩的实体的名称,如果是目录则是路径
     * @param keepDirStructure 是否保留原文件结构
     * @throws CustomException 抛出自定义异常
     */
    private static void compressDir(File file, ZipOutputStream zos, String name, boolean keepDirStructure)
            throws CustomException {
        // 1. 判断传入的File对象是否是文件夹
        if (!file.isDirectory()) {
            throw new CustomException("进行文件夹压缩时,传入的文件对象不是一个文件夹!");
        }
        // 2. 获取传入的文件目录下的文件对象数组
        File[] files = file.listFiles();
        // 3. 进行递归调用,以压缩文件目录下的所有文件
        try {
            // 4. 判断获取的文件对象数组是否存在,不存在则进行空文件目录的压缩
            if ((boolean) ObjectHandler.objectIsEmpty(files)) {
                // 5. 判断是否需要进行原文件结构保留
                if (keepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            }
            // 6. 存在文件进行压缩
            else {
                // 7. 进行遍历文件对象数组,递归调用压缩方法
                for (File entity : files) {
                    // 7.1。判断是否需要进行原文件结构的压缩,
                    if (keepDirStructure) {
                        compress(entity, zos, name + File.pathSeparator + entity.getName(), keepDirStructure);
                    }
                    // 7.2. 不需要进行原文件结构的压缩,不保留原文件结构就是将所有文件直接压缩至根目录下
                    else {
                        compress(entity, zos, entity.getName(), keepDirStructure);
                    }
                }
            }
        } catch (Exception e) {
            log.error("压缩文件目录失败,发生的异常信息为: {} ,报错行数为： {}", e.getMessage(), e.getStackTrace()[0].getLineNumber());
            throw new CustomException("压缩文件时发生异常!");
        }
    }

}
