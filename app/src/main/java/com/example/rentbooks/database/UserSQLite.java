package com.example.rentbooks.database;

import static com.example.rentbooks.DAO.UserEntry.COL_ADDRESS;
import static com.example.rentbooks.DAO.UserEntry.COL_AVATAR;
import static com.example.rentbooks.DAO.UserEntry.COL_EMAIL;
import static com.example.rentbooks.DAO.UserEntry.COL_ID;
import static com.example.rentbooks.DAO.UserEntry.COL_NAME;
import static com.example.rentbooks.DAO.UserEntry.COL_PASSWORD;
import static com.example.rentbooks.DAO.UserEntry.COL_PHANQUYEN;
import static com.example.rentbooks.DAO.UserEntry.COL_PHONE;
import static com.example.rentbooks.DAO.UserEntry.COL_USERNAME;
import static com.example.rentbooks.DAO.UserEntry.SQL_CREATE_USER;
import static com.example.rentbooks.DAO.UserEntry.SQL_DELETE_USER;
import static com.example.rentbooks.DAO.UserEntry.TABLE_USER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentbooks.DAO.UserEntry;
import com.example.rentbooks.entity.UserLogin;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class UserSQLite extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_USER = "User.db";
    public  UserSQLite(Context context) {
        super(context,DATABASE_USER,null,1 );
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_USER);
        onCreate(sqLiteDatabase);
    }
    public long inertUser(UserLogin userLogin){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserEntry.COL_PHANQUYEN, userLogin.getPhanQuyen());
        values.put(UserEntry.COL_USERNAME, userLogin.getUserName());
        values.put(UserEntry.COL_PASSWORD, userLogin.getPassWord());
        values.put(UserEntry.COL_NAME, userLogin.getName());
        values.put(UserEntry.COL_PHONE, userLogin.getPhone());
        values.put(UserEntry.COL_ADDRESS, userLogin.getAddress());
        values.put(UserEntry.COL_EMAIL, userLogin.getEmail());
        values.put(UserEntry.COL_AVATAR, userLogin.getAvatar());

        Long result = sqLiteDatabase.insert(UserEntry.TABLE_USER, null, values);
        sqLiteDatabase.close();

        return result;
    }
    public List<UserLogin> getAllUser() {
        List<UserLogin> userLoginList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            UserLogin userLogin = new UserLogin();

            userLogin.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            userLogin.setPhanQuyen(cursor.getInt(cursor.getColumnIndex(COL_PHANQUYEN)));
            userLogin.setUserName(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
            userLogin.setPassWord(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
            userLogin.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            userLogin.setPhone(cursor.getString(cursor.getColumnIndex(COL_PHONE)));
            userLogin.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
            userLogin.setAvatar(cursor.getString(cursor.getColumnIndex(COL_AVATAR)));
            userLogin.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));

            userLoginList.add(userLogin);
            cursor.moveToNext();
        }
        return userLoginList;
    }
    public UserLogin getUserByID(int id) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER, null, COL_ID + " = ?"
                , new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();

        if (cursor != null)
            cursor.moveToFirst();

            UserLogin userLogin = new UserLogin();

            userLogin.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            userLogin.setPhanQuyen(cursor.getInt(cursor.getColumnIndex(COL_PHANQUYEN)));
            userLogin.setUserName(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
            userLogin.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            userLogin.setPhone(cursor.getString(cursor.getColumnIndex(COL_PHONE)));
            userLogin.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
            userLogin.setAvatar(cursor.getString(cursor.getColumnIndex(COL_AVATAR)));
            userLogin.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
            cursor.moveToNext();
        return userLogin;
    }
    public String getPassWordByUserName(String UserName) {

        String PassWord = "";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COL_ID, COL_USERNAME, COL_PASSWORD},
                COL_USERNAME + "=?",
                new String[]{UserName},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                PassWord = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
                cursor.moveToNext();
            }
            cursor.close();

        }
        return  PassWord;
    }
    public int getIdByUser(String UserName) {
        int Id = 0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COL_ID, COL_USERNAME, COL_PASSWORD},
                COL_USERNAME + "=?",
                new String[]{UserName},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                Id = Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_ID)));
                cursor.moveToNext();
            }
            cursor.close();

        }
        return  Id;
    }
    public int getPhanQuyenByUser(String UserName) {
        int PhanQuyen = 0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COL_PHANQUYEN},
                COL_USERNAME + "=?",
                new String[]{UserName},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                PhanQuyen = Integer.valueOf(cursor.getString(cursor.getColumnIndex(COL_PHANQUYEN)));
                cursor.moveToNext();
            }
            cursor.close();

        }
        return  PhanQuyen;
    }
    public boolean checkUserName(String userName) {

        boolean checkUserNAme = false;
        String Name = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                new String[]{COL_ID, COL_USERNAME, COL_PASSWORD},
                COL_USERNAME + "=?",
                new String[]{userName},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Name = cursor.getString(cursor.getColumnIndex(COL_USERNAME));
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (Name.equals(userName)) {
            checkUserNAme = false;
        } else {
            checkUserNAme = true;
        }
        return  checkUserNAme;
    }
    public void updateUser(UserLogin userLogin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, userLogin.getName());
        values.put(COL_PHONE, userLogin.getPhone());
        values.put(COL_EMAIL, userLogin.getEmail());
        values.put(COL_ADDRESS, userLogin.getAddress());
        values.put(COL_AVATAR, userLogin.getAvatar());

        db.update(TABLE_USER, values, COL_ID + " = ?", new String[] { String.valueOf(userLogin.getId()) });
        db.close();
    }
}
