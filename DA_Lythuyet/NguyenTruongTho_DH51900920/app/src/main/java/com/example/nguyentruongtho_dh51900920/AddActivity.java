package com.example.nguyentruongtho_dh51900920;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.nguyentruongtho_dh51900920.Fragment.AddProductFragment;
import com.example.nguyentruongtho_dh51900920.Fragment.AddTypeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        imgClose = findViewById(R.id.imgClose);
        imgClose.setOnClickListener(view -> finish());

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager viewPager = findViewById(R.id.vpAdd);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AddTypeFragment(),"Thêm Loại Sản Phẩm");
        viewPagerAdapter.addFragment(new AddProductFragment(),"Thêm Sản Phẩm ");
        viewPager.setAdapter(viewPagerAdapter);



        tabLayout.setupWithViewPager(viewPager);




    }

    class  ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        // ctrl+o

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}