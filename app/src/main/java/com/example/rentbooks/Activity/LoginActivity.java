package com.example.rentbooks.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentbooks.MainActivity;
import com.example.rentbooks.R;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.entity.UserLogin;
import com.example.rentbooks.keyWord.KeyWord;
import com.example.rentbooks.listener.StatusDataUserListener;
import com.example.rentbooks.presenter.UserPresenter;

public class LoginActivity extends AppCompatActivity implements StatusDataUserListener {

    Button btnLogin, btnSign;
    EditText edtUserName;
    EditText edtPassWord;

    UserSQLite userSQLite;
    UserLogin userLogin;

    TextView tvTrangThai;

    private CheckBox checkBox;

    private UserPresenter userPresenter;
    public static SharedPreferences sharedPreferences;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        thamChieu();

        KeyWord.PhanQuyenUser = sharedPreferences.getInt(KeyWord.PHANQUYEN_USER, 0);
        KeyWord.IdUser = sharedPreferences.getInt(KeyWord.ID_USER, 0) ;

        if (KeyWord.IdUser >= 1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnSign.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignAcivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            if (edtUserName.getText().toString().trim().isEmpty() || edtPassWord.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                login();
            }
        });

    }

    private void login() {
        boolean checkPass = userPresenter.checkGetPassWordByUserName(edtUserName.getText().toString().trim(),edtPassWord.getText().toString().trim());

        if (checkPass) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (checkBox.isChecked()) {
                KeyWord.IdUser = userPresenter.getIdUserIdByUserName(edtUserName.getText().toString().trim());
                editor.putInt(KeyWord.ID_USER, KeyWord.IdUser);
                KeyWord.PhanQuyenUser = userPresenter.getPhanQuyenByUserName(edtUserName.getText().toString().trim());
                editor.putInt(KeyWord.PHANQUYEN_USER, KeyWord.PhanQuyenUser);
                editor.apply();
            } else {
                editor.remove(KeyWord.ID_USER);
                editor.remove(KeyWord.PHANQUYEN_USER);
                editor.apply();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


        }else {
            tvTrangThai.setVisibility(View.VISIBLE);
            tvTrangThai.setText("Sai Pass");
        }
    }
    private void thamChieu() {
        sharedPreferences = getPreferences(MODE_PRIVATE);

        btnLogin = findViewById(R.id.btnLogIn);
        btnSign = findViewById(R.id.btnSign);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPass);

        userSQLite = new UserSQLite(this);
        userLogin = new UserLogin();

        tvTrangThai = findViewById(R.id.tvTrangThai);

        userPresenter = new UserPresenter(this,this);

        checkBox = findViewById(R.id.chkLuuTT);
    }

    @Override
    public void onLoadUserSuccess() {
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadUserFail() {
        Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
    }

}