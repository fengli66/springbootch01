package com.lifeng.controller;

import com.lifeng.pojo.UserLogin;
import com.lifeng.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/userLogin")
public class UserController {
    @Resource
   private UserService userService;

    @RequestMapping("/test")
    public String test(Model model)
    {
        //查询所有用户信息
        System.out.println("ssss");
        List<UserLogin> userList = userService.findAll();
        model.addAttribute("users",userList);
        return "userinfo";
    }
}
