package com.example.demo1.controller;

import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo1.model.users;
import com.example.demo1.service.*;

import java.sql.SQLException;

@Controller
public class loginController {
    @RequestMapping(path="/login",method = RequestMethod.GET)
    public String login_html(){

        return "forward:login_test.html";
    }


    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject insertUserInfo(@RequestBody(required=false) users user) throws SQLException {
        System.out.println(user);
        System.out.println(user.getUser_name());
        JSONObject json = new JSONObject();


        json.put("id","-1");
        json.put("user_id",user.getUser_name());
        json.put("pwd",user.getUser_password());
        System.out.println(user.getUser_name()+"    "+user.getUser_password());
        parseToSQL temp = new parseToSQL();
        String ret = temp.parse(json);
        JSONObject result = new JSONObject();
        result.put("msg",ret);
        System.out.println(result);
        return result;

    }
}



