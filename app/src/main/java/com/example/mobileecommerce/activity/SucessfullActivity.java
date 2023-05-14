package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileecommerce.R;

public class SucessfullActivity extends AppCompatActivity {
    Button btnBuying;
    Button btnOrder;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sucessfull);
        this.btnOrder = (Button) findViewById(R.id.btnOrder);
        this.btnBuying = (Button) findViewById(R.id.btnBuying);
        this.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SucessfullActivity.this.startActivity(new Intent(SucessfullActivity.this, MyOrderActivity.class));
            }
        });
        this.btnBuying.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(SucessfullActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
