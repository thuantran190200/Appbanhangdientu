package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Giaodienchinh extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodienchinh);


        Fragment_trangchu fragment_trangchu = new Fragment_trangchu();
        loadFragment(fragment_trangchu);
        bottomNavigationView = findViewById(R.id.nav_bottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_trangchu:
                        loadFragment(fragment_trangchu);
                        return true;
                    case R.id.nav_danhmuc:
                        fragment = new Fragment_danhmuc();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_thongtin:
                        fragment = new Fragment_thongtin();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }






    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.continer,fragment);
        fragmentTransaction.commit();
    }
}