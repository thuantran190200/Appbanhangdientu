package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Dangky extends AppCompatActivity {

    ImageButton back;
    Button dangky, btn_huy;
    EditText tendn, hoten, matkhau, sdt, diachi;
    Spinner gioitinh;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        //-------------------------------------
        gioitinh = findViewById(R.id.dk_gioitinh);
        dangky = findViewById(R.id.btn_dangky);
        tendn = findViewById(R.id.dk_tendn);
        hoten = findViewById(R.id.dk_hoten);
        matkhau = findViewById(R.id.dk_matkhau);
        sdt = findViewById(R.id.dk_sdt);
        diachi = findViewById(R.id.dk_diachi);
        btn_huy = findViewById(R.id.thoat);
        back = findViewById(R.id.btn_thoat);
        //----------------------------------------

        String gt[] = {"Nam", "Nữ"};
        ArrayAdapter gt_adapter = new ArrayAdapter(Dangky.this, android.R.layout.simple_spinner_item, gt);
        gioitinh.setAdapter(gt_adapter);

        //------------------------------
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("User");
                String ten_dn = tendn.getText().toString().trim();
                String ho_ten = hoten.getText().toString();
                String mat_khau = matkhau.getText().toString();
                String gioi_tinh = gioitinh.getSelectedItem().toString();
                String sdt1 = sdt.getText().toString();
                String dia_chi = diachi.getText().toString();
                String loaitk = "khachhang";


                //Query kttendn = FirebaseDatabase.getInstance().getReference("User").orderByChild("sdt").equalTo(sdt1);
                Query ktsdt = FirebaseDatabase.getInstance().getReference("User").orderByChild("sdt").equalTo(sdt1);
                ktsdt.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        //String tendn =snapshot.child("tendn").getValue(String.class);
                        if (snapshot.exists())//||
                        {
                            Toast.makeText(Dangky.this, "Số điện thoại đã được đăng ký", Toast.LENGTH_SHORT).show();
                        }
                        /*if (ten_dn.equals(tendn)){

                            Toast.makeText(Dangky.this,"Tài khoản tồn tại. Vui lòng nhập tài khoản mới", Toast.LENGTH_SHORT).show();

                        }*/else {

                            if (TextUtils.isEmpty(ten_dn) || TextUtils.isEmpty(ho_ten) || TextUtils.isEmpty(mat_khau) || TextUtils.isEmpty(sdt1) || TextUtils.isEmpty(dia_chi)) {
                                Toast.makeText(Dangky.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                Userdata userdata = new Userdata(ten_dn, ho_ten, mat_khau, gioi_tinh, sdt1, dia_chi, loaitk);
                                reference.child(ten_dn).setValue(userdata);
                                Toast.makeText(Dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
                //viết thêm dòng code kiểm tra tên đăng nhập và sđt trên firebase nếu có thì báo tài khoản tồn tại
                //String kt_tendn = reference.

            }
        });
    }
}