package com.example.rentbooks.Activity.DetailBook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rentbooks.database.BillSQLite;
import com.example.rentbooks.database.BooksSQLite;
import com.example.rentbooks.database.CartSQLite;
import com.example.rentbooks.entity.Bill;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Cart;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailBooksViewModel{
    private Books books;
    private Cart cart;
    private BooksSQLite booksSQLite;
    private CartSQLite cartSQLite;
    private BillSQLite billSQLite;
    private Context context;

    public DetailBooksViewModel(Context context) {
        this.context = context;
        booksSQLite = new BooksSQLite(context);
        cartSQLite = new CartSQLite(context);
        billSQLite = new BillSQLite(context);
    }

    public Books getBookByIdBook(int idBook) {
        return books = booksSQLite.getBookByIdBook(idBook);
    }
    public Cart getBookCartByIdBook(int idBook) {
        return cart = cartSQLite.getBookByIdBookCart(idBook);
    }

    public boolean addCart(Cart cart) {
        long result = cartSQLite.inertCart(cart);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean buyBook(Bill bill) {
        long result = billSQLite.inertBill(bill);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean dellCart(int idCart) {
        long resutl = cartSQLite.deleteCart(idCart);
        if (resutl > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateTotalBooks(int idBook, int lastTotal) {
        booksSQLite.updateTotalBook(idBook,lastTotal);
        return false;
    }
}
