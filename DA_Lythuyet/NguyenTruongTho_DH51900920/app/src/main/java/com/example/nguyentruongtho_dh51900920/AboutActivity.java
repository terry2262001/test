package com.example.nguyentruongtho_dh51900920;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nguyentruongtho_dh51900920.Fragment.AboutSVFragment;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportFragmentManager().beginTransaction().replace(R.id.frLAbout, new AboutSVFragment()).commit();
    }
}