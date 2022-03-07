package com.example.demo1.controller;

import com.example.demo1.model.test_book;
import com.example.demo1.model.users;
import com.huawei.shade.com.alibaba.fastjson.JSON;
import com.huawei.shade.com.alibaba.fastjson.JSONArray;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo1.service.*;

import java.sql.SQLException;

@Controller
public class userController {
    @RequestMapping(path="/user",method = RequestMethod.GET)
    public String user_html(){

        return "forward:test3.html";
    }

    @RequestMapping(path = "/queryDB",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray queryDB(@RequestBody(required=false) String str) throws SQLException {

        JSONArray jsonArray = new JSONArray();
        JSONObject tempJSON = new JSONObject();
        tempJSON.put("id","0");
        tempJSON.put("kind","0");
        parseToSQL temp = new parseToSQL();
        jsonArray = temp.parse0(tempJSON);
        System.out.println(jsonArray);

        return jsonArray;

    }


    @RequestMapping(path = "/queryRecords",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray queryRecords(@RequestBody(required=false) String str) throws SQLException {

        parseToSQL temp = new parseToSQL();
        //test
//        String json1 = "{\"id\":1,\"userID\":\"11\",\"objectID\":\"1212\",\"number\":\"+10\"}"; //测试用户借还操作
//        JSONObject jsonObj1 =  JSON.parseObject(json1);
//        temp.parse(jsonObj1);
        //test
        JSONArray jsonArray = new JSONArray();
        JSONObject tempJSON = new JSONObject();

        int id = Integer.parseInt(temp.user_id);
        tempJSON.put("id","5");
        if(id == 0) tempJSON.put("kind","0");
        else tempJSON.put("kind","1");
        jsonArray = temp.parse5(tempJSON);
        System.out.println(jsonArray);

        return jsonArray;

    }
}
