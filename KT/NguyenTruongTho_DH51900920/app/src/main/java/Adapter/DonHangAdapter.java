package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nguyentruongtho_dh51900920.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import FormatUtils.FormatUtil;
import Model.DonHang;

public class DonHangAdapter extends ArrayAdapter<DonHang> {
    private Context mContext;
    private ArrayList<DonHang> donHangList;

    public DonHangAdapter(@NonNull Context context, @NonNull ArrayList<DonHang> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.donHangList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_them_don_hang, parent, false);
        DonHang donHang = donHangList.get(position);

        TextView tvMaKH = convertView.findViewById(R.id.tvMaKH);
        TextView tvTenKH = convertView.findViewById(R.id.tvTenKH);
        TextView tvNgayDatKH = convertView.findViewById(R.id.tvNgayDatKH);
        TextView tvThanhToanKH = convertView.findViewById(R.id.tvThanhToanKH);

        tvMaKH.setText(String.valueOf(donHang.getMa()));
        tvTenKH.setText(donHang.getTenKH());
        tvNgayDatKH.setText(FormatUtil.formatDate(donHang.getNgayDat()));
        if (donHang.isDaThanhToan()) {
            tvThanhToanKH.setText("Rồi");
        } else {
            tvThanhToanKH.setText("Chưa");
        }


        return convertView;
    }
}
