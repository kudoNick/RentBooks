package com.example.rentbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentbooks.Activity.ActivityBill.BillActivity;
import com.example.rentbooks.Activity.LoginActivity;
import com.example.rentbooks.database.UserSQLite;
import com.example.rentbooks.entity.UserLogin;
import com.example.rentbooks.keyWord.KeyWord;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private UserSQLite userSQLite;
    private UserLogin userLogin;
    private  DrawerLayout drawerLayout;
    private TextView tvName;
    private CircleImageView imgAvatar;
    private NavigationView navigationView;
    private View view;
    private ImageButton imgBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationDrawer();
        userSQLite = new UserSQLite(this);
        view = navigationView.getHeaderView(0);
        tvName = view.findViewById(R.id.tvName);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        loadData();

        //xem data
        List<UserLogin> userLoginList = userSQLite.getAllUser();
        Log.e("Main", String.valueOf(userLoginList));
        Toast.makeText(this, "IdUer = " + KeyWord.IdUser + " " + "Phan quyen = " + KeyWord.PhanQuyenUser, Toast.LENGTH_SHORT).show();
        imgBill = findViewById(R.id.imgBill);
        imgBill.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, BillActivity.class);
            startActivity(intent);
        });
    }


    private void loadData() {
        userLogin = userSQLite.getUserByID(KeyWord.IdUser);

        if (userLogin.getName().isEmpty()) {
            tvName.setText("Name: ");
        } else {
            tvName.setText(userLogin.getName());
        }
        if (userLogin.getAvatar().isEmpty()) {
            imgAvatar.setImageResource(R.mipmap.ic_launcher);
        } else {
            imgAvatar.setImageURI(Uri.parse(userLogin.getAvatar()));
        }
    }
    private void navigationDrawer() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);


        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_book,R.id.nav_CategoryBook, R.id.nav_cart,R.id.nav_user)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id==R.id.nav_logout){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = LoginActivity.sharedPreferences.edit();
                    editor.remove(KeyWord.ID_USER);
                    editor.remove(KeyWord.PHANQUYEN_USER);
                    editor.apply();
                    finish();

                }
                //This is for maintaining the behavior of the Navigation view
                NavigationUI.onNavDestinationSelected(menuItem,navController);
                //This is for closing the drawer after acting on it
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    //duy tri trang thai navigation
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onRestart() {
        loadData();
        super.onRestart();
    }
}