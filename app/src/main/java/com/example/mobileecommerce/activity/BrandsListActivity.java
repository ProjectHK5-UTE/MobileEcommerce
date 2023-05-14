package com.example.mobileecommerce.activity;

import android.content.Intent;
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
import com.example.mobileecommerce.adapter.RecycleAdapterBrandsList;
import com.example.mobileecommerce.api.BrandAPI;
import com.example.mobileecommerce.model.BrandsModel;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsListActivity extends AppCompatActivity {
    private List<BrandsModel> brandsModels;
    private RecycleAdapterBrandsList mAdapter2;
    private RecyclerView recyclerview;
    ImageView iv_back;

    TextView title;
    BrandAPI brandAPI;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_brands_product_list);
        anhXa();
        title.setText("Brands List");
        GetBrands();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(BrandsListActivity.this, HomePageActivity.class);
                startActivity(intent);*/
                onBackPressed();
            }
        });
    }
    private void GetBrands(){
        brandAPI= RetrofitClient.getRetrofit().create(BrandAPI.class);
        brandAPI.getBrands().enqueue(new Callback<List<BrandsModel>>() {
            @Override
            public void onResponse(Call<List<BrandsModel>> call, Response<List<BrandsModel>> response) {
                if(response.isSuccessful()){
                    brandsModels = response.body();
                    mAdapter2 = new RecycleAdapterBrandsList(brandsModels, BrandsListActivity.this);
                    recyclerview.setLayoutManager(new LinearLayoutManager(BrandsListActivity.this));
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
        void anhXa(){
            recyclerview = findViewById(R.id.recyclerview_brands);
            iv_back = findViewById(R.id.iv_back);
            title = findViewById(R.id.title);
        }
    }
