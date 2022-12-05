package com.example.nguyentruongtho_dh51900920;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
    private Button btLogin;
    private EditText etEmail;
    private EditText etPassword;
    String filename = "caches";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        btLogin = findViewById(R.id.btLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        btLogin.setOnClickListener(view -> {
            etEmail.setText("admin@gmail.com");
            etPassword.setText("admin123");
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if (email.equals("admin@gmail.com") && password.equals("admin123")){
                startActivity(new Intent(LogInActivity.this,MainActivity.class));


            }
            SharedPreferences sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.commit();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);
        if (email != null || password != null) {
            if (email.equalsIgnoreCase("admin@gmail.com") && password.equalsIgnoreCase("admin123")) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }

    }
}