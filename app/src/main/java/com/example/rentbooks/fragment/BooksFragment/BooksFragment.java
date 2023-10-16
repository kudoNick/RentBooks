package com.example.rentbooks.fragment.BooksFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentbooks.Activity.AddBooks.AddBooksActivity;
import com.example.rentbooks.databinding.FragmentBooksBinding;
import com.example.rentbooks.keyWord.KeyWord;

public class BooksFragment extends Fragment {

    private FragmentBooksBinding binding;
    private BooksViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);

        viewModel = new BooksViewModel(getContext(),binding.rcvBooks);

        if (KeyWord.PhanQuyenUser == 1) {
            binding.fab.setVisibility(View.VISIBLE);
        } else {
            binding.fab.setVisibility(View.GONE);
        }

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AddBooksActivity.class);
            startActivity(intent);
        });
        viewModel.loadAllBooks();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadAllBooks();
    }

}