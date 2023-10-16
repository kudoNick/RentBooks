package com.example.rentbooks.DAO;

public class CartEntry {
    public static final String TABLE_CART = "Cart";
    public static final String COL_IDCART = "IdCart";
    public static final String COL_IDUSER = "IdUser";
    public static final String COL_SOLUONG = "SoLuong";
    public static final String COL_CHECKBUY = "CheckBuy";
    public static final String COL_PRICEFINAL= "FriceFinal";

    public static final String SQL_CREATE_CART =

            "CREATE TABLE " + CartEntry.TABLE_CART + " (" +
                    CartEntry.COL_IDCART + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartEntry.COL_IDUSER + " INTEGER," +
                    CartEntry.COL_SOLUONG + " INTEGER," +
                    CartEntry.COL_CHECKBUY + " INTEGER," +
                    CartEntry.COL_PRICEFINAL + " INTEGER," +

                    BooksEntry.COL_IDBOOKS + " INTEGER," +
                    BooksEntry.COL_NAMEBOOKS + " TEXT," +
                    BooksEntry.COL_AUTHOR + " TEXT," +
                    BooksEntry.COL_CATEGORYBOOKS + " TEXT," +
                    BooksEntry.COL_IMAGEBOOKS + " TEXT," +
                    BooksEntry.COL_PRICE + " INTEGER," +
                    BooksEntry.COL_TOTAL + " INTEGER)"
            ;
    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartEntry.TABLE_CART;
}
