package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

public class Thongtinhoadon_Giaohang_Khachhang extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    TextView hoten, sdt, diachi;
    Button btn_lienhe, btn_huydh;
    DatabaseReference referenceCTDH, referenceSP, referenceHD;
    ArrayList<Sanpham> listsanpham;
    ArrayList<Chitiethoadon> listCTHD;
    String idHD;
    int tongtien;
    String trangthai;
    Chitietdonhang_vanchuyen adapter;
    final Calendar myCalendar= Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinhoadon_giaohang_khachhang);

        back = findViewById(R.id.back_thoat11);
        recyclerView = findViewById(R.id.recyclerview_tthd_kh);
        hoten = findViewById(R.id.hoten_nn001);
        sdt = findViewById(R.id.sdtgh01);
        diachi = findViewById(R.id.diachi110);
        btn_lienhe = findViewById(R.id.btn_dagiaohang1);
        btn_huydh = findViewById(R.id.btn_huydonhang1);

        btn_lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thongtinhoadon_Giaohang_Khachhang.this, Lienhecuahang.class);
                startActivity(intent);
            }
        });

        btn_huydh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trangthai = "Đã Hủy";
                referenceHD = FirebaseDatabase.getInstance().getReference().child("Chitietdonhang");
                Query query = referenceHD.orderByChild("id").equalTo(idHD);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            referenceHD.child(idHD).child("trangthaiGH").setValue(trangthai.trim());
                            referenceHD.child(idHD).child("ngaytaoHD").setValue("".trim());
                            Toast.makeText(Thongtinhoadon_Giaohang_Khachhang.this,"Đơn hàng đã được hủy!!!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        idHD = intent.getStringExtra("idHD");
        tongtien = intent.getIntExtra("tongtien",0);
        hoten.setText(intent.getStringExtra("hoten"));
        sdt.setText(intent.getStringExtra("sodienthoai"));
        diachi.setText(intent.getStringExtra("diachi"));
        trangthai = intent.getStringExtra("trangthai");

        if(trangthai.equals("Đã Giao".trim()) || trangthai.equals("Đã Hủy")){
            btn_huydh.setVisibility(View.GONE);
        }

        referenceCTDH = FirebaseDatabase.getInstance().getReference().child("Hoadon");
        referenceSP = FirebaseDatabase.getInstance().getReference().child("Sanpham");

        DataFromFirebaseListener();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Thongtinhoadon_Giaohang_Khachhang.this);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Chitietdonhang_vanchuyen(Thongtinhoadon_Giaohang_Khachhang.this , listsanpham,listCTHD);
        recyclerView.setAdapter(adapter);
    }
    private void DataFromFirebaseListener() {
        listsanpham = new ArrayList<>();
        listCTHD = new ArrayList<>();
        referenceCTDH.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String keyCT = ds.getKey();
                    String idSP = ds.child("idsp").getValue(String.class);
                    String idHDFromDatabase = ds.child("idhd").getValue(String.class);
                    int soluong = ds.child("soluong").getValue(Integer.class);

                    AtomicBoolean isHoaDon = new AtomicBoolean();
                    if(idHD.equals(idHDFromDatabase)){
                        isHoaDon.set(true);
                    }
                    referenceSP.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()) {
                                String key = ds.getKey();
                                String hinhsp = ds.child("anhsp").getValue(String.class);
                                String tensp = ds.child("tensp").getValue(String.class);
                                int giasp = ds.child("giasp").getValue(Integer.class);
                                String thuonghieu = ds.child("thuonghieusp").getValue(String.class);
                                String loaisp = ds.child("loaisp").getValue(String.class);
                                String madein = ds.child("madein").getValue(String.class);
                                String motaSP = ds.child("mota").getValue(String.class);
                                String diachi1 = ds.child("diachi").getValue(String.class);
                                String sdt1 = ds.child("sdt").getValue(String.class);
                                int soluongKho = ds.child("soluongsp").getValue(Integer.class);
                                AtomicBoolean isSanPham = new AtomicBoolean();
                                assert key != null;
                                if(key.equals(idSP)){
                                    isSanPham.set(true);
                                }
                                if(isHoaDon.get() && isSanPham.get()){
                                    Chitiethoadon chiTietHoaDon = new Chitiethoadon(keyCT,key,idHD,soluong);
                                    listCTHD.add(chiTietHoaDon);
                                    Sanpham sanpham = new Sanpham(key,hinhsp,tensp,soluong*giasp,soluongKho,thuonghieu,loaisp,motaSP,madein,diachi1,sdt1);
                                    listsanpham.add(sanpham);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}