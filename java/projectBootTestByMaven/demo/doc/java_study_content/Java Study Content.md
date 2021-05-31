# `Java` 学习内容

[TOC]

## 一、`Java` 开发环境的安装

### 1）、 `JDK` 的下载、安装和配置

#### Ⅰ、`JDK` 的下载

> 从官网 [Oracle JDK 下载地址](https://www.oracle.com/java/technologies/javase-downloads.html) 中选择自选集需要下载的 `JDK` 版本,下载到本地指定的文件目录中

#### Ⅱ、`JDK` 的安装

> 双击上一步下载下来的 `JDK` 安装文件,安装到指定的目录中

#### Ⅲ、`JDK` 配置

> 配置系统环境变量, `Windows 10` 的设置路径为 设置 -> 系统 -> 关于 -> 右侧 相关设置 -> 高级系统设置 -> 环境变量 -> 系统变量 -> 选中 `Path` 变量 -> 编辑 -> 右侧 新建 选项 -> 将对应的 `JDK` 安装目录的 `bin` 目录路径粘贴到新建的环境变量中 -> 所有窗口均选择 确认 选项

#### Ⅳ、`JDK` 安装检测

> - `Win + R` 打开运行窗口
> - 输入 `cmd` 选项,打开命令行窗口
> - 输入 `java -version` 命令,如果出现对应的 `JDK` 版本则安装成功;若出现 `不是内部命令` 类似的内容,则安装或配置错误,需检测有关的内容是否正确

### 2）、第一个 `Java` 程序

1. 在指定的位置创建 `HelloWord.java` 的文件(新建一个文本文档 -> 重命名 -> `HelloWord.java`(需要勾选查看选项中的文件扩展名选项))
2. 在文件中输入如下的内容,并保存
3. 打开 `cmd` 命令行窗口,切换到对应文件存放的盘符,如切换到 `D` 盘,则输入 `d:` 即可
4. 进入到 `HelloWord.java` 文件所在的路径下,如切换到 `work\java\workspace` 目录,则执行命令 `cd work\java\workspace` 即可
5. 执行明亮 `javac HelloWord.java` (此时需要加上 `.java` 的文件后缀)
6. 等待执行完成后,没有异常会打印出类似 `D:\work\java\workspace>` 的内容则可以执行 `java HelloWord` (注意此时将没有对应的 `.java` 文件后缀)

- `HelloWord.java` 代码

```java
public class HelloWorld{
    public static void main(String[] args) {
        System.out.println("HelloWord");
    }    
}
```

## 二、`Java` 的基本语法

### 1）、`Java` 类的基本结构

#### Ⅰ、类的结构

```text
// 包路径,即当前类文件所在的包路径
package xxxx

// 引入的其他包的类
import java.xxxx

// 权限修饰关键字 类名
<public|(默认)|protected|private> className{
    
    //权限修饰关键字 静态 常量 数据类型 属性名;
    <public|(默认)|protected|private> [static] [final] dataType propertyName;

    //权限修饰关键字 静态|常量|同步 方法返回的数据类型 方法名(参数数据类型 参数名,...)
    <public|(默认)|protected|private> [static|final|synchronized|...] [void|...] methodName([dataType param1|...]){
        //TODO method body
    }

} 
```

- 示例

```java
// 当前对应的 Test.java 所在的包路径
package com.yjl.demo;

// 引入java.Langx 下的所有类/class
import java.lang.*;

// 定义的 Test 类/class
public class Test{

    //定义的 Test class 的属性 name 
    private String name;

    //定义的 Test class 的 main 方法
    public static void main(String[] args) {
        //main 方法需要做的事,下面的代码是让其输出对应的整数 1 的值
        System.out.println(Integer.value(1));
    }

}
```

#### Ⅱ、方法的结构

#### Ⅲ、数据类型

### 2）、基本数据类型

### 3）、基本结构

## 附录

### 1）、 `Windows` 操作系统的

#### Ⅰ、`cmd` 命令

1. `dir`: 显示当前文件夹下的内容
2. `cd 路径`: 进入到指定的文件夹下
3. `盘符名:` : 切换到指定的盘符中
