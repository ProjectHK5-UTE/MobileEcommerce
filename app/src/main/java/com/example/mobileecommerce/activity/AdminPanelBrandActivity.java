package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.AdminRecycleAdapterBrandsList;
import com.example.mobileecommerce.model.CategoriesListModellClass;

import java.util.ArrayList;

public class AdminPanelBrandActivity extends AppCompatActivity {
    private ArrayList<CategoriesListModellClass> categoriesListModellClasses;
    private AdminRecycleAdapterBrandsList mAdapter2;
    Button btnAdd;
    RecyclerView recyclerview;
    private Integer[] image1 = {Integer.valueOf((int) R.drawable.mobile_cat), Integer.valueOf((int) R.drawable.tv_cat), Integer.valueOf((int) R.drawable.laptop_cat), Integer.valueOf((int) R.drawable.all_cat)};
    private String[] title1 = {"Mobile", "TV", "Laptop", "All"};
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_panel_brand);

        title = (TextView) findViewById(R.id.title);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        this.title.setText("Brand Management");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBrandDialogActivity dialog = new AddBrandDialogActivity(AdminPanelBrandActivity.this);
                dialog.show();
            }
        });

        this.categoriesListModellClasses = new ArrayList<>();
        for (int i = 0; i < this.image1.length; i++) {
            this.categoriesListModellClasses.add(new CategoriesListModellClass(this.image1[i], this.title1[i]));
        }
        this.mAdapter2 = new AdminRecycleAdapterBrandsList(this, this.categoriesListModellClasses);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setItemAnimator(new DefaultItemAnimator());
        this.recyclerview.setAdapter(this.mAdapter2);
    }
}