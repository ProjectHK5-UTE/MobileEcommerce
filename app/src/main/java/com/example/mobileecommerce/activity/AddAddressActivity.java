package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileecommerce.R;

/* loaded from: classes.dex */
public class AddAddressActivity extends AppCompatActivity {
    ImageView iv_back;
    TextView text_save;
    TextView title;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_address);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("Add New Address");
        this.text_save = (TextView) findViewById(R.id.text_save);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddAddressActivity.this.finish();
            }
        });
        this.text_save.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddAddressActivity.this.startActivity(new Intent(AddAddressActivity.this, PaymentActivity.class));
            }
        });
    }
}
