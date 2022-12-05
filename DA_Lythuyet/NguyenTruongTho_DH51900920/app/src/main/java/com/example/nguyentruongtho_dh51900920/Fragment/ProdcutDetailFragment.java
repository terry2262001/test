package com.example.nguyentruongtho_dh51900920.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.example.nguyentruongtho_dh51900920.R;
import com.example.nguyentruongtho_dh51900920.Utils.FormatUtil;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ProdcutDetailFragment extends Fragment {

ImageView imgOptions,imgProduct;
TextView tvName,tvPrice,tvDesc,tvType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prodcut_detail, container, false);
        imgOptions = view.findViewById(R.id.imgOptions);
        imgProduct = view.findViewById(R.id.imgProduct);
        tvName = view.findViewById(R.id.tvName);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvDesc = view.findViewById(R.id.tvDesc);
        tvType = view.findViewById(R.id.tvType);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Product product = (Product) bundle.getSerializable("Product");
            byte[] image = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgProduct.setImageBitmap(bitmap);
            tvName.setText(product.getName());
            DecimalFormat decimalFormat =new DecimalFormat("##,### VND");
            tvPrice.setText(decimalFormat.format(product.getPriceD()));
            tvType.setText(product.getType());
            tvDesc.setText(product.getDescription());


        }



       // imgOptions.setOnClickListener(view1 -> {  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();});
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}