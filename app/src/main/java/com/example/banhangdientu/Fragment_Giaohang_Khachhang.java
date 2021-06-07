package com.example.banhangdientu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;


public class Fragment_Giaohang_Khachhang extends AppCompatActivity {

    DonmuaViewadapter donmuaViewadapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageButton back;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__giaohang__khachhang);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpaper);
        back = findViewById(R.id.thoat_111);
        /*Intent intent = getIntent();
        String idHD = intent.getStringExtra("idHD");*/

        donmuaViewadapter = new DonmuaViewadapter(Fragment_Giaohang_Khachhang.this.getSupportFragmentManager());
        donmuaViewadapter.addFragment(new Fragment_Giaohang_khachhang_Choxacnhan(),"Chờ giao hàng");
        //donmuaViewadapter.addFragment(new Fragment_Giaohang_khachhang_danggiao(),"Đang giao");
        donmuaViewadapter.addFragment(new Fragment_Giaohang_Khachhang_dagiao(),"Đã giao");
        donmuaViewadapter.addFragment(new Fragment_Giaohang_Khachhang_HuyDH(),"Đã hủy");
        viewPager.setAdapter(donmuaViewadapter);
        tabLayout.setupWithViewPager(viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}