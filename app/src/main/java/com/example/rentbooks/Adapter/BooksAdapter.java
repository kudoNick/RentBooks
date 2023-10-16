package com.example.rentbooks.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentbooks.Interface.IClickIteamBooks;
import com.example.rentbooks.databinding.IteamBooksBinding;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Cart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private Context context;
    private List<Books> booksList;
    private Books books;
    private IteamBooksBinding binding;
    private IClickIteamBooks iClickIteamBooks;
    private int checkAdapter = 0;

    public BooksAdapter(Context context, List<Books> booksList, IClickIteamBooks iClickIteamBooks) {
        this.context = context;
        this.booksList = booksList;
        this.iClickIteamBooks = iClickIteamBooks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = IteamBooksBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        books = booksList.get(position);

        if (!books.getImageBook().isEmpty()) {
            binding.imgBook.setImageURI(Uri.parse(books.getImageBook()));
        }
        binding.tvNameBooks.setText(books.getNameBook());
        binding.tvAuthor.setText("Tác giả: " + books.getAuthor());

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        String price = vn.format(books.getPrice());

        binding.tvPrice.setText(price + " VND");

        binding.layout.setOnClickListener(view -> {
            if (!(iClickIteamBooks == null)) {
                iClickIteamBooks.onClickIteamBooks(booksList.get(position));
            }

        });
        binding.layout.setOnLongClickListener(view -> {
            iClickIteamBooks.onLongClickIteamBooks(booksList.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        if (booksList == null) {
            booksList = new ArrayList<>();
            return booksList.size();
        }
        return booksList.size();
    }
}
