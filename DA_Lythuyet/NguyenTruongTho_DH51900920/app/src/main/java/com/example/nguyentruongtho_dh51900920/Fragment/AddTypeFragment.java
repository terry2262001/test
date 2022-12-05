package com.example.nguyentruongtho_dh51900920.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyentruongtho_dh51900920.DbHelper;
import com.example.nguyentruongtho_dh51900920.MainActivity;
import com.example.nguyentruongtho_dh51900920.Model.ProductType;
import com.example.nguyentruongtho_dh51900920.R;

public class AddTypeFragment extends DialogFragment {
    Button btCancel, btAdd,btUpdate,btCancelEdit;
    EditText etAddProductType;
    DbHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_type, container, false);
        btCancel = view.findViewById(R.id.btCancel);
        btAdd = view.findViewById(R.id.btAdd);
        btUpdate = view.findViewById(R.id.btUpdate);
        etAddProductType = view.findViewById(R.id.etAddProductType);
        btCancelEdit = view.findViewById(R.id.btCancelEdit);
        dbHelper = new DbHelper(getContext());
        btCancel.setOnClickListener(view1 -> {

            etAddProductType.setText("");
            etAddProductType.setHint("Nhập loại sản phẩm");
        });
        Bundle bundle = this.getArguments();
        if (bundle != null ){
            btUpdate.setVisibility(View.VISIBLE);
            btAdd.setVisibility(View.GONE);
            btCancelEdit.setVisibility(View.VISIBLE);
            btCancel.setVisibility(View.GONE);
            btCancelEdit.setOnClickListener(view1 -> {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FilterFragment()).commit();
            });


           ProductType productType = (ProductType) bundle.getSerializable("ProductLong");
           etAddProductType.setText(productType.getName());
           btUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (!etAddProductType.getText().toString().isEmpty()){
                       productType.setName(etAddProductType.getText().toString());
                       if (dbHelper.updateProductType(productType) > 0){
                           Toast.makeText(getContext(), "Cập nhật thành công ", Toast.LENGTH_SHORT).show();
                           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FilterFragment()).commit();


                       }else {
                           Toast.makeText(getContext(), "Không thể cập nhật", Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       Toast.makeText(getContext(), "Loại sản phẩm không được nỏ trống !", Toast.LENGTH_SHORT).show();
                   }

               }
           });
        }
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etAddProductType.getText().toString().isEmpty()) {
                    ProductType productType = new ProductType(etAddProductType.getText().toString());

                    if (dbHelper.insertProductType(productType) > 0) {
                        Toast.makeText(getContext(), "Thêm Thành Công ", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        AddProductFragment currentFragment = (AddProductFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.vpAdd);
                        currentFragment.loadData();
                        etAddProductType.setText("");
                        etAddProductType.setHint("Nhập loại sản phẩm");


                        
                    } else {
                        Toast.makeText(getContext(), "Không Thể Thêm, Hãy Thử Lại", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getContext(), "Loại sản phẩm không được bỏ trống ", Toast.LENGTH_SHORT).show();

                }


            }
        });


        return view;
    }
}