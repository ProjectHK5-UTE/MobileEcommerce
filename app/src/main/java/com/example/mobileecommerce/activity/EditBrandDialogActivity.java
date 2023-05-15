package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mobileecommerce.R;
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

public class EditBrandDialogActivity extends AppCompatActivity {
    private BrandsModel brand;
    private TextView title_;
    private Button btnSelectImage, btnSaveBrand, btnCancel;
    private ImageView imgView;
    private EditText edtBrandName;
    private TextView oldBrandName;
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
        setContentView(R.layout.dialog_update_brand);

        brandAPI = RetrofitClient.getRetrofit().create(BrandAPI.class);

        imgView = findViewById(R.id.selected_image);

        btnSelectImage = findViewById(R.id.btnSelect_);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        title_ = findViewById(R.id.dialog_title);
        oldBrandName = findViewById(R.id.oldName_);
        edtBrandName = findViewById(R.id.edtName_);
        btnSaveBrand = findViewById(R.id.btnSave_);
        btnCancel = findViewById(R.id.btnCancel_);
        title_.setText("Edit a Brand");

        // Lấy thông tin của Brand cần sửa từ Intent và hiển thị lên giao diện
        brand = (BrandsModel) getIntent().getSerializableExtra("brand");
        oldBrandName.setText(brand.getName());
        if (brand.getLogo() != null) {
            imgUrl = Uri.parse(brand.getLogo());
            Picasso.get().load(brand.getLogo()).into(imgView);
        }

        btnSaveBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBrand();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void updateBrand() {
        if (edtBrandName.getText().toString().trim().isEmpty()) {
            Toast.makeText(EditBrandDialogActivity.this, "Please enter brand name", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestBody oldName = RequestBody.create(MediaType.parse("multipart/form-data"), edtBrandName.getText().toString().trim());
        RequestBody brandName = RequestBody.create(MediaType.parse("multipart/form-data"), edtBrandName.getText().toString().trim());
        MultipartBody.Part image = null;
        if (imgUrl != null) {
            File file = new File(RealPathUtil.getRealPath(this, imgUrl));
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            image = MultipartBody.Part.createFormData("images", file.getName(), requestFile);
            Log.e("images", image.toString());
        }

        Call<ResponseObject> call = brandAPI.update(oldName, brandName, image);
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    ResponseObject result = response.body();
                    if (result != null && result.getStatus().equals("Success")) {
                        Toast.makeText(EditBrandDialogActivity.this, "Brand updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditBrandDialogActivity.this, "Failed to update brand: " + result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditBrandDialogActivity.this, "Failed to update brand", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(EditBrandDialogActivity.this, AdminPanelBrandActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(EditBrandDialogActivity.this, "Failed to call API" + t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditBrandDialogActivity.this, AdminPanelBrandActivity.class));
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