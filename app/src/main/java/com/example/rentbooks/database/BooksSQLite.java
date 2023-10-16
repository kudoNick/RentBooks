package com.example.rentbooks.database;

import static com.example.rentbooks.DAO.BooksEntry.COL_AUTHOR;
import static com.example.rentbooks.DAO.BooksEntry.COL_CATEGORYBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_IDBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_IDCATEGORY;
import static com.example.rentbooks.DAO.BooksEntry.COL_IMAGEBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_NAMEBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_PRICE;
import static com.example.rentbooks.DAO.BooksEntry.COL_TOTAL;
import static com.example.rentbooks.DAO.BooksEntry.SQL_CREATE_BOOKS;
import static com.example.rentbooks.DAO.BooksEntry.SQL_DELETE_BOOKS;
import static com.example.rentbooks.DAO.BooksEntry.TABLE_BOOKS;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentbooks.DAO.BooksEntry;
import com.example.rentbooks.entity.Books;

import java.util.ArrayList;
import java.util.List;

public class BooksSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_BOOKS = "Books.db";
    private SQLiteDatabase sqLiteDatabase;
    public BooksSQLite(Context context){
        super(context,DATABASE_BOOKS,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_BOOKS);
        onCreate(sqLiteDatabase);
    }
    public long inertBooks(Books books){
         sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BooksEntry.COL_IDCATEGORY, books.getIdCategory());
        values.put(BooksEntry.COL_NAMEBOOKS, books.getNameBook());
        values.put(BooksEntry.COL_AUTHOR, books.getAuthor());
        values.put(BooksEntry.COL_IMAGEBOOKS, books.getImageBook());
        values.put(BooksEntry.COL_CATEGORYBOOKS, books.getCategoryBooks());
        values.put(BooksEntry.COL_PRICE, books.getPrice());
        values.put(BooksEntry.COL_TOTAL, books.getTotal());


        Long result = sqLiteDatabase.insert(BooksEntry.TABLE_BOOKS, null, values);
        sqLiteDatabase.close();

        return result;
    }
    @SuppressLint("Range")
    public List<Books> getAllBooks() {
        List<Books> booksList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_BOOKS;
         sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Books books = new Books();

            books.setIdBooks(cursor.getInt(cursor.getColumnIndex(COL_IDBOOKS)));
            books.setIdCategory(cursor.getInt(cursor.getColumnIndex(COL_IDCATEGORY)));
            books.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
            books.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
            books.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));
            books.setCategoryBooks(cursor.getString(cursor.getColumnIndex(COL_CATEGORYBOOKS)));
            books.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
            books.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));

            booksList.add(books);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return booksList;
    }
    @SuppressLint("Range")
    public List<Books> getBooksListBycategory(int idcategory) {
        List<Books> booksList = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BOOKS,
                new String[]{COL_IDBOOKS, COL_IDCATEGORY,COL_IMAGEBOOKS,COL_CATEGORYBOOKS, COL_NAMEBOOKS, COL_AUTHOR, COL_PRICE, COL_TOTAL},
                COL_IDCATEGORY + "=?",
                new String[]{String.valueOf(idcategory)}, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Books books = new Books();

            books.setIdBooks(cursor.getInt(cursor.getColumnIndex(COL_IDBOOKS)));
            books.setIdCategory(cursor.getInt(cursor.getColumnIndex(COL_IDCATEGORY)));
            books.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
            books.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
            books.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));
            books.setCategoryBooks(cursor.getString(cursor.getColumnIndex(COL_CATEGORYBOOKS)));
            books.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
            books.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));

            booksList.add(books);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return booksList;
    }
    @SuppressLint("Range")
    public boolean checkNameBooks(String nameBooks) {
        String books = "";
         sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_BOOKS,
                new String[]{COL_IDBOOKS, COL_NAMEBOOKS},
                COL_NAMEBOOKS + "=?",
                new String[]{nameBooks},
                null, null, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                books = cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS));
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (books.equals(nameBooks)) {
            sqLiteDatabase.close();
            return false;
        }
        sqLiteDatabase.close();
        return true;
    }
    @SuppressLint("Range")
    public Books getBookByIdBook(int idBook) {
        sqLiteDatabase = this.getReadableDatabase();
        Books books = new Books();
        Cursor cursor = sqLiteDatabase.query(TABLE_BOOKS,
                new String[]{COL_IDBOOKS, COL_NAMEBOOKS,COL_AUTHOR,COL_PRICE,COL_TOTAL,COL_IMAGEBOOKS},
                COL_IDBOOKS  + "=?",
                new String[]{String.valueOf(idBook)},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                books.setIdBooks(cursor.getInt(cursor.getColumnIndex(COL_IDBOOKS)));
                books.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
                books.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                books.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
                books.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));
                books.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));

                cursor.moveToNext();
            }
            cursor.close();
        }
        return books;
    }
    public long deleteBooks(int idBooks) {
        sqLiteDatabase = getWritableDatabase();

        long resutl = sqLiteDatabase.delete(TABLE_BOOKS, COL_IDBOOKS + "=?",
                new String[]{String.valueOf(idBooks)});
        sqLiteDatabase.close();
        return resutl;
    }
    public void updateTotalBook(int idbook, int lastTotal) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TOTAL, lastTotal);
        sqLiteDatabase.update(TABLE_BOOKS, values, COL_IDBOOKS + " =?",
                new String[]{String.valueOf(idbook)});
        sqLiteDatabase.close();
    }
}
