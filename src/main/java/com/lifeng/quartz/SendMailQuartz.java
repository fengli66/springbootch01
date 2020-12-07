package com.lifeng.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器类
 * @author fengli
 * @date 2020/12/07
 * @version 1.0
 *
 */
@Component
@Configurable
@EnableScheduling  //来开启对计划任务的支持
public class SendMailQuartz {
    //日志对象
    private static final Logger logger= LogManager.getLogger(SendMailQuartz.class);

    //每五秒执行一次  格式： ［秒］ ［分］ ［小时］ ［日］ ［月］ ［周］ ［年］
    //注解为定时任务，在 cron 表达式里写执行的时机
    @Scheduled(cron = "*/5 * *  * * *")
    public void reporCurrentByCron(){
        logger.info("定时器运行了！！！！");
    }
}
