package com.example.rentbooks.Activity.DetailCategory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rentbooks.Adapter.BooksAdapter;
import com.example.rentbooks.Adapter.CategoryAdapter;
import com.example.rentbooks.R;
import com.example.rentbooks.databinding.ActivityDetailCategoryBinding;
import com.example.rentbooks.databinding.FragmentBooksBinding;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.fragment.BooksFragment.BooksViewModel;

import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity {

    private ActivityDetailCategoryBinding binding;
    private BooksViewModel viewModel;
    private String nameCate;
    private int idCate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new BooksViewModel(this,binding.rcvBooks);

        Intent intent = getIntent();
        nameCate = intent.getStringExtra("nameCate");
        idCate = intent.getIntExtra("idCate", 0);

        viewModel.loadBooksByIdCategory(idCate);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thể loại: " + nameCate);

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