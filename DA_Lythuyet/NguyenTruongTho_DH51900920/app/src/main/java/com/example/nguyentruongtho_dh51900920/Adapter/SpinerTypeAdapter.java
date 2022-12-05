package com.example.nguyentruongtho_dh51900920.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Layer;

import com.example.nguyentruongtho_dh51900920.Model.ProductType;
import com.example.nguyentruongtho_dh51900920.R;

import java.util.ArrayList;
import java.util.List;

public class SpinerTypeAdapter extends ArrayAdapter<ProductType> {
    private Context context;
    private int layout;
    private List<ProductType> productTypes;

    public SpinerTypeAdapter(@NonNull Context context,int rescource, @NonNull List<ProductType> objects) {
        super(context, 0, objects);
        this.context = context;
        this.layout = rescource;
        this.productTypes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProductType productType = productTypes.get(position);
        View view = LayoutInflater.from(context).inflate(layout,null);
        TextView textView   = view.findViewById(R.id.product_type);
        textView.setText(productType.getName());
        return view;

    }
}
