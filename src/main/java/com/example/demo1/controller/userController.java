package com.example.demo1.controller;

import com.example.demo1.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户信息控制器类
 * @author pan_junbiao
 **/
@Controller
//@RequestMapping("/user")
public class userController
{
    /**
     * 获取用户信息
     */
    @RequestMapping("/user")
    public String getUser(Model model)
    {
        //创建用户信息
        User user = new User();
        user.setId(1);
        user.setUserName("pan_jubilation的博客");
        user.setBlogUrl("https://blog.csdn.net/pan_junbiao");
        user.setBlogInfo("您好，欢迎访问 pan_jubilation的博客");
        user.setRole("admin");

        //将用户信息保存到Model对象中
        model.addAttribute("user",user);

        //返回页面
        return "/hello";
    }
}