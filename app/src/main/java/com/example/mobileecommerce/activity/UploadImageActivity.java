package com.example.mobileecommerce.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.squareup.picasso.Picasso;

public class UploadImageActivity extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 1;
    TextView title;
    ImageView iv_back;

    Button chooseImage, btnUploadImage;
    TextView viewGallary;
    ImageView imgPreview;
    Uri imgUrl;

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
        chooseImage = (Button) findViewById(R.id.chooseImage);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);
        viewGallary = (TextView) findViewById(R.id.viewGallery);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChoose();
            }
        });
    }
    private void showFileChoose(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,CHOOSE_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imgUrl = data.getData();
            Picasso.get().load(imgUrl).into(imgPreview);
        }
    }
}