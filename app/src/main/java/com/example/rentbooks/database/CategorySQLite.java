package com.example.rentbooks.database;

import static com.example.rentbooks.DAO.CategoryEntry.COL_CATEGORY;
import static com.example.rentbooks.DAO.CategoryEntry.COL_ID_CATEGORY;
import static com.example.rentbooks.DAO.CategoryEntry.SQL_CREATE_CATEGORY;
import static com.example.rentbooks.DAO.CategoryEntry.SQL_DELETE_CATEGORY;
import static com.example.rentbooks.DAO.CategoryEntry.TABLE_CATEGOGY;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rentbooks.DAO.CategoryEntry;

import com.example.rentbooks.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategorySQLite extends SQLiteOpenHelper {
    public static final String DATABASE_CATEGORY = "Category.db";
    private SQLiteDatabase sqLiteDatabase;

    public CategorySQLite(Context context) {
        super(context, DATABASE_CATEGORY, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_CATEGORY);
        onCreate(sqLiteDatabase);
    }
    public long inertCategory(Category category){
        sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CategoryEntry.COL_CATEGORY, category.getNameCategoty());

        Long result = sqLiteDatabase.insert(TABLE_CATEGOGY, null, values);
        sqLiteDatabase.close();

        return result;
    }
    @SuppressLint("Range")
    public List<Category> getAllCategory() {
        List<Category> categoryList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_CATEGOGY;
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Category category = new Category();

            category.setIdCategory(cursor.getInt(cursor.getColumnIndex(COL_ID_CATEGORY)));
            category.setNameCategoty(cursor.getString(cursor.getColumnIndex(COL_CATEGORY)));


            categoryList.add(category);
            cursor.moveToNext();
        }
        return categoryList;
    }
    @SuppressLint("Range")
    public boolean checkCategory(String category) {

        boolean checkCategory = false;
        String CheckCategory = "";
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_CATEGOGY,
                new String[]{CategoryEntry.COL_ID_CATEGORY, COL_CATEGORY},
                COL_CATEGORY + "=?",
                new String[]{category},
                null, null, null);

        Log.e("test", String.valueOf(cursor.getCount()));
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                CheckCategory = cursor.getString(cursor.getColumnIndex(COL_CATEGORY));
                cursor.moveToNext();
            }
            cursor.close();
        }

        if (CheckCategory.equals(category)) {
            checkCategory = false;
        } else {
            checkCategory = true;
        }
        return  checkCategory;
    }
    public long deteleCategoy(int inCategory){
         sqLiteDatabase = getWritableDatabase();

        long resutl = sqLiteDatabase.delete(TABLE_CATEGOGY, COL_ID_CATEGORY + "=?", new String[]{String.valueOf(inCategory)});
        sqLiteDatabase.close();
        return resutl;
    }

}
