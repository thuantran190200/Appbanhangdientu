package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Xoa_Sua_sanpham extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    ImageButton back;
    Button btn_capnhatsp,btn_thoat;
    ImageView themhinhsp;
    Spinner danhmuc, madein,thuonghieu;
    EditText tensp,gia,soluong,mota,diachi,sdt;
    TextView masp;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_sua_sanpham);


        //----------------------------
        reference = FirebaseDatabase.getInstance().getReference().child("Sanpham");

        back = findViewById(R.id.btn_back_cnsp);
        btn_thoat = findViewById(R.id.btn_xoasp);
        danhmuc = findViewById(R.id.edit_cnloaisp);
        madein = findViewById(R.id.edit_cnmadein);
        thuonghieu = findViewById(R.id.edit_cnthuonghieu);
        tensp = findViewById(R.id.edit_cntensp);
        gia = findViewById(R.id.edit_cngiasp);
        soluong = findViewById(R.id.edit_cnsoluongsp);
        mota = findViewById(R.id.edit_mota);
        diachi = findViewById(R.id.edit_cndiachi);
        sdt = findViewById(R.id.edit_cnsdt);
        themhinhsp =findViewById(R.id.cnanhsanpham);
        btn_capnhatsp = findViewById(R.id.btn_capnhatsp1);
        masp = findViewById(R.id.ma_sp);

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
            /*    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, SELECT_PICTURE);*/
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , SELECT_PICTURE);
            }
        });

        btn_capnhatsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //code thêm ràng buộc không để nul
                if (tensp.getText().toString().length()!=0 && gia.getText().toString().length()!=0 && soluong.getText().toString().length()!=0 ) {
                    Calendar calendar = Calendar.getInstance();
                    StorageReference mountainsRef = storageRef.child("imgae" + calendar.getTimeInMillis() + ".png");

                    //code firebase có sẵn lấy về
                    themhinhsp.setDrawingCacheEnabled(true);
                    themhinhsp.buildDrawingCache();
                    Bitmap bitmap = ((BitmapDrawable) themhinhsp.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] data = baos.toByteArray();
                    //***********************************
                    UploadTask uploadTask = mountainsRef.putBytes(data);
                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            // Continue with the task to get the download URL
                            return mountainsRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                Toast.makeText(Xoa_Sua_sanpham.this, "Cập nhật hình thành Công", Toast.LENGTH_SHORT).show();

                                String key = reference.push().getKey();
                                Sanpham sanpham = new Sanpham(key, String.valueOf(downloadUri),tensp.getText().toString(),gia.getText().toString(),soluong.getText().toString()
                                        ,thuonghieu.getSelectedItem().toString(),danhmuc.getSelectedItem().toString(),mota.getText().toString(),madein.getSelectedItem().toString()
                                        ,diachi.getText().toString(),sdt.getText().toString());



                                reference.child(key).setValue(sanpham, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error != null) {
                                            Toast.makeText(Xoa_Sua_sanpham.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Xoa_Sua_sanpham.this, "Cập nhật thành Công", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }

                                });


                            } else {
                                // Handle failures
                                // ...
                            }

                        }
                    });
                }else {
                    Toast.makeText(Xoa_Sua_sanpham.this, "Vui lòng điền đầy đủ!!!", Toast.LENGTH_SHORT).show();
                }
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
        masp.setText(intent.getStringExtra("id"));
    }
}