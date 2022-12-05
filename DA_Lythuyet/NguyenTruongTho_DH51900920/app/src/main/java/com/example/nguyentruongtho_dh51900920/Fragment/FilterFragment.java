package com.example.nguyentruongtho_dh51900920.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyentruongtho_dh51900920.AboutActivity;
import com.example.nguyentruongtho_dh51900920.Adapter.ProductTypeAdapter;
import com.example.nguyentruongtho_dh51900920.DbHelper;
import com.example.nguyentruongtho_dh51900920.LogInActivity;
import com.example.nguyentruongtho_dh51900920.MainActivity;
import com.example.nguyentruongtho_dh51900920.Model.ProductType;
import com.example.nguyentruongtho_dh51900920.R;

import java.util.ArrayList;


public class FilterFragment extends Fragment implements ProductTypeAdapter.onClickItem{
    RecyclerView rvType;
    ArrayList<ProductType> productTypeList;
    ProductTypeAdapter productTypeAdapter;
    DbHelper dbHelper;
    ProductType productTypeLong ;
    String filename ="caches";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        //
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        //

        dbHelper = new DbHelper(getContext());
        rvType = view.findViewById(R.id.rvType);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvType.setLayoutManager(linearLayoutManager);
        rvType.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        productTypeList =  new ArrayList<>();

        productTypeAdapter = new ProductTypeAdapter(getContext(), productTypeList,this);
        rvType.setAdapter(productTypeAdapter);
        init();
        registerForContextMenu(rvType);


        return view;
    }





    public void init() {
        productTypeList.clear();

        productTypeList.addAll(dbHelper.getAllProductTypes());
        productTypeAdapter.notifyDataSetChanged();


    }

//    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("ID = " +productTypeLong.getId() + "?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbHelper.deleteProductType(productTypeLong.getId())> 0){
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            productTypeAdapter.notifyDataSetChanged();
                            init();
                        }

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true ;
            case R.id.mnu_edit:
                AddTypeFragment  addTypeFragment =  new AddTypeFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProductLong",productTypeLong);
                addTypeFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,addTypeFragment).commit();
            default:
                return super.onContextItemSelected(item);

        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_option, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnu_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break ;
            case R.id.mnu_logout:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(filename, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();//xoa sach
                editor.commit();
                startActivity(new Intent(getActivity(), LogInActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onItemLongClickItem(ProductType productType) {
        productTypeLong = productType;

    }
}