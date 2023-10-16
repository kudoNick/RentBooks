package com.example.rentbooks.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentbooks.Interface.IClickIteamBooks;
import com.example.rentbooks.Interface.IClickIteamCart;
import com.example.rentbooks.databinding.IteamBooksBinding;
import com.example.rentbooks.entity.Cart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<Cart> cartList;
    private Cart cart;
    private IClickIteamCart iClickIteamCart;
    private IteamBooksBinding binding;

    public CartAdapter(Context context, List<Cart> cartList, IClickIteamCart iClickIteamBooks) {
        this.context = context;
        this.cartList = cartList;
        this.iClickIteamCart = iClickIteamBooks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = IteamBooksBinding.inflate(LayoutInflater.from(context), parent, false);
        return new  ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        cart = cartList.get(position);

        if (!cart.getImageBook().isEmpty()) {
            binding.imgBook.setImageURI(Uri.parse(cart.getImageBook()));
        }
        binding.tvNameBooks.setText(cart.getNameBook());
        binding.tvAuthor.setText("Tác giả: " + cart.getAuthor());
        binding.tvTotal.setVisibility(View.VISIBLE);
        binding.tvTotal.setText("Số lượng: " + cart.getSoLuong());

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        String price = vn.format(cart.getPriceFinal());
        binding.tvPrice.setText(price + " VND");

        binding.layout.setOnClickListener(view -> {
                iClickIteamCart.onClickIteamBooks(cartList.get(position));
        });
        binding.layout.setOnLongClickListener(view -> {
            iClickIteamCart.onLongClickIteamBooks(cartList.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
