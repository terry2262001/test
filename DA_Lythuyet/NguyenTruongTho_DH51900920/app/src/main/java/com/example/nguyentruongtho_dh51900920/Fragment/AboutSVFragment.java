package com.example.nguyentruongtho_dh51900920.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentruongtho_dh51900920.R;

public class AboutSVFragment extends Fragment {
    ImageView imgCall;
    TextView tvSdt;
    ImageView imgMaps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about_s_v, container, false);
        tvSdt = view.findViewById(R.id.tvSdt);
        imgMaps = view.findViewById(R.id.imgMaps);
        imgMaps.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frLAbout, new MapsFragment()).commit();
        });

        imgCall = view.findViewById(R.id.imgCall);
        imgCall.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+tvSdt.getText().toString()));
            startActivity(intent);
        });

        return view;
    }
}