package com.example.rentbooks.DAO;


public class BillEntry {
    public static final String TABLE_BILL = "Bill";
    public static final String COL_IDBILL = "IdBill";
    public static final String COL_IDUSER = "IdUser";
    public static final String COL_status = "status";
    public static final String COL_SOLUONG = "SoLuong";
    public static final String COL_TOTALPRICE = "TotalPrice";

    public static String SQL_CREATE_BILL =
            "CREATE TABLE " + TABLE_BILL + " (" +
                    COL_IDBILL + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_IDUSER + " INTEGER," +
                    COL_status + " INTEGER," +
                    COL_SOLUONG + " INTEGER," +
                    COL_TOTALPRICE + " INTEGER," +

                    BooksEntry.COL_NAMEBOOKS + " TEXT," +
                    BooksEntry.COL_AUTHOR + " TEXT," +
                    BooksEntry.COL_CATEGORYBOOKS + " TEXT," +
                    BooksEntry.COL_IMAGEBOOKS + " TEXT," +
                    BooksEntry.COL_PRICE + " INTEGER)"
            ;
    public static final String SQL_DELETE_BILL =
            "DROP TABLE IF EXISTS " + TABLE_BILL;
}
