package com.lifeng.pojo;

/**
 * 用户表
 * @author fengli
 * @version 1.0
 * @date 2020/12/05
 */
public class UserLogin {
    private int id;//主键ID
    private String userName;//用户名
    private String password;//密码

    public UserLogin() {
    }

    public UserLogin(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
