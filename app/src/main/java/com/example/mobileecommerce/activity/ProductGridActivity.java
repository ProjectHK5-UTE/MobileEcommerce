package com.example.mobileecommerce.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.RecycleAdapteProductGrid;
import com.example.mobileecommerce.model.ProductGridModellClass;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class ProductGridActivity extends AppCompatActivity {
    private Integer[] image1;
    ImageView iv_back;
    private RecycleAdapteProductGrid mAdapter2;
    private ArrayList<ProductGridModellClass> productGridModellClasses;
    private RecyclerView recyclerview;
    TextView title;

    public ProductGridActivity() {
        Integer valueOf = Integer.valueOf((int) R.drawable.s9plus);
        this.image1 = new Integer[]{valueOf, valueOf, valueOf, valueOf, valueOf, valueOf};
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_grid);
        this.title.setText("Product Grid");
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductGridActivity.this.finish();
            }
        });

    }
    void anhXa(){
        recyclerview = findViewById(R.id.recyclerview);
        title = findViewById(R.id.title);
        iv_back = findViewById(R.id.iv_back);
    }
}
