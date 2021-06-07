package com.example.banhangdientu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_Thongtingiaohang extends Fragment {

    EditText hoten, sdt, diachi;
    Button btn_xacnhan;
    Chitiethoadon chitiethoadon;
    final Calendar myCalendar= Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static final  String SHARED_PREFS = "sharedPrefs";
    DatabaseReference referenceHD;
    DatabaseReference referenceCTHD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__thongtingiaohang, container, false);

        //----------------------------------------
        hoten = (EditText) view.findViewById(R.id.ten_nguoinhan);
        sdt = (EditText) view.findViewById(R.id.sdt_nn);
        diachi = (EditText) view.findViewById(R.id.diachi_gh);
        btn_xacnhan = (Button) view.findViewById(R.id.btn_xacnhandh);
        referenceHD = FirebaseDatabase.getInstance().getReference().child("Chitietdonhang");
        referenceCTHD = FirebaseDatabase.getInstance().getReference().child("Hoadon");
        hoten.setText(MainActivity.hoten);
        sdt.setText(MainActivity.sdt);
        diachi.setText(MainActivity.diachi);

        String hoten1 = hoten.getText().toString();
        String sdt1 = sdt.getText().toString().trim();
        String diachi1 = diachi.getText().toString();
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!kthoten() | !ktsodienthoai() | !ktdiachi()) {
                    return;
                }else {
                    String keyHD = referenceHD.push().getKey();
                    myCalendar.add(Calendar.DATE,2);
                    String ngaytaodon = sdf.format(myCalendar.getTime());
                    String ngayhoanthanh = "";
                    String trangthai = "Chờ giao hàng";
                    Hoadon hoaDon = new Hoadon(keyHD,ngaytaodon,ngayhoanthanh,hoten1,sdt1,diachi1,trangthai,Giaodienchinh.id,Fragment_giohang_btn_tinhtien.tt);
                    Giaodienchinh.listhoadon.add(hoaDon);
                    referenceHD.child(keyHD).setValue(hoaDon);
                    Toast.makeText(getContext(), "Tạo đơn hàng thành công", Toast.LENGTH_LONG).show();
                    Giaodienchinh.listspGiohang.forEach(sanPham -> {
                        String keyCTHD = referenceCTHD.push().getKey();
                        String idSP = sanPham.getId();
                        int soluong = sanPham.getSoluong();
                        chitiethoadon = new Chitiethoadon(keyCTHD,idSP,keyHD,soluong);
                        referenceCTHD.child(keyCTHD).setValue(chitiethoadon);
                    });
                    Giaodienchinh.listspGiohang.clear();
                    Savedata();
                    Intent intent = new Intent(getActivity(),Fragment_Giaohang_Khachhang.class);
                    intent.putExtra("idHD",keyHD);
                    startActivity(intent);
                }
            }
        });


        return view;
    }
    private Boolean ktdiachi (){
        String val = diachi.getText().toString();
        if(val.isEmpty()){
            diachi.setError("Vui lòng nhập địa chỉ");
            return false;
        }else {
            diachi.setError(null);
            return true;
        }
    }
    private Boolean ktsodienthoai (){
        String val = sdt.getText().toString();

        if(val.isEmpty()){
            sdt.setError("Vui lòng nhập số điện thoại");
            return false;
        }else {
            sdt.setError(null);
            return true;
        }
    }
    private Boolean kthoten () {
        String val = hoten.getText().toString();

        if (val.isEmpty()) {
            hoten.setError("Vui lòng nhập họ và tên");
            return false;
        } else {
            hoten.setError(null);
            return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private void Savedata(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
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