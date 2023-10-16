package com.example.rentbooks.DAO;

import android.provider.BaseColumns;

public class UserEntry {
    public static final String TABLE_USER = "User";
    public static final String COL_ID = "Id";
    public static final String COL_PHANQUYEN = "PhanQuyen";
    public static final String COL_USERNAME = "UserName";
    public static final String COL_PASSWORD = "PassWord";
    public static final String COL_NAME = "Name";
    public static final String COL_PHONE = "Phone";
    public static final String COL_ADDRESS = "Address";
    public static final String COL_EMAIL = "Email";
    public static final String COL_AVATAR = "Avatar";

    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserEntry.TABLE_USER + " (" +
                    UserEntry.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserEntry.COL_PHANQUYEN + " INTEGER," +
                    UserEntry.COL_USERNAME + " TEXT," +
                    UserEntry.COL_PASSWORD + " TEXT," +
                    UserEntry.COL_NAME + " TEXT," +
                    UserEntry.COL_PHONE + " TEXT," +
                    UserEntry.COL_ADDRESS + " TEXT," +
                    UserEntry.COL_EMAIL + " TEXT," +
                    UserEntry.COL_AVATAR + " TEXT)";

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_USER;


}
