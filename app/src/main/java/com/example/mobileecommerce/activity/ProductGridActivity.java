package com.example.mobileecommerce.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
    static int id;
    EditText inputSearch;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_grid);
        anhXa();
        this.title.setText("Product Grid");
        Intent intent = getIntent();
        id = intent.getIntExtra("idBrand", 0);
        ProductGridActivity();

        //Search products by name
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchQuery = s.toString();
                // Gọi phương thức tìm kiếm sản phẩm với searchQuery
                searchProduct(searchQuery);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void searchProduct(String searchQuery) {
        // Tìm kiếm sản phẩm theo tên
        List<ProductGridModel> productList = getProductListByName(searchQuery);

        // Cập nhật danh sách sản phẩm trong RecyclerView
        mAdapter2.setProductList(productList);
    }
    private List<ProductGridModel> getProductListByName(String searchQuery) {
        List<ProductGridModel> productList = new ArrayList<>();
        for (ProductGridModel product : productGridModelList) {
            if (product.getProductName().toLowerCase().contains(searchQuery.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }
    @SuppressLint("NotConstructor")
    public void ProductGridActivity() {
        productAPI= RetrofitClient.getRetrofit().create(ProductAPI.class);
        productAPI.getProductByBrand(id).enqueue(new Callback<List<ProductGridModel>>() {
            @Override
            public void onResponse(Call<List<ProductGridModel>> call, Response<List<ProductGridModel>> response) {
                if(response.isSuccessful()){
                    productGridModelList = response.body();
                    mAdapter2 = new RecycleAdapterProductGrid(productGridModelList, ProductGridActivity.this);
                    recyclerview.setLayoutManager(new GridLayoutManager(ProductGridActivity.this, 2));
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                }else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<ProductGridModel>> call, Throwable t) {
            }
        });
    }
    void anhXa(){
        recyclerview = findViewById(R.id.recyclerview_product_grid);
        title = findViewById(R.id.title);
        iv_back = findViewById(R.id.iv_back);
        inputSearch = findViewById(R.id.search_editext);
    }
}
