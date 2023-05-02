package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileecommerce.R;

/* loaded from: classes.dex */
public class PaymentActivity extends AppCompatActivity {
    FrameLayout fl_ecart;
    ImageView iv_back;
    TextView text_paynow;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.text_paynow = (TextView) findViewById(R.id.text_paynow);
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentActivity.this.finish();
            }
        });
        this.fl_ecart.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentActivity.this.startActivity(new Intent(PaymentActivity.this, MyCartActivity.class));
            }
        });
        this.text_paynow.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentActivity.this.startActivity(new Intent(PaymentActivity.this, SucessfullActivity.class));
            }
        });
    }
}
