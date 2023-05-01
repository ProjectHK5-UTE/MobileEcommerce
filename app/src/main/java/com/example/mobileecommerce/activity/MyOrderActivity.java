package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.MyOrderRecycleAdapter;
import com.example.mobileecommerce.model.MyOrderModelClass;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class MyOrderActivity extends AppCompatActivity {
    FrameLayout fl_ecart;
    ImageView iv_back;
    private MyOrderRecycleAdapter mAdapter2;
    private ArrayList<MyOrderModelClass> myOrderModelClasses;
    private RecyclerView recyclerview;
    TextView title;
    private Integer[] image = {Integer.valueOf((int) R.drawable.s9plus), Integer.valueOf((int) R.drawable.shoesfila)};
    private String[] title1 = {"Samsung Galaxy S9 Plus", "Fila Super"};
    private String[] price = {"Rs. 30,000", "Rs. 2500"};
    private String[] qty = {"1", "1"};
    private String[] date = {"28/01/2019 5:00PM", "12/02/2019 11:00 AM"};
    private String[] order_no = {"#412458", "#154352"};

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_order);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("My Order");
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyOrderActivity.this.finish();
            }
        });
        this.fl_ecart.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyOrderActivity.this.startActivity(new Intent(MyOrderActivity.this, MyCartActivity.class));
            }
        });
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        this.myOrderModelClasses = new ArrayList<>();
        for (int i = 0; i < this.image.length; i++) {
            this.myOrderModelClasses.add(new MyOrderModelClass(this.image[i], this.title1[i], this.price[i], this.qty[i], this.date[i], this.order_no[i]));
        }
        this.mAdapter2 = new MyOrderRecycleAdapter(this, this.myOrderModelClasses);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setItemAnimator(new DefaultItemAnimator());
        this.recyclerview.setAdapter(this.mAdapter2);
    }
}
