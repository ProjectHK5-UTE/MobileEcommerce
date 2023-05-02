package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileecommerce.R;

/* loaded from: classes.dex */
public class MyCartActivity extends AppCompatActivity {
    ImageView add;
    LinearLayout bottom_linear;
    ImageView iv_back;
    LinearLayout linear_add;
    LinearLayout linear_count;
    ImageView minus;
    TextView number;
    TextView text_checkout;
    TextView title;
    int count = 1;
    int adult = 1;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_cart);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("Cart");
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyCartActivity.this.finish();
            }
        });
        this.linear_count = (LinearLayout) findViewById(R.id.linear_count);
        this.add = (ImageView) findViewById(R.id.plus);
        this.minus = (ImageView) findViewById(R.id.minus);
        this.number = (TextView) findViewById(R.id.number);
        this.add.setOnClickListener(new View.OnClickListener() {

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyCartActivity.this.count++;
                MyCartActivity.this.number.setText(String.valueOf(MyCartActivity.this.count));
            }
        });
        this.minus.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MyCartActivity.this.count != 1) {
                    MyCartActivity.this.count--;
                    MyCartActivity.this.number.setText(String.valueOf(MyCartActivity.this.count));
                }
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.text_checkout);
        this.text_checkout = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyCartActivity.this.startActivity(new Intent(MyCartActivity.this, AddAddressActivity.class));
            }
        });
    }
}
