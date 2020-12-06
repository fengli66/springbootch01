package com.lifeng.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * @author fengli
 * @version 1.0
 * @date 2020/12/06
 */
//：用于将 个类声明为过滤器，该注解将会在应用部署时被容器处理，容器根据具体的属性配直将相应的类部署为过滤器 这
@WebFilter(filterName = "userLoginFilter",urlPatterns = "/*")
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------>>> init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------>>> doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("------>>> destory");
    }
}
