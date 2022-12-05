package com.example.nguyentruongtho_dh51900920.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyentruongtho_dh51900920.Adapter.ProductAdapter;
import com.example.nguyentruongtho_dh51900920.Adapter.ProductTypeAdapter;
import com.example.nguyentruongtho_dh51900920.Adapter.SpinerTypeAdapter;
import com.example.nguyentruongtho_dh51900920.DbHelper;
import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.example.nguyentruongtho_dh51900920.Model.ProductType;
import com.example.nguyentruongtho_dh51900920.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class AddProductFragment extends Fragment {
    Spinner product_type;
    DbHelper dbHelper;
    ProductAdapter productTypeAdapter;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    EditText etName, etDesc, etPrice;
    ImageView imgProduct;
    Button btCancel,btAdd,btUpdate,btCancelEdit;
    SpinerTypeAdapter spinerTypeAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        product_type = view.findViewById(R.id.product_type);
        btCancelEdit = view.findViewById(R.id.btCancelEdit);
        dbHelper = new DbHelper(getContext());
        etName =view.findViewById(R.id.etName);
        etPrice =view.findViewById(R.id.etPrice);
        btUpdate = view.findViewById(R.id.btUpdate);
        etDesc = view.findViewById(R.id.etDesc);
        imgProduct =view.findViewById(R.id.imgProduct);
        btCancel =view.findViewById(R.id.btCancel);
        btAdd =view.findViewById(R.id.btAdd);
        imgProduct.setOnClickListener(view1 -> openImage());
        btCancel.setOnClickListener(view1 -> {
           referst();
        });
        loadData();
        btAdd.setOnClickListener(view1 -> {
            if (!etName.getText().toString().isEmpty() && !etPrice.getText().toString().isEmpty() && !etDesc.getText().toString().isEmpty()){
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                try {
                    double priceD = Long.parseLong(etPrice.getText().toString());
                    ProductType productType = (ProductType) product_type.getSelectedItem();

                    Product product = new Product(name, ImagetoByte(imgProduct),desc,priceD,productType.getId());
                    if (dbHelper.insertProductData1(product) > 0){
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Không thêm được, Hãy Thử Lại", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }else {
                Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            }
        

        });
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Product product = (Product) bundle.getSerializable("Product");
            btCancel.setVisibility(View.GONE);
            btCancelEdit.setVisibility(View.VISIBLE);


            etName.setText(product.getName());
            etDesc.setText(product.getDescription());
            etPrice.setText(String.valueOf(product.getPriceD()));
            product_type.setSelection(product.getTypeid()-1);

            byte[] image = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgProduct.setImageBitmap(bitmap);
            btAdd.setVisibility(View.GONE);
            btUpdate.setVisibility(View.VISIBLE);
            btUpdate.setOnClickListener(view1 -> {
                Toast.makeText(getContext(), "ddd", Toast.LENGTH_SHORT).show();
                if (!etName.getText().toString().isEmpty() && !etPrice.getText().toString().isEmpty() && !etDesc.getText().toString().isEmpty()){

                    // String price = etPrice.getText().toString();
                    try {
                        ProductType productType = (ProductType) product_type.getSelectedItem();
                        String name = etName.getText().toString();
                        String desc = etDesc.getText().toString();
                        double priceD = Long.parseLong(etPrice.getText().toString());
                        product.setName(name);
                        product.setDescription(desc);
                        product.setPriceD(priceD);
                        product.setTypeid(productType.getId());
                        product.setImage(ImagetoByte(imgProduct));
                        if (dbHelper.updateProduct1(product) > 0){
                            Toast.makeText(getContext(), "Cập nhật thành công ", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

                        }else{
                            Toast.makeText(getContext(), "Khong thể Cập Nhật", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(), "Không Được Bỏ Trống", Toast.LENGTH_SHORT).show();
                }

                btCancelEdit.setOnClickListener(view2 -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit());
                

            });



        }



        return view;


    }

    private void referst() {
        etName.setText("");
        etDesc.setText("");
        etPrice.setText("") ;
        etName.setHint("Nhập Tên Sản Phẩm");
        etDesc.setHint("Nhập Mô tả");
        etPrice.setHint("Nhập giá");
        product_type.setSelection(0);
    }


    private byte[] ImagetoByte(ImageView imgProduct) {
        Bitmap bitmap = ((BitmapDrawable)imgProduct.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    public void loadData() {
        ArrayList<ProductType> productTypes = dbHelper.getAllProductTypes();
        if (!productTypes.isEmpty()) {

            spinerTypeAdapter = new SpinerTypeAdapter(getContext(), R.layout.item_product_type, productTypes);
            product_type.setAdapter(spinerTypeAdapter);
            spinerTypeAdapter.notifyDataSetChanged();

        }
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgProduct.setImageURI(imageUri);
        }
    }
}