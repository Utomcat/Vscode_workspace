package com.ranyk.www.demo.study.multithreading.thread;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.*;

/**
 * @author ranyk
 * @description 线程类实现方式之继承 Thread 类
 * 
 * @date 2021-07-16
 * @version V1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Thread01 extends Thread {
    
    private AtomicInteger money;

    @Override
    public void run() {
        money.incrementAndGet();
    }

}
