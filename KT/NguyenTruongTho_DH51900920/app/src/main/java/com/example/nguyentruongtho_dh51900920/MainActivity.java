package com.example.nguyentruongtho_dh51900920;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.DonHangAdapter;
import Model.DonHang;

public class MainActivity extends AppCompatActivity {

    ListView lvDonHang;
    DonHangAdapter donHangAdapter;
    Button btThem;
    ArrayList<DonHang> donHangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDonHang = findViewById(R.id.lvDonHang);
        btThem = findViewById(R.id.btThem);
        donHangList = new ArrayList<>();
        donHangAdapter = new DonHangAdapter(MainActivity.this, donHangList);
        lvDonHang.setAdapter(donHangAdapter);

        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, ThemDonHangActivity.class), 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 2) {
                if (data.hasExtra("DH")) {
                    DonHang donHang = (DonHang) data.getSerializableExtra("DH");
                    donHangList.add(donHang);
                    donHangAdapter.notifyDataSetChanged();

                }
            }
        }
    }
}