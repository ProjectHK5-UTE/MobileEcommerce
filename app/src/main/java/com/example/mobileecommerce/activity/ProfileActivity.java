package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileecommerce.R;

public class ProfileActivity extends AppCompatActivity {
    TextView title;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        //
        this.title = (TextView) findViewById(R.id.title);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        Button btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        Button btnChangePassword = (Button) findViewById(R.id.btnChangePwd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileActivity.this.finish();
            }
        });
        this.title.setText("My Profile");
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, UpdatePasswordActivity.class));
            }
        });
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, UploadImageActivity.class));
            }
        });
    }
}