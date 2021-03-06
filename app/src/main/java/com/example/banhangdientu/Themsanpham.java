package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class Themsanpham extends AppCompatActivity {


    private static final int SELECT_PICTURE = 1;
    ImageButton back;
    Button btn_dangsp,btn_thoat;
    ImageView themhinhsp;
    Spinner danhmuc, madein,thuonghieu;
    EditText tensp,gia,soluong,mota,diachi,sdt;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference Mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsanpham);

        Mdata = FirebaseDatabase.getInstance().getReference().child("Sanpham");

        back = findViewById(R.id.btn_back_addsp);
        btn_thoat = findViewById(R.id.btn_huys);
        danhmuc = findViewById(R.id.edit_loaisp);
        madein = findViewById(R.id.edit_madein);
        thuonghieu = findViewById(R.id.edit_thuonghieu);
        tensp = findViewById(R.id.edit_tensp);
        gia = findViewById(R.id.edit_giasp);
        soluong = findViewById(R.id.edit_soluongsp);
        mota = findViewById(R.id.edit_mota);
        diachi = findViewById(R.id.edit_diachi);
        sdt = findViewById(R.id.edit_sdt);
        themhinhsp =findViewById(R.id.anhsanpham);
        btn_dangsp = findViewById(R.id.dangsp);

        String dm[] = {"Tivi","Loa","Amly","Linh ki???n","D??n m??y"};
        ArrayAdapter dm_adapter = new ArrayAdapter(Themsanpham.this, android.R.layout.simple_spinner_item,dm);
        danhmuc.setAdapter(dm_adapter);
        String made[] = {"Vi???t nam", "H??n qu???c","Nh???t b???n","M???", "Canada"};
        ArrayAdapter made_adapter = new ArrayAdapter(Themsanpham.this, android.R.layout.simple_spinner_item,made);
        madein.setAdapter(made_adapter);
        String hang[] = {"Sony", "Samsung","LG","Toshiba","JBL",};
        ArrayAdapter hang_adapter = new ArrayAdapter(Themsanpham.this, android.R.layout.simple_spinner_item,hang);
        thuonghieu.setAdapter(hang_adapter);

        StorageReference storageRef = storage.getReferenceFromUrl("gs://muahangdientu.appspot.com");

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        btn_dangsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //code th??m r??ng bu???c kh??ng ????? nul
                if (tensp.getText().toString().length()!=0 && gia.getText().toString().length()!=0 && soluong.getText().toString().length()!=0 ) {
                    Calendar calendar = Calendar.getInstance();
                    StorageReference mountainsRef = storageRef.child("imgae" + calendar.getTimeInMillis() + ".png");

                    //code firebase c?? s???n l???y v???
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
                                Toast.makeText(Themsanpham.this, "Th??nh C??ng", Toast.LENGTH_SHORT).show();

                                String key = Mdata.push().getKey();
                                int giasp = Integer.parseInt(gia.getText().toString().trim());
                                int soluongkho = Integer.parseInt(soluong.getText().toString().trim());
                                Sanpham sanpham = new Sanpham(key, String.valueOf(downloadUri),tensp.getText().toString(),giasp,soluongkho
                                ,thuonghieu.getSelectedItem().toString(),danhmuc.getSelectedItem().toString(),mota.getText().toString(),madein.getSelectedItem().toString()
                                ,diachi.getText().toString(),sdt.getText().toString());



                                Mdata.child(key).setValue(sanpham, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error != null) {
                                            Toast.makeText(Themsanpham.this, "k Th??nh C??ng", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Themsanpham.this, "L??u Th??nh C??ng", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Themsanpham.this, "Vui l??ng ??i???n ?????y ?????!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream =getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                themhinhsp.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }
        }
    }
}