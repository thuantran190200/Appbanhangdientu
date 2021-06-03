package com.example.banhangdientu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;


public class Fragment_trangchu extends Fragment {


    DatabaseReference reference;
    ArrayList<Sanpham> listsanpham = new ArrayList<>();
    SanphamAdapter sanphamAdapter;
    GridView gridView;
    Button timkiem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        timkiem = (Button) view.findViewById(R.id.timkiem);
        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Timkiem_sp.class);
                startActivity(intent);
            }
        });

        DatafromFirebase();
        gridView = (GridView) view.findViewById(R.id.grib_view);
        sanphamAdapter=  new SanphamAdapter(getContext(), listsanpham);
        gridView.setAdapter(sanphamAdapter);
        sanphamAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChitietSanpham.class);
                intent.putExtra("anhsp",listsanpham.get(position).getAnhsp());
                intent.putExtra("tensp",listsanpham.get(position).getTensp());
                intent.putExtra("giasp",listsanpham.get(position).getGiasp());
                intent.putExtra("soluongsp",listsanpham.get(position).getSoluongsp());
                intent.putExtra("mota",listsanpham.get(position).getMota());
                startActivity(intent);
            }
        });




        return view;
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
                    int giasp = ds.child("giasp").getValue(Integer.class);
                    String diachi = ds.child("diachi").getValue(String.class);
                    String mota = ds.child("mota").getValue(String.class);
                    String loaisp=ds.child("loaisp").getValue(String.class);
                    String thuonghieu= ds.child("thuonghieusp").getValue(String.class);
                    String sdt1 = ds.child("sdt").getValue(String.class);
                    int soluong= ds.child("soluongsp").getValue(Integer.class);
                    String madein = ds.child("madein").getValue(String.class);
                    AtomicBoolean isSP = new AtomicBoolean();
                    listsanpham.forEach(sanpham -> {
                        if (sanpham.getId() == key) {
                            isSP.set(true);
                        }
                    });


                    Sanpham sanpham = new Sanpham(key,anhsp, tensp, giasp, soluong,thuonghieu, loaisp, mota, madein, diachi,sdt1);
                    listsanpham.add(sanpham);
                    //code sắp xếp theo thứ tự
                    Collections.sort(listsanpham, new Comparator<Sanpham>() {
                        @Override
                        public int compare(Sanpham o1, Sanpham o2) {
                            return o1.getTensp().compareTo(o2.getTensp());
                        }
                    });
                }
                sanphamAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}