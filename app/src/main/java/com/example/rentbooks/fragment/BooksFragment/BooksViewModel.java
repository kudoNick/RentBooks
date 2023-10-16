package com.example.rentbooks.fragment.BooksFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rentbooks.Adapter.BooksAdapter;
import com.example.rentbooks.Activity.DetailBook.DetailBookActivity;
import com.example.rentbooks.Interface.IClickIteamBooks;
import com.example.rentbooks.Interface.LoadDataBooks;
import com.example.rentbooks.database.BooksSQLite;
import com.example.rentbooks.entity.Books;

import java.util.ArrayList;
import java.util.List;

public class BooksViewModel extends ViewModel implements LoadDataBooks, IClickIteamBooks {
    private BooksSQLite booksSQLite;
    private Context context;
    private List<Books> booksList;
    private BooksAdapter booksAdapter;
    private RecyclerView recyclerView;

    public BooksViewModel(Context context,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        booksSQLite = new BooksSQLite(context);
        booksList = new ArrayList<>();
    }

    public boolean deleteBooks(int idBooks) {
        long resutl = booksSQLite.deleteBooks(idBooks);
        if (resutl > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void loadAllBooks() {
        booksList = booksSQLite.getAllBooks();

        booksAdapter = new BooksAdapter(context, booksList,this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(booksAdapter);
    }

    @Override
    public void loadBooksByIdCategory(int idCategory) {
        booksList = booksSQLite.getBooksListBycategory(idCategory);

        booksAdapter = new BooksAdapter(context, booksList,this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(booksAdapter);
    }

    @Override
    public void onClickIteamBooks(Books books) {
        Intent intent = new Intent(context, DetailBookActivity.class);
        intent.putExtra("idBook", books.getIdBooks());
        context.startActivity(intent);
    }

    @Override
    public void onLongClickIteamBooks(Books books) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa sách " + books.getNameBook() + " Không?")
                .setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (deleteBooks(books.getIdBooks())) {
                            Toast.makeText(builder.getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            loadAllBooks();
                            dialogInterface.cancel();
                        } else {
                            Toast.makeText(builder.getContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                            dialogInterface.cancel();
                        }
                    }
                }).setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create();
        builder.show();
    }
}
