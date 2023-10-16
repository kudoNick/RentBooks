package com.example.rentbooks.fragment.CartFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rentbooks.R;
import com.example.rentbooks.database.CartSQLite;
import com.example.rentbooks.databinding.FragmentCartBinding;
import com.example.rentbooks.entity.Cart;

import java.util.List;

public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private CartViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        viewModel = new CartViewModel(getContext(),binding.rcvCart);

        viewModel.loadData();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadData();
    }
}