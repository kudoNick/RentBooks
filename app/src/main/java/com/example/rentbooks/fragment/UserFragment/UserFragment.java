package com.example.rentbooks.fragment.UserFragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentbooks.Activity.EditUserActivity;
import com.example.rentbooks.R;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.entity.UserLogin;
import com.example.rentbooks.keyWord.KeyWord;

public class UserFragment extends Fragment {

    private ImageView imgAvatar;
    private TextView tvName,tvPhone,tvEmail,tvAddress;
    private Button btnEditUser;
    private View view;
    private UserLogin userLogin;
    private UserSQLite userSQLite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);
        thamChieu();

        // sua thong tin user
        btnEditUser.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), EditUserActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void thamChieu() {
        imgAvatar = view.findViewById(R.id.imgAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvAddress = view.findViewById(R.id.tvAddress);
        btnEditUser = view.findViewById(R.id.btnEditUser);

    }

    private void loadData() {
        userLogin = new UserLogin();
        userSQLite = new UserSQLite(getContext());
        userLogin = userSQLite.getUserByID(KeyWord.IdUser);

        tvName.setText(userLogin.getName());
        tvPhone.setText(userLogin.getPhone());
        tvEmail.setText(userLogin.getEmail());
        tvAddress.setText(userLogin.getAddress());

        if (userLogin.getAvatar().isEmpty()) {
            imgAvatar.setImageResource(R.mipmap.ic_launcher);
        } else {
            imgAvatar.setImageURI(Uri.parse(userLogin.getAvatar()));
        }
    }
    @Override
    public void onStart()
    {
        loadData();
        super.onStart();
    }

    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

}