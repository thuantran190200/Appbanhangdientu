package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Thongtincanhan extends AppCompatActivity {

    EditText hoten1, sdt1, diachi1;
    Button btn_luulai;
    Spinner gioitinh;
    ImageButton btn_thoat;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);

        //-------------------
        hoten1 = findViewById(R.id.txt_hoten);
        sdt1 = findViewById(R.id.txt_sdt);
        diachi1 = findViewById(R.id.txt_diachi);
        btn_luulai = findViewById(R.id.btn_luu);
        btn_thoat = findViewById(R.id.thoat_ttcn);
        gioitinh = findViewById(R.id.txt_gioitinh);

        String gt[] = {"Nam", "Nữ"};
        ArrayAdapter gt_adapter = new ArrayAdapter(Thongtincanhan.this, android.R.layout.simple_spinner_item, gt);
        gioitinh.setAdapter(gt_adapter);

        //------------------------------
        uploaddata();
        //---------------------------------------------

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_luulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetoFirebase();
                finish();
                Toast.makeText(Thongtincanhan.this,"Lưu thông tin thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updatetoFirebase(){
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = reference.orderByChild("tendn").equalTo(MainActivity.tendn);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    MainActivity.hoten = hoten1.getText().toString();
                    MainActivity.sdt = sdt1.getText().toString();
                    MainActivity.diachi = diachi1.getText().toString();
                    MainActivity.gioitinh = gioitinh.getSelectedItem().toString();

                    reference.child(MainActivity.tendn).child("hoten").setValue(MainActivity.hoten);
                    reference.child(MainActivity.tendn).child("sdt").setValue(MainActivity.sdt);
                    reference.child(MainActivity.tendn).child("diachi").setValue(MainActivity.diachi);
                    reference.child(MainActivity.tendn).child("gioitinh").setValue(MainActivity.gioitinh);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
    private void uploaddata(){
        hoten1.setText(MainActivity.hoten);
        sdt1.setText(MainActivity.sdt);
        diachi1.setText(MainActivity.diachi);
        //ArrayAdapter gt_adapter = new ArrayAdapter(Thongtincanhan.this, android.R.layout.simple_spinner_item, gt);
        //
        // gioitinh.setAdapter(getText[MainActivity.gioitinh]);
    }
}