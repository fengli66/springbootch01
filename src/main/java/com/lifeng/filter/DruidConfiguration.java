package com.lifeng.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

/**
 * 开启监控功能
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
@Configuration  //Spring 中有很多 XML 配直文件，文件中会配直很多 bean在类上添加 onfiguration ，大 可以理 为该类变成一个泡在 配直文件

public class DruidConfiguration {

    @Bean //等同于 XML 配直文件中的 bean＞配置
    public ServletRegistrationBean druidStatViewServle(){
        //ServletRegistrationBean
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单（存在共同时，deny优先与allow）
        //如果满足deny，就提示：Sorry，you are not permitted to view this page .servletRegistrationBean.addInitParemeter("deny","192.168.1.173");
        servletRegistrationBean.addInitParameter("deny","192.168.3.101");
        //登录查看信息的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 开启过滤功能
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
