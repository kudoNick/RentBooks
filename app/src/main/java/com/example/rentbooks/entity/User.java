package com.example.rentbooks.entity;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.List;
import java.util.regex.Pattern;

public class User {

    //Phan quyen: 0 = user , 1 = admin
    private int Id, PhanQuyen;
    private String Name, Phone, Address, Email, Avatar;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        PhanQuyen = phanQuyen;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public boolean checkEmail() {
        return !TextUtils.isEmpty(Email) && Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }

    public boolean checkPhone() {
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean kt = Phone.matches(reg);
        return kt;

    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", PhanQuyen=" + PhanQuyen +
                ", Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Avatar='" + Avatar + '\'' +
                '}';
    }
}
