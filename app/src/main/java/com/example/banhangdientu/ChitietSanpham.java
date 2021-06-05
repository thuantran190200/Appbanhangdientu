package com.example.banhangdientu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ChitietSanpham extends AppCompatActivity {

    ImageView loadhinh, btn_back;
    TextView loadtensp, loadgiasp, loadsoluongsp, loadmota;
    Button btn_add_giohang, btn_muangay;
    Sanpham sanpham1;
    public static final  String SHARED_PREFS = "sharedPrefs";
    @RequiresApi(api = Build.VERSION_CODES.N)
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
        Giaodienchinh.listspGiohang.forEach(Sanpham -> {
            if(Sanpham.getId().equals(sanpham1.getId()) && Sanpham.getSoluong() >= sanpham1.getSoluongsp()){
                btn_add_giohang.setEnabled(false);
                btn_add_giohang.setBackgroundColor(getResources().getColor(R.color.silver));
                Toast.makeText(ChitietSanpham.this, "Sản phẩm hiện đã hết hàng.", Toast.LENGTH_SHORT).show();
            }
        });
       /* if(sanpham1.getSoluong() == 0){
            btn_add_giohang.setEnabled(false);
            btn_add_giohang.setBackgroundColor(getResources().getColor(R.color.silver));
        }*/

        //----------------------------------------------------------
        btn_add_giohang.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)//????
            @Override
            public void onClick(View v) {
                Giaodienchinh.listspGiohang.forEach(SanphamGH -> {
                    if (SanphamGH.getId().equals(sanpham1.getId())) {
                        sanpham1 = SanphamGH;
                    }
                });
                if( sanpham1.getSoluong() == 0) {
                    sanpham1.setSoluong(sanpham1.getSoluong() + 1);
                    Giaodienchinh.listspGiohang.add(sanpham1);
                    Toast.makeText(ChitietSanpham.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    sanpham1.setSoluong(sanpham1.getSoluong() + 1);
                    Toast.makeText(ChitietSanpham.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                if(sanpham1.getSoluong() >= sanpham1.getSoluongsp()){
                    btn_add_giohang.setEnabled(false);
                    btn_add_giohang.setBackgroundColor(getResources().getColor(R.color.silver));
                    Toast.makeText(ChitietSanpham.this, "Sản phẩm hiện đã hết hàng.", Toast.LENGTH_SHORT).show();
                }
                saveData();
            }
        });

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
        //show sản phẩm chọn vào form chi tiết sản phẩm
        Intent intent = getIntent();
        String url = intent.getStringExtra("anhsp");
        Picasso.with(this).load(url).into(loadhinh);
        loadtensp.setText(intent.getStringExtra("tensp"));
        loadgiasp.setText(String.valueOf(intent.getIntExtra("giasp",0))+" "+"Đồng");
        loadsoluongsp.setText(String.valueOf(intent.getIntExtra("soluongsp",0))+" "+"cái");
        loadmota.setText(intent.getStringExtra("mota"));


        //load thong tin san pham lấy số lượng(?????)
        Intent intent1 = getIntent();
        String tensp = intent1.getStringExtra("tensp");
        int gia = intent1.getIntExtra("giasp",0);
        int soluongsp = intent1.getIntExtra("soluongsp",0);
        String thuonghieu = intent1.getStringExtra("thuonghieusp");
        String loaisp = intent1.getStringExtra("loaisp");
        String mota = intent1.getStringExtra("mota");
        String diachi1 = intent1.getStringExtra("diachi");
        String sdt = intent1.getStringExtra("sdt");
        String id1 = intent1.getStringExtra("id");
        String anhsp = intent1.getStringExtra("anhsp");
        String madein = intent1.getStringExtra("madein");

        sanpham1 = new Sanpham(id1,anhsp,tensp,gia,soluongsp,thuonghieu,loaisp,mota,madein,diachi1,sdt);
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // creating a new variable for gson.
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String json = gson.toJson(Giaodienchinh.listspGiohang);
        //below line is to save data in shared
        //prefs in the form of string.
        editor.putString("listGH", json);
        editor.apply();
    }
}
