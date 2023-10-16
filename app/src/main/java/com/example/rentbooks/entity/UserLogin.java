package com.example.rentbooks.entity;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class UserLogin extends User {
    private String UserName, PassWord;
    private int lengthPass = 6;


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public boolean checkPassWord() {
        return PassWord.length() >= lengthPass;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

}
