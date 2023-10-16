package com.example.rentbooks.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentbooks.R;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.listener.StatusDataUserListener;
import com.example.rentbooks.entity.UserLogin;
import com.example.rentbooks.presenter.UserPresenter;

public class SignAcivity extends AppCompatActivity implements StatusDataUserListener {

    private EditText edtUserName,edtPassWord,edtPhone,edtEmail,edtAddress;
    private Button btnSign;
    private UserSQLite userSQLite;
    private UserLogin userLogin;
    public static Activity SignActivity;

    private UserPresenter userPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_acivity);
        thamChieu();
        userPresenter = new UserPresenter( this,this);

        btnSign.setOnClickListener(view -> {
            userLogin = new UserLogin();

            userLogin.setName("");
            userLogin.setAvatar("");
            userLogin.setUserName(edtUserName.getText().toString().trim());
            userLogin.setPassWord(edtPassWord.getText().toString().trim());
            userLogin.setPhone(edtPhone.getText().toString().trim());
            userLogin.setEmail(edtEmail.getText().toString().trim());
            userLogin.setAddress(edtAddress.getText().toString().trim());

            if (edtUserName.getText().toString().isEmpty() || edtAddress.getText().toString().isEmpty()
                    || edtPhone.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtPassWord.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
            } else if (!userLogin.checkPassWord()) {
                Toast.makeText(SignActivity, "Mật khẩu phải trên 6 kí tự!", Toast.LENGTH_SHORT).show();
            } else if (!userLogin.checkEmail()) {
                Toast.makeText(SignActivity, "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
            } else if (!userLogin.checkPhone()) {
                Toast.makeText(SignActivity, "Phone không đúng định dạng!", Toast.LENGTH_SHORT).show();
            }
            else {
                userPresenter.signDataUser(userLogin);
            }
        });


    }

    @Override
    public void onLoadUserSuccess() {
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadUserFail() {
        Toast.makeText(this, "Tên tài khoản đã có người dùng!", Toast.LENGTH_SHORT).show();
    }
    private void thamChieu() {
        SignActivity = this;

        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);

        btnSign = findViewById(R.id.btnSignUser);

        userSQLite = new UserSQLite(this);

    }
}