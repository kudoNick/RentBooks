package com.example.rentbooks.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.rentbooks.Activity.SignAcivity;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.listener.StatusDataUserListener;
import com.example.rentbooks.entity.UserLogin;

import java.util.ArrayList;
import java.util.List;

public class UserPresenter{

    private StatusDataUserListener signDataUserListener;
    private Context context;

    private UserSQLite userSQLite;
    private UserLogin userLogin;
    private List<UserLogin> userLoginList;

    public UserPresenter(StatusDataUserListener signDataUserListener, Context context) {
        this.signDataUserListener = signDataUserListener;
        this.context = context;
    }

    public void signDataUser(UserLogin userLogin){

        userSQLite = new UserSQLite(context);

        userLoginList = new ArrayList<>();
        userLoginList = userSQLite.getAllUser();

        boolean checkUser = userSQLite.checkUserName(userLogin.getUserName());
        if (checkUser) {

            if (userLoginList.isEmpty()) {
                userLogin.setPhanQuyen(1);
            } else {
                userLogin.setPhanQuyen(0);
            }
            long result = userSQLite.inertUser(userLogin);
            if (result > 0) {
                signDataUserListener.onLoadUserSuccess();
                SignAcivity.SignActivity.finish();
            } else {
                Toast.makeText(context, "Loi o class UserPresenter dong 48", Toast.LENGTH_SHORT).show();
            }
        } else {
            signDataUserListener.onLoadUserFail();
        }







    }
    public boolean checkGetPassWordByUserName(String UserName, String PassWord){
        boolean checkPass;
        String passWord = "";

        userSQLite = new UserSQLite(context);
        passWord = userSQLite.getPassWordByUserName(UserName);

        if (passWord.equals(PassWord)) {
            checkPass = true;
            signDataUserListener.onLoadUserSuccess();
        } else {
            checkPass = false;
            signDataUserListener.onLoadUserFail();
        }
        return checkPass;
    }

    public int getIdUserIdByUserName(String UserName){
        userSQLite = new UserSQLite(context);
        return userSQLite.getIdByUser(UserName);
    }
    public int getPhanQuyenByUserName(String UserName){
        userSQLite = new UserSQLite(context);
        return userSQLite.getPhanQuyenByUser(UserName);
    }
}
