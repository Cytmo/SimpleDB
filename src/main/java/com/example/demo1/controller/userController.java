package com.example.demo1.controller;

import com.huawei.shade.com.alibaba.fastjson.JSONArray;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo1.service.*;

import java.sql.SQLException;
import java.util.Arrays;

@Controller
public class userController {
    @RequestMapping(path="/user",method = RequestMethod.GET)
    public String user_html(){

        return "forward:table_test_admin.html";
    }

    @RequestMapping(path = "/user",method = RequestMethod.POST)
    @ResponseBody
    public JSONArray queryDB(@RequestBody(required=false) JSONObject json_search) throws SQLException {

        JSONArray jsonArray = new JSONArray();
        System.out.println(json_search);
//        parseToSQL temp = new parseToSQL();
//        jsonArray = temp.parse0(json_search);  //查阅所有书籍
//        System.out.println(jsonArray);

        JSONObject json1=new JSONObject();
        json1.put("book_id","222");
        json1.put("book_name","333");
        json1.put("author","4444");
        json1.put("collection_number","5555");
        json1.put("existing_number","6666");
        json1.put("price","5555");
        json1.put("publisher","66666");
        json1.put("introduction","77777");
        jsonArray.add(json1);

        JSONObject json2=new JSONObject();
        json2.put("book_id","0000222");
        json2.put("book_name","0000333");
        json2.put("author","00004444");
        json2.put("collection_number","00005555");
        json2.put("existing_number","00006666");
        json2.put("price","0005555");
        json2.put("publisher","00066666");
        json2.put("introduction","0077777");

        jsonArray.add(json2);

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


    @RequestMapping(path = "/br",method = RequestMethod.POST) //borrow and return
    @ResponseBody
    public String br(@RequestBody(required=false) JSONObject jsonObject) throws SQLException {
        
        parseToSQL temp = new parseToSQL();
        String jsonArray = temp.parse(jsonObject);
        System.out.println(jsonArray);

        return jsonArray;

    }
}
