package com.example.demo1.controller;

import com.example.demo1.model.test_book;
import com.example.demo1.model.users;
import com.huawei.shade.com.alibaba.fastjson.JSONArray;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class userController {
    @RequestMapping(path="/user",method = RequestMethod.GET)
    public String user_html(){

        return "forward:test3.html";
    }

    @RequestMapping(path = "/queryDB",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray queryDB(@RequestBody(required=false) String str){

        JSONArray jsonArray = new JSONArray();

        JSONObject book1=new JSONObject();
        book1.put("id","111");
        book1.put("user_id","111333");
        book1.put("pwd","这本书很棒");

        JSONObject book2=new JSONObject();
        book2.put("id","222");
        book2.put("user_id","222");
        book2.put("pwd","大家加油!");

        JSONObject book3=new JSONObject();
        book3.put("id","333");
        book3.put("user_id","333444555");
        book3.put("pwd","我爱华为");

        jsonArray.add(book1);
        jsonArray.add(book2);
        jsonArray.add(book3);
        System.out.println(jsonArray);
        return jsonArray;

    }
}
