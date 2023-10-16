package com.example.rentbooks.entity;

import android.content.Context;
import android.widget.Toast;

public class Category  {
    private int IdCategory;
    private String NameCategoty;

    public String getNameCategoty() {
        return NameCategoty;
    }

    public void setNameCategoty(String nameCategoty) {
        NameCategoty = nameCategoty;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }

    public boolean isCheck(Context context) {
        if (getNameCategoty().isEmpty()) {
            Toast.makeText(context, "Vui long k de trong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" +
                "IdCategory=" + IdCategory +
                ", NameCategoty='" + NameCategoty + '\'' +
                '}';
    }
}
