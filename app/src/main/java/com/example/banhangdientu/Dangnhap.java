package com.example.banhangdientu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dangnhap extends AppCompatActivity {

    Button btndangnhap, btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        //----------------------
        btnthoat=findViewById(R.id.btn_thoat1);
        btndangnhap=findViewById(R.id.btn_dangnhaptk);

        //-----------------------
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //-------------------------------------------
    }
}