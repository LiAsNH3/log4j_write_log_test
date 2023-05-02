package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import static com.example.demo.Constant.TASK_ID;
import static com.example.demo.Constant.THREAD_CONTEXT_KEY;

@Slf4j
public class Task implements Runnable{
    private final String name;
    private final String uuid;
    public Task(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }
    @Override
    public void run() {
        ThreadContext.put(THREAD_CONTEXT_KEY, uuid);
        ThreadContext.put(TASK_ID, uuid);
        Logger logger = LogUtil.getLogger(uuid);
        log.info("begin to execute task: {}", name);
        logger.info("begin to execute task: {}", name);
        try {
            Thread.sleep(10000);
            int sum = 0;
            for (int  i = 0; i < 100000; i++) {
                sum += i;
            }
            logger.info("summ value is: {}", sum);
        } catch (InterruptedException e) {
            log.error("while sleep throw InterruptedException");
        }
        log.info("end execute task: {}", name);
        logger.info("end execute task: {}", name);
    }
}
