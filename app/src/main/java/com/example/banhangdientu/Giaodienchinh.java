package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Giaodienchinh extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Kiemtraketnoi_Internet kiemtraketnoi_internet = new Kiemtraketnoi_Internet();
    public static ArrayList<Sanpham> listspGiohang = new ArrayList<>();
    public static String id;
    SharedPreferences sharedPreferences;
    public static final  String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodienchinh);

        loadData();

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

                    case R.id.nav_giohang:
                        fragment = new Fragment_giohang();
                        loadFragment(fragment);
                        return true;

                    case R.id.nav_thongtin:
                        fragment = new Toi();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }
    //kiểm tra kết nối mạng
   /* @Override
    protected void onStart() {
        IntentFilter filter =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(kiemtraketnoi_internet,filter);
        super.onStart();
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.SkyBlue));
        }
    }
    // kiểm tra kết nối mạng
   /* @Override
    protected void onStop() {
        unregisterReceiver(kiemtraketnoi_internet);
        super.onStop();
    }*/


    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.continer,fragment);
        fragmentTransaction.commit();
    }

    public void loadData(){
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        id = sharedPreferences.getString("id",null);
        /*ktdangnhap = sharedPreferences.getBoolean("ktdangnhap",false);
        hoten = sharedPreferences.getString("hoten",null);
        sdt = sharedPreferences.getString("sdt",null);
        diachi = sharedPreferences.getString("diachi",null);
        gioitinh = sharedPreferences.getString("gioitinh",null);
        loaitk = sharedPreferences.getString("loaitk",null);*/
        Gson gson = new Gson();
        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("listGH", null);
        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<Sanpham>>() {}.getType();
        listspGiohang = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (listspGiohang == null) {
            // if the array list is empty
            // creating a new array list.
            listspGiohang = new ArrayList<>();
        }

    }
}