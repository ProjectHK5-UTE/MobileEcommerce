package com.example.mobileecommerce.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.RecycleAdapterBrandsList;
import com.example.mobileecommerce.adapter.RecycleAdapterProductGrid;
import com.example.mobileecommerce.api.BrandAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ProductGridActivity extends AppCompatActivity {
    private List<ProductGridModel> productGridModelList;
    private RecycleAdapterProductGrid mAdapter2;
    private RecyclerView recyclerview;
    ImageView iv_back;
    TextView title;
    ProductAPI productAPI;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_grid);
        anhXa();
        this.title.setText("Product Grid");
        ProductGridActivity();
    }

    @SuppressLint("NotConstructor")
    public void ProductGridActivity() {
        productAPI= RetrofitClient.getRetrofit().create(ProductAPI.class);
        productAPI.getProductByBrand().enqueue(new Callback<List<ProductGridModel>>() {
            @Override
            public void onResponse(Call<List<ProductGridModel>> call, Response<List<ProductGridModel>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProductGridActivity.this, "dadaad", Toast.LENGTH_SHORT).show();
                    productGridModelList = response.body();
                    mAdapter2 = new RecycleAdapterProductGrid(productGridModelList, ProductGridActivity.this);
                    recyclerview.setLayoutManager(new GridLayoutManager(ProductGridActivity.this, 2));
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }else{
                    int statusCode = response.code();
                    Toast.makeText(ProductGridActivity.this, "dm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductGridModel>> call, Throwable t) {
                Toast.makeText(ProductGridActivity.this, "cc", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void anhXa(){
        recyclerview = findViewById(R.id.recyclerview);
        title = findViewById(R.id.title);
        iv_back = findViewById(R.id.iv_back);
    }
}
