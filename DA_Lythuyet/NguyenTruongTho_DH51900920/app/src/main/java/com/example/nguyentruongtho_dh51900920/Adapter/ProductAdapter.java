package com.example.nguyentruongtho_dh51900920.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.example.nguyentruongtho_dh51900920.R;
import com.example.nguyentruongtho_dh51900920.Utils.FormatUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {
    private Context mContext;
    private ArrayList<Product> productList;
    onItemClickListener listener;

    public ProductAdapter(Context mContext, ArrayList<Product> productList, onItemClickListener listener) {
        this.mContext = mContext;
        this.productList = productList;
        this.listener = listener;
    }

    public ProductAdapter(Context mContext, ArrayList<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }



    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item,parent,false);
        return new ProductAdapter.ProductVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        Product product = productList.get(position);
        byte[] image = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imgProduct.setImageBitmap(bitmap);
        holder.tvName.setText(product.getName());


       // holder.tvPrice.setText(decimalFormat.format(product.getPrice()));
        DecimalFormat decimalFormat =new DecimalFormat("##,### VND");
        holder.tvPrice.setText(decimalFormat.format(product.getPriceD()));
        holder.tvType.setText(product.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(product, holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.OnItemLongClick(product,holder.getAdapterPosition());
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName,tvPrice,tvType;
        public ProductVH(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvType  = itemView.findViewById(R.id.tvType);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
    public interface onItemClickListener{
        void OnItemClick( Product product, int pos);
        void OnItemLongClick(Product product, int pos);
    }
}
