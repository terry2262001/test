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
import androidx.fragment.app.FragmentActivity;
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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.nguyentruongtho_dh51900920.AboutActivity;
import com.example.nguyentruongtho_dh51900920.Adapter.ProductAdapter;
import com.example.nguyentruongtho_dh51900920.Adapter.ProductTypeAdapter;
import com.example.nguyentruongtho_dh51900920.DbHelper;
import com.example.nguyentruongtho_dh51900920.LogInActivity;
import com.example.nguyentruongtho_dh51900920.MainActivity;
import com.example.nguyentruongtho_dh51900920.Model.Product;
import com.example.nguyentruongtho_dh51900920.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements ProductAdapter.onItemClickListener {
    RecyclerView rvProdcut;
    ArrayList<Product> productList;
    ProductAdapter productAdapter;
    DbHelper dbHelper;
    int positon;
    Product productLong;
    String filename ="caches";


    final FragmentActivity activity = (FragmentActivity) getContext();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        dbHelper = new DbHelper(getContext());
        rvProdcut = view.findViewById(R.id.rvProduct);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvProdcut.setLayoutManager(linearLayoutManager);
        rvProdcut.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        productList = new ArrayList<>();

        productAdapter = new ProductAdapter(getContext(), productList, this);
        rvProdcut.setAdapter(productAdapter);
        init();
        registerForContextMenu(rvProdcut);
        setHasOptionsMenu(true);
        return view;
    }


    private void init() {
        productList.clear();
        productList.addAll(dbHelper.getAllProductSQL1());
        productAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public void OnItemClick(Product product, int pos) {

        ProdcutDetailFragment prodFragment = new ProdcutDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Product", product);
        prodFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, prodFragment).commit();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_edit:
                AddProductFragment prodFragment = new AddProductFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("Product", productLong);
                prodFragment.setArguments(bundle);


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, prodFragment).commit();

                return true;

            case R.id.mnu_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("ID = " + productLong.getId() + "?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dbHelper.deleteProduct(productLong.getId()) > 0) {
                            Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
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


                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void OnItemLongClick(Product product, int pos) {
        positon = pos;
        productLong = product;

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
    public void onResume() {
        super.onResume();
        init();

    }

    @Override
    public void onPause() {
        super.onPause();
        init();

    }


}