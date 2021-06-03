package com.example.banhangdientu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChitietSanpham extends AppCompatActivity {

    ImageView loadhinh, btn_back;
    TextView loadtensp, loadgiasp, loadsoluongsp, loadmota;
    Button btn_add_giohang, btn_muangay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sanpham);

        //----------------------------------------------
        btn_back = findViewById(R.id.btn_back_ctsp);
        loadhinh = findViewById(R.id.load_hinh);
        loadtensp = findViewById(R.id.load_tensp);
        loadgiasp = findViewById(R.id.load_giasp);
        loadsoluongsp = findViewById(R.id.load_soluongsp);
        loadmota = findViewById(R.id.load_mota);
        btn_add_giohang = findViewById(R.id.btn_addgiohang);
        btn_muangay = findViewById(R.id.btn_muangay);
        //**************
        loaddata();

        //----------------------------------------------------------
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //----------------------------------------------------------
    }
    //-------------------------------------------------------------

    //load thông tin sản phẩm vào form chi tiết sản phẩm
    private void loaddata(){
        Intent intent = getIntent();
        String url = intent.getStringExtra("anhsp");
        Picasso.with(this).load(url).into(loadhinh);
        loadtensp.setText(intent.getStringExtra("tensp"));
        loadgiasp.setText(String.valueOf(intent.getIntExtra("giasp",0))+" "+"Đồng");
        loadsoluongsp.setText(String.valueOf(intent.getIntExtra("soluongsp",0))+" "+"cái");
        loadmota.setText(intent.getStringExtra("mota"));
    }
}
