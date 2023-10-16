package com.example.rentbooks.database;

import static com.example.rentbooks.DAO.BooksEntry.COL_AUTHOR;
import static com.example.rentbooks.DAO.BooksEntry.COL_CATEGORYBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_IDBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_IMAGEBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_NAMEBOOKS;
import static com.example.rentbooks.DAO.BooksEntry.COL_PRICE;
import static com.example.rentbooks.DAO.BooksEntry.COL_TOTAL;
import static com.example.rentbooks.DAO.BooksEntry.SQL_DELETE_BOOKS;
import static com.example.rentbooks.DAO.BooksEntry.TABLE_BOOKS;
import static com.example.rentbooks.DAO.CartEntry.COL_CHECKBUY;
import static com.example.rentbooks.DAO.CartEntry.COL_IDCART;
import static com.example.rentbooks.DAO.CartEntry.COL_IDUSER;
import static com.example.rentbooks.DAO.CartEntry.COL_PRICEFINAL;
import static com.example.rentbooks.DAO.CartEntry.COL_SOLUONG;
import static com.example.rentbooks.DAO.CartEntry.SQL_CREATE_CART;
import static com.example.rentbooks.DAO.CartEntry.SQL_DELETE_CART;
import static com.example.rentbooks.DAO.CartEntry.TABLE_CART;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rentbooks.DAO.BillEntry;
import com.example.rentbooks.DAO.BooksEntry;
import com.example.rentbooks.DAO.CartEntry;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartSQLite extends SQLiteOpenHelper {

    public static final String DATABASE_CART = "Cart.db";
    private SQLiteDatabase sqLiteDatabase;

    public CartSQLite(Context context){
        super(context,DATABASE_CART,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_CART);
        onCreate(sqLiteDatabase);
    }

    public long inertCart(Cart cart){
        sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CartEntry.COL_IDUSER, cart.getIdUser());
        values.put(CartEntry.COL_CHECKBUY, cart.getCheckBuy());
        values.put(CartEntry.COL_SOLUONG, cart.getSoLuong());
        values.put(CartEntry.COL_PRICEFINAL, cart.getPriceFinal());

        values.put(BooksEntry.COL_IDBOOKS, cart.getIdBooks());
        values.put(BooksEntry.COL_NAMEBOOKS, cart.getNameBook());
        values.put(BooksEntry.COL_AUTHOR, cart.getAuthor());
        values.put(BooksEntry.COL_IMAGEBOOKS, cart.getImageBook());
        values.put(BooksEntry.COL_CATEGORYBOOKS, cart.getCategoryBooks());
        values.put(BooksEntry.COL_PRICE, cart.getPrice());
        values.put(BooksEntry.COL_TOTAL, cart.getTotal());


        Long result = sqLiteDatabase.insert(CartEntry.TABLE_CART, null, values);
        sqLiteDatabase.close();

        return result;
    }
    @SuppressLint("Range")
    public List<Cart> getAllCart() {
        List<Cart> cartList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_CART;
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Cart cart = new Cart();

            cart.setIdCart(cursor.getInt(cursor.getColumnIndex(COL_IDCART)));
            cart.setIdUser(cursor.getInt(cursor.getColumnIndex(COL_IDUSER)));
            cart.setSoLuong(cursor.getInt(cursor.getColumnIndex(COL_SOLUONG)));
            cart.setCheckBuy(cursor.getInt(cursor.getColumnIndex(COL_CHECKBUY)));
            cart.setSoLuong(cursor.getInt(cursor.getColumnIndex(COL_SOLUONG)));
            cart.setPriceFinal(cursor.getInt(cursor.getColumnIndex(COL_PRICEFINAL)));

            cart.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
            cart.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
            cart.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));
            cart.setCategoryBooks(cursor.getString(cursor.getColumnIndex(COL_CATEGORYBOOKS)));
            cart.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
            cart.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));

            cartList.add(cart);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return cartList;
    }

    @SuppressLint("Range")
    public List<Cart> getCartByIdUser(int idUser) {
        List<Cart> cartList = new ArrayList<>();
        sqLiteDatabase = this.getReadableDatabase();


        Cursor cursor = sqLiteDatabase.query(TABLE_CART,
                new String[]{COL_IDCART, COL_IDBOOKS, COL_NAMEBOOKS, COL_AUTHOR, COL_PRICE, COL_TOTAL, COL_IMAGEBOOKS, COL_IDUSER,COL_PRICEFINAL,COL_SOLUONG},
                COL_IDUSER + " =?",
                new String[]{String.valueOf(idUser)},
                null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Cart cart = new Cart();
                cart.setIdBooks(cursor.getInt(cursor.getColumnIndex(COL_IDBOOKS)));
                cart.setIdCart(cursor.getInt(cursor.getColumnIndex(COL_IDCART)));
                cart.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
                cart.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                cart.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
                cart.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));
                cart.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));

                cart.setPriceFinal(cursor.getInt(cursor.getColumnIndex(COL_PRICEFINAL)));
                cart.setSoLuong(cursor.getInt(cursor.getColumnIndex(COL_SOLUONG)));

                cartList.add(cart);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return cartList;
    }

    @SuppressLint("Range")
    public Cart getBookByIdBookCart(int idBook) {
        sqLiteDatabase = this.getReadableDatabase();

        Cart cart = new Cart();
        Cursor cursor = sqLiteDatabase.query(TABLE_CART,
                new String[]{COL_IDBOOKS, COL_NAMEBOOKS,COL_AUTHOR,COL_PRICE,COL_TOTAL,COL_IMAGEBOOKS,COL_IDCART,COL_IDUSER,COL_SOLUONG,COL_CHECKBUY,COL_SOLUONG,COL_PRICEFINAL},
                COL_IDBOOKS  + "=?",
                new String[]{String.valueOf(idBook)},
                null, null, null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                cart.setIdBooks(cursor.getInt(cursor.getColumnIndex(COL_IDBOOKS)));
                cart.setNameBook(cursor.getString(cursor.getColumnIndex(COL_NAMEBOOKS)));
                cart.setAuthor(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                cart.setPrice(cursor.getInt(cursor.getColumnIndex(COL_PRICE)));
                cart.setTotal(cursor.getInt(cursor.getColumnIndex(COL_TOTAL)));
                cart.setImageBook(cursor.getString(cursor.getColumnIndex(COL_IMAGEBOOKS)));

                cart.setIdCart(cursor.getInt(cursor.getColumnIndex(COL_IDCART)));
                cart.setIdUser(cursor.getInt(cursor.getColumnIndex(COL_IDUSER)));
                cart.setSoLuong(cursor.getInt(cursor.getColumnIndex(COL_SOLUONG)));
                cart.setCheckBuy(cursor.getInt(cursor.getColumnIndex(COL_CHECKBUY)));
                cart.setSoLuong(cursor.getInt(cursor.getColumnIndex(COL_SOLUONG)));
                cart.setPriceFinal(cursor.getInt(cursor.getColumnIndex(COL_PRICEFINAL)));

                cursor.moveToNext();
            }
            cursor.close();
        }
        return cart;
    }

    public long deleteCart(int idCart) {
        sqLiteDatabase = getWritableDatabase();
        long resutl = sqLiteDatabase.delete(TABLE_CART, COL_IDCART + " =?",
                new String[]{String.valueOf(idCart)});
        sqLiteDatabase.close();

        return resutl;
    }
}
