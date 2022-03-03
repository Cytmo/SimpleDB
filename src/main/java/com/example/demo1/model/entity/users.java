package com.example.demo1.model.entity;

public class users {
    private String user_name;
    private String user_password;
    private String identify;  // 0表示管理员 ,1表示普通用户
    private String email;

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getIdentify() {
        return identify;
    }

    public String getEmail() {
        return email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
