package com.example.rentbooks.fragment.CategoryFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentbooks.Activity.AddCategory.CategoryActivity;
import com.example.rentbooks.Activity.DetailCategory.DetailCategoryActivity;
import com.example.rentbooks.Adapter.CategoryAdapter;
import com.example.rentbooks.Interface.IClickIteamCategory;
import com.example.rentbooks.Interface.LoadDataBooks;
import com.example.rentbooks.R;
import com.example.rentbooks.databinding.FragmentCategoryBinding;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Category;
import com.example.rentbooks.keyWord.KeyWord;

import java.util.List;


public class CategoryFragment extends Fragment{

    private FragmentCategoryBinding binding;
    private CategoryViewModel viewModel;
    private Dialog dialog;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private final int location = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);

        viewModel = new CategoryViewModel(getContext(),binding.rcvCategory,location,getActivity());

        viewModel.loadCategory();



        if (!(KeyWord.PhanQuyenUser == 1)) {
            binding.fab.setVisibility(View.GONE);
        }

        binding.fab.setOnClickListener(view -> {
            viewModel.loadDialog();
        });

        return binding.getRoot();
    }
}