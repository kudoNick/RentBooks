package com.example.rentbooks.Activity.ActivityBill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rentbooks.R;
import com.example.rentbooks.database.BillSQLite;
import com.example.rentbooks.databinding.ActivityBillBinding;
import com.example.rentbooks.entity.Bill;
import com.example.rentbooks.keyWord.KeyWord;

import java.util.List;

public class BillActivity extends AppCompatActivity {

    private ActivityBillBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BillSQLite billSQLite = new BillSQLite(this);
        List<Bill> billList = billSQLite.getAllBill();
        Bill bill = billList.get(1);

        int status = bill.getStatus();
        if (status == 0) {
            binding.tvStatus.setText("Đã xác nhận đơn hàng");
        } else if (!(status == 0)){

        }
        Toast.makeText(this, String.valueOf(bill.getStatus()), Toast.LENGTH_SHORT).show();
    }@Override
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