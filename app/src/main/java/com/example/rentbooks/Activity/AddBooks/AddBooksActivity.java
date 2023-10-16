package com.example.rentbooks.Activity.AddBooks;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.rentbooks.Activity.AddCategory.CategoryActivity;
import com.example.rentbooks.databinding.ActivityAddBooksBinding;
import com.example.rentbooks.entity.Books;

public class AddBooksActivity extends AppCompatActivity {

    private ActivityAddBooksBinding binding;
    private AddBooksViewModel viewModel;
    private CategoryActivity categoryActivity;
    private String imgBooks = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBooksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new AddBooksViewModel(this);

        categoryActivity = new CategoryActivity();


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnChooseCategory.setOnClickListener(view -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        });

        choosePhotoBooks();
        binding.btnAddBook.setOnClickListener(view -> {
            Books books = new Books();

            books.setImageBook(imgBooks);
            books.setNameBook(binding.edtNameBook.getText().toString().trim());
            books.setCategoryBooks(categoryActivity.getNameCategory());
            books.setIdCategory(categoryActivity.getIdCategory());
            books.setAuthor(binding.edtAuthor.getText().toString().trim());

            if (!binding.edtPrice.getText().toString().isEmpty()) {
                books.setPrice(Integer.parseInt(binding.edtPrice.getText().toString().trim()));
            }
            if (!binding.edtTotal.getText().toString().isEmpty()) {
                books.setTotal(Integer.parseInt(binding.edtTotal.getText().toString().trim()));
            }
            if (viewModel.addBooks(books)) {
                finish();
            }

        });
    }

    private void choosePhotoBooks() {
        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        binding.imgBook.setImageURI(uri);
                        imgBooks = String.valueOf(uri);
                    } else {
                    }
                });
        binding.imgBook.setOnClickListener(view -> {
            pickMedia.launch(new PickVisualMediaRequest());
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.tvCategory.setText(categoryActivity.getNameCategory());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}