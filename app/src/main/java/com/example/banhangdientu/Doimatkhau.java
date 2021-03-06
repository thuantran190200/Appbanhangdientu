package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Doimatkhau extends AppCompatActivity {

    ImageButton btn_thoat;
    TextView tendn;
    EditText edt_mk_cu, edt_mk_moi, edt_mk_moi1;
    Button btn_luu;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);

        btn_thoat = findViewById(R.id.back_thoat);
        tendn = findViewById(R.id.show_tendn);
        tendn.setText(MainActivity.tendn);
        edt_mk_cu = findViewById(R.id.mk_cu);
        edt_mk_moi = findViewById(R.id.mk_moi);
        edt_mk_moi1 = findViewById(R.id.mk_moi1);
        btn_luu = findViewById(R.id.btn_doimktk);

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatapassword();
            }
        });
    }
    private void updatapassword(){
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = reference.orderByChild("tendn").equalTo(MainActivity.tendn);
        String mk = edt_mk_cu.getText().toString().trim();
        String mkmoi = edt_mk_moi.getText().toString().trim();
        String mkmoi1 = edt_mk_moi1.getText().toString().trim();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String checkpassword = snapshot.child(MainActivity.tendn).child("matkhau").getValue(String.class);
                    if (checkpassword.equals(mk)) {
                        if(mkmoi.equals(mkmoi1)) {

                            String luumk = edt_mk_moi1.getText().toString().trim();
                            reference.child(MainActivity.tendn).child("matkhau").setValue(luumk);
                            Toast.makeText(Doimatkhau.this, "?????i m???t kh???u th??nh c??ng!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Doimatkhau.this, "Nh???p sai m???t kh???u m???i. Vui l??ng ki???m tra l???i!!!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Doimatkhau.this, "Sai m???t kh???u c?? vui l??ng nh???p l???i.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}