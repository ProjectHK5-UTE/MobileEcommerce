package com.example.mobileecommerce.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.RecycleAdapteProductList;
import com.example.mobileecommerce.model.ProductListModelClass;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class ProductListActivity extends AppCompatActivity {
    ImageView iv_back;
    private Integer[] like;
    private RecycleAdapteProductList mAdapter2;
    private ArrayList<ProductListModelClass> productListModelClasses;
    private RecyclerView recyclerview;
    private Integer[] image1 = {Integer.valueOf((int) R.drawable.s9plus), Integer.valueOf((int) R.drawable.iphnx), Integer.valueOf((int) R.drawable.googlepixel), Integer.valueOf((int) R.drawable.vivo11)};
    private String[] title1 = {"Samsung S9 Plus", "I Phone X", "Google Pixel 3", "Vivo V-11 Pro"};

    public ProductListActivity() {
        Integer valueOf = Integer.valueOf((int) R.drawable.ic_heart_light);
        Integer valueOf2 = Integer.valueOf((int) R.drawable.ic_dark_like);
        this.like = new Integer[]{valueOf, valueOf2, valueOf2, valueOf};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_list);
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductListActivity.this.finish();
            }
        });
        this.productListModelClasses = new ArrayList<>();
        for (int i = 0; i < this.image1.length; i++) {
            this.productListModelClasses.add(new ProductListModelClass(this.image1[i], this.title1[i], this.like[i]));
        }
        this.mAdapter2 = new RecycleAdapteProductList(this, this.productListModelClasses);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setItemAnimator(new DefaultItemAnimator());
        this.recyclerview.setAdapter(this.mAdapter2);
    }
}
