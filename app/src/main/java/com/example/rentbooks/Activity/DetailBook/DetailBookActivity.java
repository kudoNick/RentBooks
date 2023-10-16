package com.example.rentbooks.Activity.DetailBook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentbooks.R;
import com.example.rentbooks.databinding.ActivityDetailBookBinding;
import com.example.rentbooks.entity.Bill;
import com.example.rentbooks.entity.Books;
import com.example.rentbooks.entity.Cart;
import com.example.rentbooks.keyWord.KeyWord;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailBookActivity extends AppCompatActivity {
    private ActivityDetailBookBinding binding;
    private DetailBooksViewModel viewModel;
    private static int amout = 1 ;
    private static String stotalPrice;
    private static int totalPrice;
    private static int priceBook;
    private static int total;
    private static int lastTotal;
    private int idBook = 0;
    private int idCart = 0;
    private Books books;
    private Cart cart;
    private Bill bill;
    private Locale locale;
    private NumberFormat numberFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new DetailBooksViewModel(this);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData();
        logicSoluong();
        btnAddCart();
        btnBuyBook();
    }
    private void btnBuyBook() {

        if (total <= 0) {
            binding.btnBuy.setEnabled(false);
            binding.btnAddCart.setEnabled(false);
        } else {
            binding.btnBuy.setEnabled(true);
            binding.btnAddCart.setEnabled(true);
        }
        binding.btnBuy.setOnClickListener(view -> {
            if (!(idBook == 0)) {
                bill = new Bill();
                bill.setIdUser(KeyWord.IdUser);
                bill.setStatus(0); //0 là người bán chưa xác nhận, 1 là người bán đã xác nhận
                bill.setSoLuong(amout);
                bill.setTotalPrice(totalPrice);

                bill.setNameBook(books.getNameBook());
                bill.setAuthor(books.getAuthor());
                bill.setCategoryBooks(books.getCategoryBooks());
                bill.setImageBook(books.getImageBook());
                bill.setPrice(books.getPrice());


                Locale localeVN = new Locale("vi", "VN");
                NumberFormat vn = NumberFormat.getInstance(localeVN);
                String price = vn.format(bill.getTotalPrice());

                lastTotal = total - amout;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bạn có muốn mua sách " + bill.getNameBook() + " với giá là " + price + " VNĐ không?")
                        .setPositiveButton("Mua", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (viewModel.buyBook(bill)) {
                                    viewModel.updateTotalBooks(books.getIdBooks(),lastTotal);
                                    Toast.makeText(DetailBookActivity.this, "Mua sách " + bill.getNameBook() + " thành công!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    dialogInterface.cancel();
                                    Toast.makeText(DetailBookActivity.this, "Mua " + bill.getNameBook() + " Thất bại!", Toast.LENGTH_SHORT).show();
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
            else if (!(idCart == 0)) {
                bill = new Bill();
                bill.setIdUser(KeyWord.IdUser);
                bill.setStatus(0); //0 là người bán chưa xác nhận, 1 là người bán đã xác nhận
                bill.setSoLuong(amout);
                bill.setTotalPrice(totalPrice);

                bill.setNameBook(cart.getNameBook());
                bill.setAuthor(cart.getAuthor());
                bill.setCategoryBooks(cart.getCategoryBooks());
                bill.setImageBook(cart.getImageBook());
                bill.setPrice(cart.getPrice());

                Locale localeVN = new Locale("vi", "VN");
                NumberFormat vn = NumberFormat.getInstance(localeVN);
                String price = vn.format(bill.getTotalPrice());

                lastTotal = cart.getTotal() - amout;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bạn có muốn mua sách " + bill.getNameBook() + " với giá là " + price + " VNĐ không?")
                        .setPositiveButton("Mua", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (viewModel.buyBook(bill)) {
                                    if (viewModel.dellCart(cart.getIdCart())) {
                                        viewModel.updateTotalBooks(idCart, lastTotal);
                                        finish();
                                    }
                                } else {
                                    dialogInterface.cancel();
                                }
                            }
                        }).setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }
    private void btnAddCart() {
        binding.btnAddCart.setOnClickListener(view -> {

            cart = new Cart();

            cart.setIdBooks(idBook);
            cart.setNameBook(books.getNameBook());
            cart.setAuthor(books.getAuthor());
            cart.setImageBook(books.getImageBook());
            cart.setPrice(books.getPrice());
            cart.setTotal(books.getTotal());

            cart.setIdUser(KeyWord.IdUser);
            cart.setSoLuong(amout);
            cart.setPriceFinal(totalPrice);
            cart.setCheckBuy(0);

            Boolean aBoolean = viewModel.addCart(cart);
            if (aBoolean) {
                Toast.makeText(this, "Đã thêm sách " + books.getNameBook() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Thêm sách vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void loadData() {
        locale = new Locale("vi", "VN");
        numberFormat = NumberFormat.getInstance(locale);
        Intent intent = getIntent();

        idBook = intent.getIntExtra("idBook", 0);
        idCart = intent.getIntExtra("idCart",0);

        if (!(idBook ==0)) {
            books = viewModel.getBookByIdBook(idBook);

            binding.tvNameBooks.setText("Tên sách: " + books.getNameBook());
            binding.tvAuthor.setText("Tên tác giả: " + books.getAuthor());

            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);
            String price = vn.format(books.getPrice());
            binding.tvPrice.setText("Giá: " + price + " VNĐ");

            binding.tvTotal.setText("Kho: " + books.getTotal());
            binding.imgBook.setImageURI(Uri.parse(books.getImageBook()));

            total = books.getTotal();
            priceBook = books.getPrice();

            totalPrice = amout * priceBook;
            stotalPrice = numberFormat.format(totalPrice);
            binding.tvTotalPrice.setText(stotalPrice +" VNĐ");
        } else if (!(idCart == 0)) {
            cart = viewModel.getBookCartByIdBook(idCart);

            binding.tvNameBooks.setText("Tên sách: " + cart.getNameBook());
            binding.tvAuthor.setText("Tên tác giả: " + cart.getAuthor());

            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);
            String price = vn.format(cart.getPrice());
            binding.tvPrice.setText("Giá: " + price + " VNĐ");

            binding.tvTotal.setText("Kho: " + cart.getTotal());
            binding.imgBook.setImageURI(Uri.parse(cart.getImageBook()));

            total = cart.getTotal();
            priceBook = cart.getPrice();
            amout = cart.getSoLuong();

            totalPrice = cart.getPriceFinal();
            stotalPrice = numberFormat.format(totalPrice);
            binding.tvTotalPrice.setText(stotalPrice +" VNĐ");

            binding.edtAmount.setText(String.valueOf(cart.getSoLuong()));
            binding.btnAddCart.setVisibility(View.GONE);
        }



    }
    private void logicSoluong() {
        binding.edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    amout = Integer.parseInt(String.valueOf(editable));
                    totalPrice = amout * priceBook;
                    stotalPrice = numberFormat.format(totalPrice);
                    binding.tvTotalPrice.setText(stotalPrice +" VNĐ");

                    if (amout > total) {
                        binding.edtAmount.setText(String.valueOf(total));
                        totalPrice = amout * priceBook;
                        stotalPrice = numberFormat.format(totalPrice);
                        binding.tvTotalPrice.setText(stotalPrice +" VNĐ");
                    }
                } catch (Exception e) {
                    amout = 1;
                    binding.edtAmount.setText(String.valueOf(amout));
                    totalPrice = amout * priceBook;
                    stotalPrice = numberFormat.format(totalPrice);
                    binding.tvTotalPrice.setText(stotalPrice +" VNĐ");
                }
            }
        });

        binding.imgBtnDow.setOnClickListener(view -> {
            if (amout > 1) {
                amout--;
                binding.edtAmount.setText(String.valueOf(amout));
                totalPrice = amout * priceBook;
                stotalPrice = numberFormat.format(totalPrice);
                binding.tvTotalPrice.setText(stotalPrice +" VNĐ");
            }
        });
        binding.imgBtnUp.setOnClickListener(view -> {
            if (amout < 20) {
                amout++;
                binding.edtAmount.setText(String.valueOf(amout));
                totalPrice = amout * priceBook;
                stotalPrice = numberFormat.format(totalPrice);
                binding.tvTotalPrice.setText(stotalPrice +" VNĐ");
            }
        });
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
    @Override
    protected void onStop() {
        super.onStop();
        amout = 1 ;
        stotalPrice = "1";
        priceBook = 1;
        totalPrice = 1;
        total = 1;
        lastTotal = 0;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        amout = 1 ;
        stotalPrice = "1";
        priceBook = 1;
        totalPrice = 1;
        total = 1;
        lastTotal = 0;
    }
}