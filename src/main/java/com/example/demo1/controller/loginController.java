package com.example.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo1.model.users;

import java.util.List;

@Controller
public class loginController {
    @RequestMapping(path="/login",method = RequestMethod.GET)
    public String hello(){

        return "forward:login.html";
    }

    //接收login_test页面的字符数组
//    @RequestMapping(path = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public Object Receive(@RequestBody users user){  //Employee可以改为Object
////
////        System.out.println(user.getUser_name()+"\n"+user.getUser_password()+user.getIdentify()+user.getEmail());
//
//        System.out.println(user.getUser_name());
//        if(user.getUser_name().equals("jeremy")) {
//            return "{\"msg\":\"添加成功,恭喜你\"}";
//        }else
//        {
//            return "{\"msg\":\"添加失败,加油,再试一试\"}";
//        }
//
//    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String insertUserInfo(@RequestBody(required=false) users user){
        System.out.println(user);
        System.out.println(user.getUser_name());
        if(user.getUser_name().equals("jeremy")) {
            return "{\"msg\":\"添加成功,恭喜你\"}";
        }else
        {
            return "{\"msg\":\"添加失败,加油,再试一试\"}";
        }

    }
}



