package com.lifeng.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器 监听对象的创建与销毁
 * @author fengli
 * @date 2020/12/06
 * @version 1.0
 */
@WebListener //：用于将一个类声明为监听器
public class UserLoginListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 上下文销毁");
    }
}
