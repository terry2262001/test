package com.example.nguyentruongtho_dh51900920;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import FormatUtils.FormatUtil;
import Model.DonHang;

public class ThemDonHangActivity extends AppCompatActivity {
    //    private EditText etMa,etTen;
//    private TextView tvNgaySinh;
//    private ImageView imgCalender;
//    private RadioGroup rdGroup;
//    private Button btLuu;
    EditText etMa, etTen;
    TextView tvNgayDat;
    ImageView imgCalender;
    RadioGroup rdGroup;
    Button btLuu;
    Calendar calendar;
    boolean isThanhToan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_don_hang);

        etMa = findViewById(R.id.etMa);
        etTen = findViewById(R.id.etTen);
        tvNgayDat = findViewById(R.id.tvNgayDat);
        imgCalender = findViewById(R.id.imgCalender);
        rdGroup = findViewById(R.id.rdGroup);
        calendar = Calendar.getInstance();
        btLuu = findViewById(R.id.btLuu);

        imgCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(Calendar.YEAR, i);
                        calendar.set(Calendar.MONTH, i1);
                        calendar.set(Calendar.DATE, i2);
                        tvNgayDat.setText(FormatUtil.formatDate(calendar.getTime()));

                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThemDonHangActivity.this,
                        listener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();


            }
        });
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdbRoi:
                        isThanhToan = true;
                        break;
                    case R.id.rdbChua:
                        isThanhToan = false;

                }
            }
        });
        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ma = Integer.parseInt(etMa.getText().toString());
                String ten = etTen.getText().toString();
                try {
                    Date date = FormatUtil.formatStringToDate(tvNgayDat.getText().toString());
                    DonHang donHang = new DonHang(ma, ten, date, isThanhToan);
                    Intent intent = getIntent();
                    intent.putExtra("DH", donHang);
                    setResult(2, intent);
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
