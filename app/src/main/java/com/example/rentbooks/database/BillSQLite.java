package com.example.rentbooks.database;

import static com.example.rentbooks.DAO.BooksEntry.TABLE_BOOKS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.rentbooks.DAO.BillEntry;
import com.example.rentbooks.DAO.BooksEntry;
import com.example.rentbooks.entity.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_BILL = "Bill.db";
    private SQLiteDatabase sqLiteDatabase;
    public BillSQLite(Context context) {
        super(context, DATABASE_BILL, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BillEntry.SQL_CREATE_BILL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BillEntry.SQL_DELETE_BILL);
        onCreate(sqLiteDatabase);
    }

    public long inertBill(Bill bill) {
        sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BillEntry.COL_IDUSER, bill.getIdUser());
        values.put(BillEntry.COL_status, bill.getStatus());
        values.put(BillEntry.COL_SOLUONG, bill.getSoLuong());
        values.put(BillEntry.COL_TOTALPRICE, bill.getTotalPrice());

        values.put(BooksEntry.COL_NAMEBOOKS, bill.getNameBook());
        values.put(BooksEntry.COL_AUTHOR, bill.getAuthor());
        values.put(BooksEntry.COL_CATEGORYBOOKS, bill.getCategoryBooks());
        values.put(BooksEntry.COL_IMAGEBOOKS, bill.getImageBook());
        values.put(BooksEntry.COL_PRICE, bill.getPrice());

        long result = sqLiteDatabase.insert(BillEntry.TABLE_BILL, null, values);
        sqLiteDatabase.close();

        return result;
    }
    @SuppressLint("Range")
    public List<Bill> getAllBill() {
        List<Bill> billList = new ArrayList<>();

        String sql = "SELECT * FROM " + BillEntry.TABLE_BILL;
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Bill bill = new Bill();

            bill.setIdBill(cursor.getInt(cursor.getColumnIndex(BillEntry.COL_IDBILL)));
            bill.setIdUser(cursor.getInt(cursor.getColumnIndex(BillEntry.COL_IDUSER)));
            bill.setStatus(cursor.getInt(cursor.getColumnIndex(BillEntry.COL_status)));
            bill.setSoLuong(cursor.getInt(cursor.getColumnIndex(BillEntry.COL_SOLUONG)));
            bill.setTotalPrice(cursor.getInt(cursor.getColumnIndex(BillEntry.COL_TOTALPRICE)));

            bill.setNameBook(cursor.getString(cursor.getColumnIndex(BooksEntry.COL_NAMEBOOKS)));
            bill.setAuthor(cursor.getString(cursor.getColumnIndex(BooksEntry.COL_AUTHOR)));
            bill.setCategoryBooks(cursor.getString(cursor.getColumnIndex(BooksEntry.COL_CATEGORYBOOKS)));
            bill.setImageBook(cursor.getString(cursor.getColumnIndex(BooksEntry.COL_IMAGEBOOKS)));
            bill.setPrice(cursor.getInt(cursor.getColumnIndex(BooksEntry.COL_PRICE)));

            billList.add(bill);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return billList;
    }
}
