package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dangnhap extends AppCompatActivity {

    TextInputLayout username, password;
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

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });
        //-------------------------------------------
    }
    private Boolean xacnhantaikhoan(){
        username = findViewById(R.id.username);
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            username.setError("Tài khoản trống vui lòng đăng nhập");
            return false;
        }else if(val.length()>= 15){
            username.setError("Tên đăng nhập quá dài");
            return false;
        }else if(!val.matches(noWhiteSpace)){
            username.setError("Bạn không được viết ký tự đặc biệt");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean xacnhanmatkhau(){
        password = findViewById(R.id.password);
        String val = password.getEditText().getText().toString();
        if(val.isEmpty()){
            password.setError("Không để mật khẩu trống");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginuser(){
        if( ! xacnhantaikhoan() | ! xacnhanmatkhau()){
            return;
        }else {
            isUser();
        }
    }
    private void isUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");

        Query checkuser = reference.orderByChild("tendn").equalTo(userEnteredUsername);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (snapshot.exists()) {

                        username.setError(null);
                        username.setErrorEnabled(false);
                        String key = ds.getKey();
                        String passwordFromDB = snapshot.child(key).child("matkhau").getValue(String.class);

                        if (userEnteredPassword.equals(passwordFromDB)) {

                            username.setError(null);
                            username.setErrorEnabled(false);

                            //Intent intent = new Intent(Dangnhap.this, Giaodien_trangchu.class);
                            //startActivity(intent);


                            String tendnFromDB = snapshot.child(key).child("tendn").getValue(String.class);
                            String hotenFromDB = snapshot.child(key).child("hoten").getValue(String.class);
                            String gioitinhFromDB = snapshot.child(key).child("gioitinh").getValue(String.class);
                            String sdtFromDB = snapshot.child(key).child("sdt").getValue(String.class);
                            String diachiFromDB = snapshot.child(key).child("diachi").getValue(String.class);
                            String loaitkFromDB = snapshot.child(key).child("loaitk").getValue(String.class);

                            MainActivity.tendn = tendnFromDB;
                            MainActivity.sdt = sdtFromDB;
                            MainActivity.hoten = hotenFromDB;
                            MainActivity.diachi = diachiFromDB;
                            MainActivity.gioitinh = gioitinhFromDB;
                            MainActivity.loaitk = loaitkFromDB;
                            MainActivity.ktdangnhap = true;

                            /*//Create Database Store
                            Sessionmanager sessionmanager = new Sessionmanager(getApplicationContext(), Sessionmanager.SESSION_USER);
                            sessionmanager.createLoginSession(hotenFromDB, taikhoanFromDB, emailFromDB, sdtFromDB);

                            startActivity(new Intent(getApplicationContext(), Giaodien_trangchu.class));*/
                            Toast.makeText(Dangnhap.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            finish();



                            Intent intent1 = new Intent(getApplicationContext(), Thongtincanhan.class);
                            intent1.putExtra("hoten", hotenFromDB);
                            intent1.putExtra("gioitinh", gioitinhFromDB);
                            intent1.putExtra("sdt", sdtFromDB);
                            intent1.putExtra("diachi", diachiFromDB);
                            intent1.putExtra("loaitk", loaitkFromDB);

                        } else {
                            password.setError("Sai mật khẩu");
                            password.requestFocus();
                        }
                    } else {
                        username.setError("Tài khoảng không tồn tại");
                        username.requestFocus();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}