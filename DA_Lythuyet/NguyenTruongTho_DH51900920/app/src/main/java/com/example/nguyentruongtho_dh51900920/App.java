package com.example.nguyentruongtho_dh51900920;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper db;
        db = new DbHelper(this);
        db.createTableProductType();
       // db.createTableProduct();
        db.createTableProduct1();


    }
}
