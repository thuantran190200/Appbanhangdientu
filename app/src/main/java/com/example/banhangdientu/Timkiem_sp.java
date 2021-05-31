package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Timkiem_sp extends AppCompatActivity {


    RecyclerView recyclerView;
    EditText editText;
    DatabaseReference reference;
    ArrayList<Sanpham> listsanpham;
    RecyclerView_Search recyclerView_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem_sp);

        DatafromFirebase();
        editText = findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                recyclerView_search.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                    //  Log.d("abc", "onDataChange: vao day");
                    String key = ds.getKey();
                    String anhsp = ds.child("anhsp").getValue(String.class);
                    String tensp = ds.child("tensp").getValue(String.class);
                    String giasp = ds.child("giasp").getValue(String.class);
                    String diachi = ds.child("diachi").getValue(String.class);
                    String mota = ds.child("mota").getValue(String.class);
                    String loaisp=ds.child("loaisp").getValue(String.class);
                    String thuonghieu= ds.child("thuonghieusp").getValue(String.class);
                    String sdt1 = ds.child("sdt").getValue(String.class);
                    String soluong= ds.child("spluongsp").getValue(String.class);
                    String madein = ds.child("madein").getValue(String.class);




                    Sanpham sanpham = new Sanpham(key,anhsp, tensp, giasp,soluong,thuonghieu, loaisp, mota, madein, diachi,sdt1);
                    listsanpham.add(sanpham);
                }
                recyclerView = findViewById(R.id.show_sp_timkiem);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Timkiem_sp.this);
                recyclerView_search = new RecyclerView_Search(Timkiem_sp.this, listsanpham);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerView_search);
                recyclerView_search.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}