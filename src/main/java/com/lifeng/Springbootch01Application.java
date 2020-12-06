package com.lifeng;

import com.lifeng.repository.UserRespository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.Resource;

/**
 * 入口类
 *
 * @author fengli
 * @version 1.0
 */
@SpringBootApplication      //项目启动注解
@ServletComponentScan   //：使用该注解后， Servlet Filter Listener 可以直接通过＠WebServlet @WebFilter、＠WebListener 自动注册，无须其他代码
public class Springbootch01Application {

    public static void main(String[] args) {
        //程序开始运行的方法
        SpringApplication.run(Springbootch01Application.class, args);

    }

}
