package com.example.rentbooks.fragment.CategoryFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rentbooks.Activity.AddCategory.CategoryActivity;
import com.example.rentbooks.Activity.DetailCategory.DetailCategoryActivity;
import com.example.rentbooks.Adapter.CategoryAdapter;
import com.example.rentbooks.Interface.IClickIteamCategory;
import com.example.rentbooks.Interface.LoadDataCategory;
import com.example.rentbooks.R;
import com.example.rentbooks.database.CategorySQLite;
import com.example.rentbooks.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel implements LoadDataCategory,IClickIteamCategory {
    private Context context;
    private Activity activity;
    private Category category;
    private CategorySQLite categorySQLite;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rcvCategory;
    private int checkLocation;
    public CategoryViewModel(Context context,RecyclerView rcvCategory,int checkLocation,Activity activity) {
        this.context = context;
        this.rcvCategory = rcvCategory;
        this.checkLocation = checkLocation;
        this.activity = activity;
        category = new Category();
        categorySQLite = new CategorySQLite(context);
        categoryList = new ArrayList<>();
    }
    public boolean addCategory(String nameCategory) {

        category.setNameCategoty(nameCategory);

        Boolean check = categorySQLite.checkCategory(nameCategory);

        if (category.isCheck(context)) {
            if (check) {
                long result = categorySQLite.inertCategory(category);
                if (result > 0) {
                    Toast.makeText(context, "Thanh Cong", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(context, "Thể loại " + nameCategory + " đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    public boolean deleteCategory(int idCategory) {
        long result = categorySQLite.deteleCategoy(idCategory);
        if (result >0) {
            return true;
        }
        return false;
    }

    @Override
    public void loadCategory() {
        categoryList = categorySQLite.getAllCategory();
        categoryAdapter = new CategoryAdapter(categoryList,LayoutInflater.from(context), this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcvCategory.setAdapter(categoryAdapter);
        rcvCategory.setLayoutManager(gridLayoutManager);
    }
    @Override
    public void loadDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        Button btnAddCategory = dialog.findViewById(R.id.btnSave);
        EditText edtCategory = dialog.findViewById(R.id.edtCategory);

        btnAddCategory.setOnClickListener(view -> {
            if (addCategory(edtCategory.getText().toString().trim())) {
                dialog.dismiss();
                edtCategory.setText("");
                loadCategory();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickIteamCategory(Category category) {
        //Nếu checkLocation = 1 là đang dùng viewModel ở fragment, = 2 la ở activity
        if (checkLocation == 1) {
            Intent intent = new Intent(activity, DetailCategoryActivity.class);
            intent.putExtra("nameCate", category.getNameCategoty());
            intent.putExtra("idCate", category.getIdCategory());
            context.startActivity(intent);
        } else if (checkLocation == 2) {
            CategoryActivity.nameCategory = category.getNameCategoty();
            CategoryActivity.idCategory = category.getIdCategory();
            activity.finish();
        }
    }
    @Override
    public void onLongClickIteamCategoty(Category category) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Bạn có muốn xóa thể loại " + category.getNameCategoty() + " Không?")
                .setPositiveButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (deleteCategory(category.getIdCategory())) {
                            Toast.makeText(builder.getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                            loadCategory();
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
