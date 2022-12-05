package vn.edu.stu.demosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import vn.edu.stu.demosqlite.model.SinhVien;
import vn.edu.stu.demosqlite.util.DBUtil;

public class MainActivity extends AppCompatActivity {
    final String DB_NAME = "dbSinhVien.db";
    final String DB_PATH_SUFIX = "/databases/";

    Button btnThem;
    ListView lvSinhVien;
    ArrayAdapter<SinhVien> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        copyDatabase();
        getDataFromDB();
    }

    private void getDataFromDB() {
        adapter.clear();
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        // Cursor cursor = database.rawQuery("SELECT * FROM SinhVien", null);
        // Cursor cursor = database.rawQuery("SELECT * FROM SinhVien WHERE namsinh >= ?", new String[]{"2000"});
//        Cursor cursor = database.query(
//                "SinhVien",
//                null, //new String[]{"ten", "namsinh"},
//                "namsinh >= ?",
//                new String[]{"2000"},
//                null,
//                null,
//                null
//        );
        Cursor cursor = database.query(
                "SinhVien",
                null, //new String[]{"ten", "namsinh"},
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            int namSinh = cursor.getInt(2);
            adapter.add(new SinhVien(ma, ten, namSinh));
        }
        cursor.close();
        database.close();
    }

    private void addControls() {
        btnThem = findViewById(R.id.btnThem);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvSinhVien.setAdapter(adapter);
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThem();
                getDataFromDB();
            }
        });
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                xuLySua(index);
                getDataFromDB();
            }
        });
        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                xuLyXoa(index);
                getDataFromDB();
                return true;
            }
        });
    }

    private void xuLySua(int index) {
        if (index >= 0 && index < adapter.getCount()) {
            SinhVien sv = adapter.getItem(index);
            Random rd = new Random();
            sv.setTen("Sinh vien " + rd.nextInt(100));
            sv.setNamSinh(1980 + rd.nextInt(30));
            ContentValues row = new ContentValues();
            row.put("ten", sv.getTen());
            row.put("namsinh", sv.getNamSinh());
            SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            int updatedCount = database.update(
                    "SinhVien",
                    row,
                    "ma = ?",
                    new String[]{sv.getMa() + ""}
            );
            database.close();

            Toast.makeText(
                    this,
                    "Đã cập nhật " + updatedCount + " dòng",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void xuLyXoa(int index) {
        if (index >= 0 && index < adapter.getCount()) {
            SinhVien sv = adapter.getItem(index);
            SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
            int deletedCount = database.delete(
                    "SinhVien",
                    "ma = ?",
                    new String[]{sv.getMa() + ""}
            );
            database.close();

            Toast.makeText(
                    this,
                    "Đã xóa " + deletedCount + " dòng",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void xuLyThem() {
        Random rd = new Random();
        String ten = "Sinh viên " + rd.nextInt(100);
        int namSinh = 1980 + rd.nextInt(30);
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        ContentValues row = new ContentValues();
        row.put("ten", ten);
        row.put("namsinh", namSinh);
        long insertedId = database.insert(
                "SinhVien",
                null,
                row
        );
        database.close();

        Toast.makeText(
                this,
                "Thêm mã mới: " + insertedId,
                Toast.LENGTH_SHORT
        ).show();
    }

    private void copyDatabase() {
        DBUtil.copyDBFileFromAssets(
                MainActivity.this,
                DB_NAME,
                DB_PATH_SUFIX
        );
    }
}