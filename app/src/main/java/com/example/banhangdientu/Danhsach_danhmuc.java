 package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

 public class Danhsach_danhmuc extends AppCompatActivity {

     DatabaseReference reference;
     ArrayList<Sanpham> listsanpham = new ArrayList<>();
     SanphamAdapter sanphamAdapter;
     GridView gridView;
     String tendm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_danhmuc);

        DatafromFirebase();
        loadata();
        gridView = findViewById(R.id.grib_view_dsdm);
        sanphamAdapter=  new SanphamAdapter(this, listsanpham);
        gridView.setAdapter(sanphamAdapter);
        sanphamAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Danhsach_danhmuc.this, ChitietSanpham.class);
                intent.putExtra("anhsp",listsanpham.get(position).getAnhsp());
                intent.putExtra("tensp",listsanpham.get(position).getTensp());
                intent.putExtra("giasp",listsanpham.get(position).getGiasp());
                intent.putExtra("soluongsp",listsanpham.get(position).getSoluongsp());
                intent.putExtra("mota",listsanpham.get(position).getMota());
                startActivity(intent);
            }
        });
    }
     private void DatafromFirebase(){
         listsanpham = new ArrayList<>();
         reference= FirebaseDatabase.getInstance().getReference().child("Sanpham");
         reference.addValueEventListener(new ValueEventListener() {
             @RequiresApi(api = Build.VERSION_CODES.N)
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot ds : snapshot.getChildren()){

                     String key = ds.getKey();
                     String anhsp = ds.child("anhsp").getValue(String.class);
                     String tensp = ds.child("tensp").getValue(String.class);
                     int giasp = ds.child("giasp").getValue(int.class);
                     String diachi = ds.child("diachi").getValue(String.class);
                     String mota = ds.child("mota").getValue(String.class);
                     String loaisp=ds.child("loaisp").getValue(String.class);
                     String thuonghieu= ds.child("thuonghieusp").getValue(String.class);
                     String sdt1 = ds.child("sdt").getValue(String.class);
                     int soluong= ds.child("soluongsp").getValue(int.class);
                     String madein = ds.child("madein").getValue(String.class);

                     if (loaisp.equals(tendm)){
                         Sanpham sanpham = new Sanpham(key,anhsp, tensp, giasp,soluong,thuonghieu, loaisp, mota, madein, diachi,sdt1);
                         listsanpham.add(sanpham);
                         Collections.sort(listsanpham, new Comparator<Sanpham>() {
                             @Override
                             public int compare(Sanpham o1, Sanpham o2) {
                                 if (o1.getGiasp()>o2.getGiasp()){
                                     return 1;
                                 }else {
                                     if (o1.getGiasp()==o2.getGiasp()){
                                         return 0;
                                     }else {
                                         return -1;
                                     }
                                 }
                             }
                         });
                     }


                 }
                 sanphamAdapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }
     private void loadata(){
        Intent intent = getIntent();
        tendm = intent.getStringExtra("loaisp");
     }
}