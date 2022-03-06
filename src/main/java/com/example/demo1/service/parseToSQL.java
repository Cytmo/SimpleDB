package com.example.demo1.service;

import com.huawei.shade.com.alibaba.fastjson.JSON;
import com.huawei.shade.com.alibaba.fastjson.JSONArray;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;



public class parseToSQL {
    private Connection dbConnection = null;
    static dbConn conn;
    public parseToSQL() throws SQLException {
        conn = new dbConn();
        conn.createConnection();
        dbConnection =  conn.dbConnection;

    }



    public String parse(JSONObject jsonObj) throws SQLException {
        String result = null;
        int kind = jsonObj.getIntValue("id");
        switch (kind){
            case -2:
                result = String.valueOf(parse_1(jsonObj,false)); // 注册
                break;
            case -1:
                result = String.valueOf(parse_1(jsonObj,true));  //登录
                break;
            case 0:
                parse0(jsonObj);
                break;
            case 1:
                result = parse1(jsonObj);
                break;
            case 2:
                result = parse2(jsonObj);
                break;
            case 3:
                result = String.valueOf(parse3(jsonObj));
                break;
            default:
                System.out.println("Wrong kind of Operation "+ kind);
                break;
        }

        return (String) result;
    }

    public int closeConnection(){
        conn.closeConnection();
        return 0;
    }
    public int parse_1(JSONObject jsonObj,boolean if_login) throws SQLException {
        String id = jsonObj.getString("user_id");
        String pwd = jsonObj.getString("pwd");
        String SQLCmd = ";";
        int result = 2;
        if(if_login){
            SQLCmd = "SELECT * FROM users WHERE user_name = " +"'"+id+"'" +";";
            System.out.println(SQLCmd);
            result = conn.LoginDB(SQLCmd,pwd);
            if(result == 0){
                System.out.println("管理员登陆成功");
                return 0;
            }
            else if(result == 1){
                System.out.println("用户登陆成功");
                return 1;
            }else{
                System.out.println("登陆失败");
                return 2;
            }
        }else{
            SQLCmd = "INSERT INTO users VALUES(" + "'"+id+"'"+","+"'"+pwd+"'"+",'1',' ' );";
            System.out.println(SQLCmd);
            result = conn.insertToDB(SQLCmd);
            if(result==0) System.out.println("新用户注册成功，id = "+id);
            else{
                System.out.println("注册失败，用户已存在, id = "+ id );
            }
            return 0;
        }

    }
    public JSONArray parse0(JSONObject jsonObj) throws SQLException {
        int kind = jsonObj.getIntValue("kind");
        String dbname = "books";
        boolean if_books = true;
        if(kind==1){
            dbname = "papers";
            if_books = false;
        }
        String SQLCmd = "SELECT * FROM "+ dbname+";";
        conn.QueryDB(SQLCmd,if_books);
        return conn.queryResultReturned;
    }

    public String parse1(JSONObject jsonObj) throws SQLException {
        String userid = jsonObj.getString("userID");
        String objectID = jsonObj.getString("objectID");
        String number = jsonObj.getString("number");
        String dbName = "books";
        return updateExisting(objectID, dbName, Integer.parseInt(number));
    }
    /*
    管理员
    1-增加减少已有文献数量--------修改数量按钮
    ● 操作id "id:2"
    ● 文献id objectID
    ● 文献类别(0:图书,1:论文) kind
    ● 处理方法( int 表示数量,正表加,负表减) number
    */


    public String parse2(JSONObject jsonObj) throws SQLException {
        String objectID = jsonObj.getString("objectID");
        String dbName = "books";
        int number = jsonObj.getIntValue("number");
        return updateExisting(objectID, dbName, number);
    }
    /*
    插入删除文献-------插入删除
    ● 操作id "id:3"
    ● 文献类别(0:图书,1:论文) kind
    ● 文献id objectID
    ● 插入还是删除 IOD 0:insert 1: delete
    文献的介绍 1.图书(名字,作者,数量,价格,出版社,介绍)
    2-论文(名字,作者,时间,期刊会议名称,期号，卷号，页号，DOI)  分割号是逗号------合并成一个字符串 introduction
*/


    public int parse3(JSONObject jsonObj) throws SQLException {
        String result = null;
        int kind = jsonObj.getIntValue("kind");
        String dbname = "books";
        if(kind==1) dbname = "papers";

        String objectID = jsonObj.getString("objectID");
        int number = jsonObj.getIntValue("number");
        int IOD = jsonObj.getIntValue("IOD");
        if(IOD==0) {
            String introduction = jsonObj.getString("introduction");
            System.out.println("INSERT RESULT IS "+insertIntoDB(dbname,introduction,objectID));
        }
        else {
            System.out.println("DELETE RESULT IS "+deleteFromDB(dbname,objectID));
        }
        return 0;
    }


    private String updateExisting(String objectID, String dbName, int number) throws SQLException {
        String dbQuery = "SELECT " + "*" + " FROM " + dbName +" WHERE book_id=" + objectID+";";
        System.out.println(dbQuery);
        String queryResult = conn.QueryDB(dbQuery,true);
        String temp[]=queryResult.split(",");
        temp[4]=temp[4].replace(" ",""); //第五位即为existing number
        int existingNumber = Integer.parseInt(temp[4]);
        existingNumber += number;
        String SQLCmd = "UPDATE" +" " + dbName +" SET existing_number = " +existingNumber+ " WHERE book_id = "+"'" +objectID+"';";
        conn.UpdateDB(SQLCmd);
        queryResult = conn.QueryDB(dbQuery,true);
        return queryResult;
    }

    private int insertIntoDB(String dbname,String introduction,String objectID) throws SQLException {
        String SQLCmd = ";";
        String id="book_id";
        if(dbname=="papers") id="paper_id";
        SQLCmd = "INSERT INTO "+dbname+" VALUES"+"("+ introduction+");";
        return conn.insertToDB(SQLCmd);
    }

    private int deleteFromDB(String dbname,String objectID) throws SQLException {
        String SQLCmd = ";";
        String id="book_id";
        if(dbname!="books") id="paper_id";
        SQLCmd = "DELETE FROM "+ dbname + " WHERE "+ id+" = "+objectID;
        System.out.println(SQLCmd);
        int deleteResult = conn.deleteFromDB(SQLCmd);
        return deleteResult;
    }

    public static void main(String[] args) throws SQLException {
        String json_2= "{\"id\":-1,\"user_id\":\"0\",\"pwd\":\"huawei+123\"}"; //管理员登录
        String json_1= "{\"id\":-2,\"user_id\":\"123456\",\"pwd\":\"123456\"}";          //注册
        String json_22= "{\"id\":-1,\"user_id\":\"123456\",\"pwd\":\"123456\"}"; //登录
        String json0 = "{\"id\":0,\"kind\":\"0\"}";
        String json1 = "{\"id\":1,\"userID\":\"11\",\"objectID\":\"1212\",\"number\":\"+10\"}"; //测试用户借还操作
        String json2 = "{\"id\":2,\"objectID\":\"1212\",\"number\":\"-1\"}";//测试管理员增减操作
        String json3 = "{\"id\":3,\"kind\":\"0\",\"objectID\":\"123456\"," +
                "\"IOD\":\"0\",\"introduction\":\"'123456','dadada',\n" +
                " 'fdhasjfhkj','323','45','$233','fdasfdsafdsa','cool'\"}";//测试插入操作
        String json4 = "{\"id\":3,\"kind\":\"0\",\"objectID\":\"123456\",\"IOD\":\"1\",\"introduction\":\"\"}";//测试删除操作
        JSONObject jsonObj_2 =  JSON.parseObject(json_2);
        JSONObject jsonObj_22 =  JSON.parseObject(json_22);
        JSONObject jsonObj_1 =  JSON.parseObject(json_1);
        JSONObject jsonObj0 =  JSON.parseObject(json0);
        JSONObject jsonObj1 =  JSON.parseObject(json1);
        JSONObject jsonObj2 =  JSON.parseObject(json2);
        JSONObject jsonObj3 =  JSON.parseObject(json3);
        JSONObject jsonObj4 =  JSON.parseObject(json4);
        parseToSQL temp = new parseToSQL();
        temp.parse(jsonObj0);
//        temp.parse(jsonObj_22);
//        temp.parse(jsonObj_1);
//        temp.parse(jsonObj_22);
//        temp.parse(jsonObj2);
//        temp.parse(jsonObj3);
//        temp.parse(jsonObj4);
//        temp.parse(jsonObj_2);
//        temp.closeConnection();

    }
}
