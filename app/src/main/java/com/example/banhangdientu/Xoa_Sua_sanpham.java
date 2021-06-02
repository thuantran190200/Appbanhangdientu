package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Xoa_Sua_sanpham extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    ImageButton back;
    Button btn_capnhatsp,btn_thoat, btnxoasp;
    ImageView themhinhsp;
    Spinner danhmuc, madein,thuonghieu;
    EditText tensp,gia,soluong,mota,diachi,sdt;
    //TextView masp;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference reference;
    String idmasp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_sua_sanpham);


        //----------------------------
        reference = FirebaseDatabase.getInstance().getReference().child("Sanpham");

        back = findViewById(R.id.btn_back_cnsp);
        danhmuc = findViewById(R.id.edit_cnloaisp);
        madein = findViewById(R.id.edit_cnmadein);
        thuonghieu = findViewById(R.id.edit_cnthuonghieu);
        tensp = findViewById(R.id.edit_cntensp);
        gia = findViewById(R.id.edit_cngiasp);
        soluong = findViewById(R.id.edit_cnsoluongsp);
        mota = findViewById(R.id.edit_cnmota);
        diachi = findViewById(R.id.edit_cndiachi);
        sdt = findViewById(R.id.edit_cnsdt);
        themhinhsp =findViewById(R.id.cnanhsanpham);
        btn_capnhatsp = findViewById(R.id.btn_capnhatsp1);
        btnxoasp = findViewById(R.id.btn_xoasp);
        //masp = findViewById(R.id.ma_sp);

        String dm[] = {"Tivi","Loa","Amly","Linh kiện","Dàn máy"};
        ArrayAdapter dm_adapter = new ArrayAdapter(Xoa_Sua_sanpham.this, android.R.layout.simple_spinner_item,dm);
        danhmuc.setAdapter(dm_adapter);
        String made[] = {"Việt nam", "Hàn quốc","Nhật bản","Mỹ", "Canada"};
        ArrayAdapter made_adapter = new ArrayAdapter(Xoa_Sua_sanpham.this, android.R.layout.simple_spinner_item,made);
        madein.setAdapter(made_adapter);
        String hang[] = {"Sony", "Samsung","LG","Toshiba","JBL",};
        ArrayAdapter hang_adapter = new ArrayAdapter(Xoa_Sua_sanpham.this, android.R.layout.simple_spinner_item,hang);
        thuonghieu.setAdapter(hang_adapter);

        StorageReference storageRef = storage.getReferenceFromUrl("gs://muahangdientu.appspot.com");


        loaddata();
        //----------------------------
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        themhinhsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , SELECT_PICTURE);
            }
        });



        btnxoasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoasp();
            }
        });

        btn_capnhatsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatSP();

            }
        });
    }
    private void loaddata(){
        Intent intent = getIntent();
        String url = intent.getStringExtra("anhsp");
        Picasso.with(this).load(url).into(themhinhsp);
        tensp.setText(intent.getStringExtra("tensp"));
        gia.setText(intent.getStringExtra("giasp"));
        soluong.setText(intent.getStringExtra("soluongsp"));
        //thuonghieu.setText(intent.getStringExtra("thuonghieu"));
        //danhmuc.setText(intent.getStringExtra("loaisp"));
        mota.setText(intent.getStringExtra("mota"));
        diachi.setText(intent.getStringExtra("diachi"));
        sdt.setText(intent.getStringExtra("sdt"));
        //masp.setText(intent.getStringExtra("id"));
        idmasp = intent.getStringExtra("id");
    }
    private void CapNhatSP(){
        reference = FirebaseDatabase.getInstance().getReference().child("Sanpham");
        Query query = reference.orderByChild("id").equalTo(idmasp);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    reference.child(idmasp).child("tensp").setValue(tensp.getText().toString().trim());
                    reference.child(idmasp).child("giasp").setValue(gia.getText().toString().trim());
                    reference.child(idmasp).child("mota").setValue(mota.getText().toString().trim());
                    reference.child(idmasp).child("sdt").setValue(sdt.getText().toString().trim());
                    reference.child(idmasp).child("diachi").setValue(diachi.getText().toString().trim());
                    reference.child(idmasp).child("soluongsp").setValue(soluong.getText().toString().trim());
                    reference.child(idmasp).child("thuonghieusp").setValue(thuonghieu.getSelectedItem().toString().trim());
                    reference.child(idmasp).child("loaisp").setValue(danhmuc.getSelectedItem().toString().trim());
                    reference.child(idmasp).child("madein").setValue(madein.getSelectedItem().toString().trim());
                    Toast.makeText(Xoa_Sua_sanpham.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private  void xoasp(){
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    reference = FirebaseDatabase.getInstance().getReference().child("Sanpham");
                    Query query = reference.orderByChild("id").equalTo(idmasp);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    ds.getRef().removeValue();
                                }
                                Toast.makeText(Xoa_Sua_sanpham.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(Xoa_Sua_sanpham.this);
        builder.setMessage("Bạn có chắc muốn xóa sản phẩm?").setPositiveButton("Yes", onClickListener)
                .setNegativeButton("No", onClickListener).show();
    }
}