package com.example.demo1.model.entity;

/**
 * 用户信息实体类
 * @author pan_junbiao
 **/
public class User {
    private int id; //用户编号
    private String userName; //用户姓名
    private String blogUrl; //博客地址
    private String blogInfo; //博客信息
    private String role; //用户角色

    //省略getter与setter方法...

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public String getBlogInfo() {
        return blogInfo;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public void setBlogInfo(String blogInfo) {
        this.blogInfo = blogInfo;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

