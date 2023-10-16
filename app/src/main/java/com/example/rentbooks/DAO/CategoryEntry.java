package com.example.rentbooks.DAO;

public class CategoryEntry {
    public static final String TABLE_CATEGOGY = "Category";

    public static final String COL_ID_CATEGORY = "IdCategory";
    public static final String COL_CATEGORY = "NameCategory";

    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + CategoryEntry.TABLE_CATEGOGY + " (" +
                    CategoryEntry.COL_ID_CATEGORY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CategoryEntry.COL_CATEGORY + " TEXT)" ;
    public static final String SQL_DELETE_CATEGORY =
            "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_CATEGOGY;


}
