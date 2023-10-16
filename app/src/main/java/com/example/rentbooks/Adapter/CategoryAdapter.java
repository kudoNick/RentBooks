package com.example.rentbooks.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentbooks.Interface.IClickIteamCategory;
import com.example.rentbooks.databinding.ItemCategoryBinding;
import com.example.rentbooks.entity.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Category category;
    private ItemCategoryBinding binding;
    private LayoutInflater context;
    private IClickIteamCategory iClickIteamCategory;


    public CategoryAdapter(List<Category> categoryList, LayoutInflater context,IClickIteamCategory iClickIteamCategory) {
        this.categoryList = categoryList;
        this.context = context;
        this.iClickIteamCategory = iClickIteamCategory;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCategoryBinding.inflate(context, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        category = categoryList.get(position);
        if (category == null) {
            return;
        }
        binding.tvNameCategory.setText(category.getNameCategoty());

        binding.layout.setOnClickListener(view -> {
            iClickIteamCategory.onClickIteamCategory(categoryList.get(position));
        });

        binding.layout.setOnLongClickListener(view -> {
            iClickIteamCategory.onLongClickIteamCategoty(categoryList.get(position));
            return true;
        });
        

    }

    @Override
    public int getItemCount() {
        if (categoryList != null) {
            return categoryList.size();
        }
        return 0;
    }

}
