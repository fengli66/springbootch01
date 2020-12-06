package com.lifeng;

import com.lifeng.repository.UserRespository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * 入口类
 *
 * @author fengli
 * @version 1.0
 */
@SpringBootApplication      //项目启动注解
public class Springbootch01Application {
    @Resource
    private UserRespository userRespository;

    public static void main(String[] args) {
        //程序开始运行的方法
        SpringApplication.run(Springbootch01Application.class, args);

    }

}
