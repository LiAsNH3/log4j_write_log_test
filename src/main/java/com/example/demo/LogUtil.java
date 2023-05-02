package com.example.demo;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

public class LogUtil {
    private static final LoggerContext test;

    static {
        // 读取log4j的配置
        test = new LoggerContext("test");
        // 使用改配置可以全部由log4j自动实现分id打印日志文件
//        File file = new File("src/main/resources/log4j2.xml");
        File file = new File("src/main/resources/strLookupXml.xml");
        test.setConfigLocation(file.toURI());
    }

    public static Logger getLogger(String uuId) {
        return test.getLogger(uuId);
    }

}
