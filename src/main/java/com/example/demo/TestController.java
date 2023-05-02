package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {
    private static final ThreadPoolExecutor pool = new OverrideThreadPoolExecutor(2, 5,
            1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    @PostMapping(value = "/execute")
    public List<String> execute(@RequestParam Integer a) {
        log.info("begin to execute task, input param is : {}", a);
        List<String> uuids = new ArrayList<>();
        if (a > 0) {
            for (int i = 0; i < a; i++) {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                pool.submit(new Task("test" + i, uuid));
                uuids.add(uuid);
            }
        }
        log.info("end execute tasks");
        return uuids;

    }

    @GetMapping(value = "/query/{taskId}")
    public List<String> getLog(@PathVariable("taskId") String taskId) {
        log.info("query task log, taskId is: {}", taskId);
        try {
            File file = new File("D:\\code\\restful\\logs\\" + taskId + ".log");
            return FileUtils.readLines(file, "UTF-8");
        } catch (Exception e) {
            log.error("while read file, error : ", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
