package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobileecommerce.R;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000L);
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    SplashScreenActivity.this.finish();
                } catch (Exception unused) {
                }
            }
        }.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}