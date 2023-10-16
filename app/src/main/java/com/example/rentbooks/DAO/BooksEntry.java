package com.example.rentbooks.DAO;

public class BooksEntry {
    public static final String TABLE_BOOKS = "Books";
    public static final String COL_IDBOOKS = "IdBooks";
    public static final String COL_IDCATEGORY = "IdCategory";
    public static final String COL_NAMEBOOKS = "NameBook";
    public static final String COL_AUTHOR = "Author";
    public static final String COL_IMAGEBOOKS = "ImageBook";
    public static final String COL_CATEGORYBOOKS = "CategoryBooks";
    public static final String COL_PRICE = "Price";
    public static final String COL_TOTAL = "Total";

    public static final String SQL_CREATE_BOOKS =
            "CREATE TABLE " + BooksEntry.TABLE_BOOKS + " (" +
                    BooksEntry.COL_IDBOOKS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    BooksEntry.COL_IDCATEGORY + " INTEGER," +
                    BooksEntry.COL_NAMEBOOKS + " TEXT," +
                    BooksEntry.COL_AUTHOR + " TEXT," +
                    BooksEntry.COL_CATEGORYBOOKS + " TEXT," +
                    BooksEntry.COL_IMAGEBOOKS + " TEXT," +
                    BooksEntry.COL_PRICE + " INTEGER," +
                    BooksEntry.COL_TOTAL + " INTEGER)"
                    ;
    public static final String SQL_DELETE_BOOKS =
            "DROP TABLE IF EXISTS " + BooksEntry.TABLE_BOOKS;
}
