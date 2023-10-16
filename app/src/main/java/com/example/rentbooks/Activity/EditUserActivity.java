package com.example.rentbooks.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rentbooks.R;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.entity.UserLogin;
import com.example.rentbooks.keyWord.KeyWord;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserActivity extends AppCompatActivity {

    private EditText edtName,edtPhone,edtEmail, edtAddress;
    private Button btnSave;
    private CircleImageView imgAvatar;
    private UserLogin userLogin,userLogin2;
    private UserSQLite userSQLite;

    private Toolbar toolbar;

    private String uriAvatar ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        intiview();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();



        //sua thong tin nguoi dung

        btnSave.setOnClickListener(view -> {
            userLogin2 = new UserLogin();

            userLogin2.setId(KeyWord.IdUser);
            userLogin2.setName(edtName.getText().toString().trim());
            userLogin2.setPhone(edtPhone.getText().toString().trim());
            userLogin2.setEmail(edtEmail.getText().toString().trim());
            userLogin2.setAddress(edtAddress.getText().toString().trim());
            userLogin2.setAvatar(uriAvatar);

            if ( edtAddress.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty()
                    || edtEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
            } else if (!userLogin2.checkEmail()) {
                Toast.makeText(this, "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
            } else if (!userLogin2.checkPhone()) {
                Toast.makeText(this, "Phone không đúng định dạng!", Toast.LENGTH_SHORT).show();
            } else {
                userSQLite.updateUser(userLogin2);
                Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        // chon anh tu he thong
        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    if (uri != null) {
                        imgAvatar.setImageURI(uri);
                        uriAvatar = String.valueOf(uri);
                    } else {
                    }
                });

        imgAvatar.setOnClickListener(view -> {
            pickMedia.launch(new PickVisualMediaRequest());
        });

    }

    private void loadData() {
        userLogin = new UserLogin();
        userSQLite = new UserSQLite(this);
        userLogin = userSQLite.getUserByID(KeyWord.IdUser);
        edtName.setText(userLogin.getName());
        edtPhone.setText(userLogin.getPhone());
        edtEmail.setText(userLogin.getEmail());
        edtAddress.setText(userLogin.getAddress());
        if (userLogin.getAvatar().isEmpty()) {
            imgAvatar.setImageResource(R.mipmap.ic_launcher);
        } else {
            imgAvatar.setImageURI(Uri.parse(userLogin.getAvatar()));
        }
    }

    private void intiview() {
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);

        btnSave = findViewById(R.id.btnEditUser);

        imgAvatar = findViewById(R.id.imgAvatar);

        toolbar = findViewById(R.id.toolbar);
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