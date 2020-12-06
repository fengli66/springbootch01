package com.lifeng.listener;

import com.lifeng.pojo.UserLogin;
import com.lifeng.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * 监听器 监听对象的创建与销毁
 * @author fengli
 * @date 2020/12/06
 * @version 1.0
 */
@WebListener //：用于将一个类声明为监听器
public class UserLoginListener implements ServletContextListener {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER="ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 上下文初始化");
        //查询数据库所有的用户
        List<UserLogin> userLogins = userService.findAll();
        //清除缓存中的用户
        redisTemplate.delete(ALL_USER);
        //将数据存放到redis中
        redisTemplate.opsForList().leftPushAll(ALL_USER,userLogins);
        //在真实项目中需要注释掉，查询所有的用户数据
        List<UserLogin> userLoginList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        System.out.println("缓存中的目前用户数有："+userLoginList.size()+" 人");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 上下文销毁");
    }
}
