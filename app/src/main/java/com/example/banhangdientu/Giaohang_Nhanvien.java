package com.example.banhangdientu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

public class Giaohang_Nhanvien extends AppCompatActivity {

    Donmua_admin_viewadapter donmua_admin_viewadapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaohang_nhanvien);

        tabLayout = findViewById(R.id.tablayout_nv);
        viewPager = findViewById(R.id.viewpaper_nv);
        back = findViewById(R.id.thoat_222);

        donmua_admin_viewadapter = new Donmua_admin_viewadapter(Giaohang_Nhanvien.this.getSupportFragmentManager());
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_choxacnhan(), "Chờ giao hàng");
        //donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_danggiao(), "Đang giao");
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_dagiao(), "Đã giao");
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_HuyDH(), "Hủy ĐH");
        viewPager.setAdapter(donmua_admin_viewadapter);
        tabLayout.setupWithViewPager(viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /*@Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.aqua));
        }
    }*/
}