package com.example.demo;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

import static com.example.demo.Constant.TASK_ID;

@Plugin(name = "thread", category = StrLookup.CATEGORY)
public class ThreadLookup implements StrLookup {

    @Override
    public String lookup(String s) {
        return ThreadContext.get(TASK_ID);
    }

    @Override
    public String lookup(LogEvent logEvent, String s) {
        // 与使用strLookupXml.xml中的配置搭配使用，可以实现不同的id写入到不同文件
        Object value = logEvent.getContextData().getValue(TASK_ID);
        String s2 =  value == null ? "main" : value.toString();
        // 此处也可以用id来获取日志记录器，用该id区分不同分日志记录
        return s2;
    }
}
