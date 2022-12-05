package com.example.nguyentruongtho_dh51900920;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nguyentruongtho_dh51900920.Adapter.ProductAdapter;
import com.example.nguyentruongtho_dh51900920.Fragment.FilterFragment;
import com.example.nguyentruongtho_dh51900920.Fragment.HomeFragment;
import com.example.nguyentruongtho_dh51900920.Fragment.ProdcutDetailFragment;
import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView ;
    Fragment selectedFragment  = null;
    Toolbar toolbar ;
    String title = "Quản Lý Truyện ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_add:
                    startActivity(new Intent(MainActivity.this, AddActivity.class));
                    break;
                case R.id.nav_type:
                    selectedFragment = new FilterFragment();
                    break  ;
            }
            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            }
            return true;
        }
    };


}