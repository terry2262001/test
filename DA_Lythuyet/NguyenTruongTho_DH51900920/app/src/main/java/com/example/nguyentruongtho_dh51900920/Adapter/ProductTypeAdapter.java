package com.example.nguyentruongtho_dh51900920.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentruongtho_dh51900920.Model.ProductType;
import com.example.nguyentruongtho_dh51900920.R;

import java.util.ArrayList;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ProTypeVH> {
    private Context mContext;
    private ArrayList<ProductType> protypeList;
    private onClickItem listener;

    public ProductTypeAdapter(Context mContext, ArrayList<ProductType> protypeList, onClickItem listener) {
        this.mContext = mContext;
        this.protypeList = protypeList;
        this.listener = listener;
    }

    public ProductTypeAdapter(Context mContext, ArrayList<ProductType> protypeList) {
        this.mContext = mContext;
        this.protypeList = protypeList;
    }

    @NonNull
    @Override
    public ProTypeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product_type,parent,false);
        return new ProductTypeAdapter.ProTypeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProTypeVH holder, int position) {
        ProductType productType = protypeList.get(position);
        holder.product_type.setText(productType.getName());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onItemLongClickItem(productType);
                return false;

            }
        });



    }

    @Override
    public int getItemCount() {
        return protypeList.size();
    }

    public class ProTypeVH extends RecyclerView.ViewHolder {
        TextView product_type;
        public ProTypeVH(@NonNull View itemView) {
            super(itemView);
            product_type = itemView.findViewById(R.id.product_type);
        }
    }
    public interface onClickItem{
        void onItemLongClickItem(ProductType productType);
    }
}
