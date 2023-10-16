package com.example.rentbooks.Activity.AddBooks;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.rentbooks.database.BooksSQLite;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.User;

public class AddBooksViewModel extends ViewModel {
    private Books books;
    private BooksSQLite booksSQLite;
    private Context context;

    public AddBooksViewModel(Context context) {
//        books = new Books();
        booksSQLite = new BooksSQLite(context);
        this.context = context;
    }

    public boolean addBooks(Books books) {

        if (!books.isCheckEmpty()) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin, bao gồm cả ảnh bía sách", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (booksSQLite.checkNameBooks(books.getNameBook())) {
                Long result = booksSQLite.inertBooks(books);
                if (result > 0) {
                    Toast.makeText(context, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(context, "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(context, "Sách " + books.getNameBook() + " đã có", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
