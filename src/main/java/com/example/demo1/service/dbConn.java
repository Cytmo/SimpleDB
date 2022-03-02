package com.example.demo1.service;
import java.sql.*;

/*
    数据库相关操作
 */
public class dbConn {

    private final String dbDrive = "org.opengauss.Driver";
    private final String dbUrl = "jdbc:opengauss://121.36.60.12:5432/remote_db";
    private final String dbUserName = "remote_user";
    private final String dbPassword = "huawei+123";
    public Connection dbConnection = null;

    //通过构造方法加载数据库驱动
    public dbConn() {
        try {
            Class.forName(dbDrive);
            System.out.println("jdbc加载成功");
        } catch (ClassNotFoundException notfound) {
            notfound.printStackTrace();
        }
    }

    //创建数据库连接
    public void createConnection() {
        try {
            /* 创建数据库连接对象 */
            dbConnection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            System.out.println("数据库连接成功");
//            conn.close();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }

    //关闭数据库连接
    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改数据
    public int insertToDB(String SQLCmd) throws SQLException {
        Statement statement = dbConnection.createStatement(); // Statement对象
        int rows; // 影响行数
        ResultSet rs; // 结果集合
        rows = statement.executeUpdate(SQLCmd);
        System.out.println("插入影响行数为：" + rows);
        return 0;
    }


    public String QueryDB(String SQLCmd, boolean if_books) throws SQLException {
        Statement statement = dbConnection.createStatement(); // Statement对象
        ResultSet rs; // 结果集合
        rs = statement.executeQuery(SQLCmd);
        String result = null;
        System.out.println("查询结果为：");
        if (if_books) {
            while (rs.next()) {
                String s = rs.getString("book_id") + "-" + rs.getString("book_name") + "-"
                        + rs.getString("author") + "-" + rs.getString("collection_number") + "-"
                        + rs.getString("existing_number") + "-" + rs.getString("price") + "-"
                        + rs.getString("publisher") + "-" + rs.getString("introduction");
                result = s;
                s.replace(" ", "");
                s.replace("-", ",");
                System.out.println(s);
            }
        }
//        ● 文献id objectID
//        ● 文献类别(0:图书,1:论文) kind
//        ● 插入还是删除 IOD (0:插入/1:删除)
//        ● 论文(名字,作者,时间,期刊会议名称,期号，卷号，页号，DOI)  分割号是逗号------合并成一个字符串 introduction
        else {
            while (rs.next()) {
                String s = rs.getString("paper_id") + "-" + rs.getString("paper_title") + "-"
                        + rs.getString("author") + "-" + rs.getString("date") + "-"
                        + rs.getString("jc_name") + "-" + rs.getString("issue_number") + "-"
                        + rs.getString("volume_number") + "-" + rs.getString("page_number") + "-"
                        + rs.getString("doi");
                result = s;
                s.replace(" ", "");
                s.replace("-", ",");
                System.out.println(s);
            }
        }
        return result;
    }

    public int UpdateDB(String SQLCmd) throws SQLException {
        Statement statement = dbConnection.createStatement(); // Statement对象
        int rows;
        rows = statement.executeUpdate(SQLCmd);
        System.out.println("更新影响行数为：" + rows);
        return 0;
    }

    public int deleteFromDB(String SQLCmd) throws SQLException {
        Statement statement = dbConnection.createStatement(); // Statement对象
        int rows;
        rows = statement.executeUpdate(SQLCmd);
        System.out.println("删除影响行数为：" + rows);
        return 0;
    }

    public int LoginDB(String SQLCmd, String pwd) throws SQLException {
        Statement statement = dbConnection.createStatement(); // Statement对象
        ResultSet rs; // 结果集合
        int result = 2;
        rs = statement.executeQuery(SQLCmd);
        System.out.println("查询结果为：");
        while (rs.next()) {
            String id = rs.getString("user_name");
            String pwd_query = rs.getString("user_password");
            pwd_query.replaceAll(" ","");
            System.out.println("111111111111111111111111"+pwd_query+"uyuytuytutyujty");
            System.out.println("++++++++++++++++++++++++"+pwd);
            String if_admin = rs.getString("identify");
            if (pwd.equals(pwd_query)) {
                if (if_admin.equals("1")) {
                    result = 0;
                } else {
                    return 1;
                }
            } else return 2;
        }
        return 4;
    }
}