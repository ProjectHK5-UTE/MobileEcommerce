package com.example.mobileecommerce.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.RecycleAdapteCategoriesList;
import com.example.mobileecommerce.model.CategoriesListModellClass;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class CategoriesListActivity extends AppCompatActivity {
    private ArrayList<CategoriesListModellClass> categoriesListModellClasses;
    ImageView iv_back;
    private RecycleAdapteCategoriesList mAdapter2;
    private RecyclerView recyclerview;
    TextView title;
    private Integer[] image1 = {Integer.valueOf((int) R.drawable.mobile_cat), Integer.valueOf((int) R.drawable.tv_cat), Integer.valueOf((int) R.drawable.laptop_cat), Integer.valueOf((int) R.drawable.all_cat)};
    private String[] title1 = {"Mobile", "TV", "Laptop", "All"};

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_list);
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("Categories");
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.activity.CategoriesListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CategoriesListActivity.this.finish();
            }
        });
        this.categoriesListModellClasses = new ArrayList<>();
        for (int i = 0; i < this.image1.length; i++) {
            this.categoriesListModellClasses.add(new CategoriesListModellClass(this.image1[i], this.title1[i]));
        }
        this.mAdapter2 = new RecycleAdapteCategoriesList(this, this.categoriesListModellClasses);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setItemAnimator(new DefaultItemAnimator());
        this.recyclerview.setAdapter(this.mAdapter2);
    }
}
