package com.example.rentbooks.fragment.CartFragment;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rentbooks.Activity.DetailBook.DetailBookActivity;
import com.example.rentbooks.Adapter.BooksAdapter;
import com.example.rentbooks.Adapter.CartAdapter;
import com.example.rentbooks.Adapter.CategoryAdapter;
import com.example.rentbooks.Interface.IClickIteamBooks;
import com.example.rentbooks.Interface.IClickIteamCart;
import com.example.rentbooks.database.CartSQLite;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Cart;
import com.example.rentbooks.keyWord.KeyWord;

import java.util.List;

public class CartViewModel implements IClickIteamCart {
    private Context context;
    private RecyclerView rcvCart;
    private Cart cart;
    private List<Cart> cartList;
    private List<Books> booksList;
    private CartSQLite cartSQLite;
    private CartAdapter cartAdapter;


    public CartViewModel(Context context, RecyclerView rcvCart) {
        this.context = context;
        this.rcvCart = rcvCart;
        cartSQLite = new CartSQLite(context);
    }

    public void loadData() {
        cartList = cartSQLite.getCartByIdUser(KeyWord.IdUser);

        cartAdapter = new CartAdapter(context, cartList, this);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvCart.setHasFixedSize(true);
        rcvCart.setLayoutManager(gridLayoutManager);
        rcvCart.setAdapter(cartAdapter);
    }


    @Override
    public void onClickIteamBooks(Cart cart) {
        Intent intent = new Intent(context, DetailBookActivity.class);
        intent.putExtra("idCart", cart.getIdBooks());
        context.startActivity(intent);
    }

    @Override
    public void onLongClickIteamBooks(Cart cart) {

    }
}
