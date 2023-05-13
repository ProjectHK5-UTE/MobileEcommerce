package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.AdminRecycleAdapterBrandsList;
import com.example.mobileecommerce.api.BrandAPI;
import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.service.RealPathUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBrandDialogActivity extends AppCompatActivity {
    private Button btnSelectImage, btnAddBrand, btnCancel;
    private ImageView imgView;
    private EditText edtBrandName;
    private Uri imgUrl;
    private Uri mUri;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 9;
    private static final int PICK_IMAGE_REQUEST = 10;
    private BrandAPI brandAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestReadExternalStoragePermission();
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_brand);

        brandAPI = RetrofitClient.getRetrofit().create(BrandAPI.class);

        imgView = findViewById(R.id.selected_image);

        btnSelectImage = findViewById(R.id.btnSelect_);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        edtBrandName = findViewById(R.id.edtName_);

        btnCancel = findViewById(R.id.btnCancel_);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAddBrand = findViewById(R.id.btnSave_);
        btnAddBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBrand();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUrl = data.getData();
            mUri = imgUrl;
            String realPath = RealPathUtil.getRealPath(this, mUri);
            if (realPath != null) {
                Picasso.get().load(imgUrl).into(imgView);
            } else {
                Toast.makeText(this, "Cannot get real path of image", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void addBrand() {
        if (edtBrandName.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddBrandDialogActivity.this, "Please enter brand name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imgUrl == null) {
            Toast.makeText(AddBrandDialogActivity.this, "Please select brand image", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody brandName = RequestBody.create(MediaType.parse("multipart/form-data"), edtBrandName.getText().toString().trim());
        File file = new File(RealPathUtil.getRealPath(this, imgUrl));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("images", file.getName(), requestFile);

        Call<ResponseObject> call = brandAPI.insert(brandName, image);
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    ResponseObject result = response.body();
                    if (result != null && result.getStatus().equals("Success")) {
                        Toast.makeText(AddBrandDialogActivity.this, "Brand added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddBrandDialogActivity.this, "Failed to add brand: " + result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddBrandDialogActivity.this, "Failed to add brand", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(AddBrandDialogActivity.this, AdminPanelBrandActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(AddBrandDialogActivity.this, "Failed to call API"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddBrandDialogActivity.this, AdminPanelBrandActivity.class));
                finish();
            }
        });
    }
    private void requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        } else {
            // Quyền đã được cấp, thực hiện các hành động tương ứng
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Quyền được cấp, thực hiện các hành động tương ứng
                } else {
                    // Quyền không được cấp, thông báo cho người dùng biết
                    Toast.makeText(this, "Read external storage permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}