package com.example.banhangdientu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.banhangdientu.Giaodienchinh.SHARED_PREFS;

public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo;
    public static String tendn;
    public static String hoten;
    public static String sdt;
    public static String gioitinh;
    public static String diachi;
    public static String loaitk;
    public static Boolean ktdangnhap = false;
    private static int SPLASH_SCREEN = 4000;
    SharedPreferences sharedPreferences;
    public static final  String SHARED_PREFS = "sharedPrefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageview);
        logo = findViewById(R.id.text1);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( MainActivity.this, Giaodienchinh.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
    public void loadData(){

        /*Gson gson = new Gson();
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String json = sharedPreferences.getString("listGiohang", null);
        Type type = new TypeToken<ArrayList<Sanpham>>() {}.getType();
        listGH = gson.fromJson(json, type);
        if (listGH == null) {

            listGH = new ArrayList<>();
        }*/
    }

}