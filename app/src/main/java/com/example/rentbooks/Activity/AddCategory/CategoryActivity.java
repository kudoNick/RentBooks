package com.example.rentbooks.Activity.AddCategory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentbooks.Adapter.CategoryAdapter;
import com.example.rentbooks.Interface.IClickIteamCategory;
import com.example.rentbooks.R;
import com.example.rentbooks.databinding.ActivityCategoryBinding;
import com.example.rentbooks.entity.Category;
import com.example.rentbooks.fragment.CategoryFragment.CategoryViewModel;
import com.example.rentbooks.keyWord.KeyWord;

import java.util.List;

public class CategoryActivity extends AppCompatActivity{

    private ActivityCategoryBinding binding;
    private CategoryViewModel viewModel;
    public static String nameCategory;
    public static int idCategory;

    private final int location = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new CategoryViewModel(this,binding.rcvCategory,location,this);
        //set toolbar
        setSupportActionBar(binding.toolbar);
        //set button back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //load data
        viewModel.loadCategory();
        //check phan quyen ( admin moi co quyen them,sua,xoa)
        phanQuyen();
    }

    public void phanQuyen() {
        if (!(KeyWord.PhanQuyenUser == 1)) {
            binding.fab.setVisibility(View.GONE);
        }

        binding.fab.setOnClickListener(view -> {
            viewModel.loadDialog();
        });
    }
    public String getNameCategory() {
        return nameCategory;
    }
    public int getIdCategory() {
        return idCategory;
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