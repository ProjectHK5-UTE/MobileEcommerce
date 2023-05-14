package com.example.mobileecommerce.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.model.dto.ResponseDTO;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.service.RealPathUtil;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 1;
    TextView title;
    ImageView iv_back;
    Button chooseImage, btnUploadImage;
    TextView viewGallary;
    ImageView imgPreview;
    Uri imgUrl;

    Uri mUri;

    ProgressDialog mProgressDialog;

    CustomerAPI customerAPI = RetrofitClient.getRetrofit().create(CustomerAPI.class);
    private String username;
    static SharedPreferences pres;

    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_upload_image);
        this.title = (TextView) findViewById(R.id.title);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.title.setText("EDIT PROFILE AVATAR");
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UploadImageActivity.this.finish();
            }
        });
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait....");

        chooseImage = (Button) findViewById(R.id.chooseImage);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);
        viewGallary = (TextView) findViewById(R.id.viewGallery);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);

        callAPIGetUserName();

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChoose();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null) {
                    UpdateAvatar();
                }
            }
        });
    }

    private void callAPIGetUserName() {
        String email = SharedPreferences.getEmail();
        customerAPI.getUserName(email).enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                if(response.isSuccessful()){
                    username= response.body().getMessage();
                    getImage(username);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Log.e("CALL API get Username","Fail");
            }
        });
    }

    private void showFileChoose(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,CHOOSE_IMAGE);
    }

    private void UpdateAvatar() {
        mProgressDialog.show();
        RequestBody responseBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"), username);

        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        Log.e("Real Path", "là"+strRealPath);
        File file = new File(strRealPath);
        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvt = MultipartBody.Part.createFormData("images", file.getName(), requestBodyAvt);
        customerAPI.updateAvatar(responseBodyUsername, multipartBodyAvt).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                mProgressDialog.dismiss();
                if(response.isSuccessful()) {
                    Toast.makeText(UploadImageActivity.this, "Avatar update successful", Toast.LENGTH_SHORT).show();
                    System.out.println("Thay đổi avatar thành công");
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("chang images", t.toString());
                mProgressDialog.dismiss();
                Toast.makeText(UploadImageActivity.this, "Avatar update failed", Toast.LENGTH_SHORT).show();
                System.out.println("Thay đổi avatar thất bại!");
            }
        });
    }

    private void getImage(String username) {
        customerAPI.getImage(username).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    ResponseBody body = response.body();
                    InputStream inputStream = body.byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgPreview.setImageBitmap(bitmap);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UploadImageActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imgUrl = data.getData();
            mUri = imgUrl;
            Picasso.get().load(imgUrl).into(imgPreview);
        }
    }
}