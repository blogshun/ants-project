//package com.ants.project.site.task;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @author liushun
// * @version 1.0
// * @Date 2016-03-07
// */
//@Component("testTask")
//public class TestTask {
//
//    private final Logger logger = LoggerFactory.getLogger("Task");
//
//    int kb = 1024;
//
//    @Scheduled(cron = "* * * * * *")
//    public void job() {
//        // 可使用内存
//        long totalMemory = Runtime.getRuntime().totalMemory() / kb;
//        // 剩余内存
//        long freeMemory = Runtime.getRuntime().freeMemory() / kb;
//        // 最大可使用内存
//        long maxMemory = Runtime.getRuntime().maxMemory() / kb;
//        logger.debug("任务进行中 可使用内存->{} 剩余内存->{} 最大可使用内存->{}", totalMemory, freeMemory, maxMemory);
//    }
//}
