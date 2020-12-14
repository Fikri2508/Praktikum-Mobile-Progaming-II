package com.example.listlearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;

import static com.example.listlearning.MyApp.db;

public class AddRoomDataActivity extends AppCompatActivity implements DialogImageOptionsListerner{

    public final static String TAG_DATA_INTENT = "data mahasiswa";
    public final static int REQUEST_CAMERA = 101;
    public final static int REQUEST_GALLERY = 202;
    public final static int PICK_CAMERA = 1001;
    public final static int PICK_GALLERY = 2002;
    private MahasiswaDao dao;
    private ImageView imageView;
    private ImageView addImage;
    private RequestOptions requestOptions;
    private File fileImage;
    private Button btnTambah;



    EditText etNama;
    EditText etNim;
    EditText etKejuruan;
    EditText etAlamat;
    Mahasiswa mahasiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room_data);
        Button btnTambah = findViewById(R.id.btInsert);
        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        etKejuruan = findViewById(R.id.etKejuruan);
        etAlamat = findViewById(R.id.etAlamat);
        addImage = findViewById(R.id.add_image);
        imageView = findViewById(R.id.image);
        dao = MyApp.getInstance().getDataBase().userDao();


        if (getIntent() != null) {
            String id = getIntent().getStringExtra(TAG_DATA_INTENT);
            mahasiswa = dao.findByName(id);
        }
        if (mahasiswa == null) {
            mahasiswa = new Mahasiswa();
        }

        requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_account)
                .error(R.drawable.ic_account);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomDialogImageOptions(AddRoomDataActivity.this,
                        AddRoomDataActivity.this)
                        .show();
            }
        });




        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahData();
            }
        });
    }


    private void tambahData(){
        if (!etAlamat.getText().toString().isEmpty()&&!etKejuruan.getText().toString().isEmpty()&&
                !etNama.getText().toString().isEmpty()&&!etNim.getText().toString().isEmpty()) {

            mahasiswa.setAlamat(etAlamat.getText().toString());
            mahasiswa.setKejuruan(etKejuruan.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());
            mahasiswa.setNim(etNim.getText().toString());
            db.userDao().insertAll(mahasiswa);
            startActivity(new Intent(AddRoomDataActivity.this, RoomDataActivity.class));
            }else {
            Toast.makeText(this,"Mohon masukan dengan benar", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onCameraClick() {
        if (ContextCompat.checkSelfPermission(this ,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ){
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public void onGalerryClick() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ){
            openGalery();
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            openCamera();
        } else if (requestCode == REQUEST_CAMERA) {
            openGalery();

        }
    }


    @SuppressLint("SetText18n")
    @Override
    protected void onResume() {
        super.onResume();
        if (mahasiswa.getId() > 0 ) {
            etNama.setText(mahasiswa.getNama());
            etNim.setText(mahasiswa.getNim());
            btnTambah.setText("Ubah Data");
            loadImage(new File(mahasiswa.getGambar()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String path;
            if (requestCode == PICK_GALLERY) {
                path = getRealPathFromUri(data.getData());
            } else {
                path = fileImage.getAbsolutePath();
            }

            mahasiswa.setGambar(path);
            loadImage(new File(path));
        }
    }

    private void openCamera() {
        try{
            fileImage = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            Uri imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileImage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, PICK_CAMERA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("IntentReset")
    private void openGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_GALLERY);
    }

    private void loadImage(File image) {
        if (image == null) return;

        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(image)
                .into(imageView);
    }

    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+ 1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null
                , MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

}

