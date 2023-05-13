package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.AdminRecycleAdapterBrandsList;
import com.example.mobileecommerce.api.BrandAPI;
import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPanelBrandActivity extends AppCompatActivity {
    private List<BrandsModel> brandsModels;
    private AdminRecycleAdapterBrandsList mAdapter2;
    Button btnAdd;
    RecyclerView recyclerview;
    TextView title;
    BrandAPI brandAPI;

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
                Intent intent = new Intent(AdminPanelBrandActivity.this.getBaseContext(), AddBrandDialogActivity.class);
                finish();
                startActivity(intent);
            }
        });
        brandsModels = new ArrayList<>();
        GetBrands();
    }

    private void GetBrands(){
        brandAPI= RetrofitClient.getRetrofit().create(BrandAPI.class);
        brandAPI.getBrands().enqueue(new Callback<List<BrandsModel>>() {
            @Override
            public void onResponse(Call<List<BrandsModel>> call, Response<List<BrandsModel>> response) {
                if(response.isSuccessful()){
                    brandsModels = response.body();
                    mAdapter2 = new AdminRecycleAdapterBrandsList(brandsModels, AdminPanelBrandActivity.this);
                    recyclerview.setLayoutManager(new LinearLayoutManager(AdminPanelBrandActivity.this));
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<BrandsModel>> call, Throwable t) {

            }
        });
    }
}