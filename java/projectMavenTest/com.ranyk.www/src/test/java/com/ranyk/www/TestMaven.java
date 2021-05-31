package com.ranyk.www;

import java.util.List;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestMaven{

    @Test
    public void test0(){
        List<Integer> list =  Lists.asList(Integer.valueOf("1"),new Integer[0]);
        log.info("{}", list);
    }

}