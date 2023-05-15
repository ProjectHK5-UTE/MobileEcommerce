package com.example.mobileecommerce.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Range;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.Dialog;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.ProductRecycleAdapter;
import com.example.mobileecommerce.adapter.RecycleAdapteProductList;
import com.example.mobileecommerce.api.BrandAPI;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.ProductListModelClass;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.google.android.material.slider.RangeSlider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ProductListActivity extends AppCompatActivity {

    ImageView iv_back;
    private RecyclerView recyclerView;

    private ProductRecycleAdapter productRecycleAdapter;

    private List<ProductGridModel> listProduct;

    ProductAPI productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);


    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_list);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        ImageView imgSearch = findViewById(R.id.img_click_search);
        EditText edtSearch = findViewById(R.id.edit_txt_search);
        this.iv_back = imageView;
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerview_list_product);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        LoadAllProduct();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSearch.getText().toString().equals("")) {
                    LoadAllProduct();
                    return;
                }

                productAPI.searchProduct(edtSearch.getText().toString()).enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        if (response.isSuccessful()) {
                            Gson gson = new Gson();
                            String json = gson.toJson(response.body().getData());
                            Type productListType = new TypeToken<List<ProductGridModel>>(){}.getType();
                            List<ProductGridModel> productList = gson.fromJson(json, productListType);
                            productRecycleAdapter = new ProductRecycleAdapter(productList, ProductListActivity.this);
                            recyclerView.setAdapter(productRecycleAdapter);
                        }
                        else {
                            productRecycleAdapter = new ProductRecycleAdapter(new ArrayList<>(), ProductListActivity.this);
                            recyclerView.setAdapter(productRecycleAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {

                    }
                });
            }
        });

        Button btnShowFilter = findViewById(R.id.btn_show_filter_product);
        btnShowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFilterDialog();
            }
        });
    }

    private void LoadAllProduct() {
        productAPI.getAllProduct().enqueue(new Callback<List<ProductGridModel>>() {
            @Override
            public void onResponse(Call<List<ProductGridModel>> call, Response<List<ProductGridModel>> response) {
                listProduct = response.body();
                productRecycleAdapter = new ProductRecycleAdapter(listProduct, ProductListActivity.this);
                recyclerView.setAdapter(productRecycleAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductGridModel>> call, Throwable t) {

            }
        });
    }

    private void OpenFilterDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_product);

        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);


        Button btnExitFilter = dialog.findViewById(R.id.btnThoatFilter);
        Button btnReset = dialog.findViewById(R.id.btnReset);
        Button btnFilter = dialog.findViewById(R.id.btnFilter);
        RangeSlider sliderPrice = dialog.findViewById(R.id.slider_price);
        RangeSlider sliderBattery = dialog.findViewById(R.id.slider_battery);
        RangeSlider sliderScreen = dialog.findViewById(R.id.slider_screen);

//        double min_price, max_price, min_screen, max_screen;
//        int min_battery, max_battery;

        btnExitFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderPrice.setValues(0f, 3000f);
                sliderBattery.setValues(2000f, 6000f);
                sliderScreen.setValues(0f, 8f);
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double startPrice = sliderPrice.getValues().get(0);
                double endPrice = sliderPrice.getValues().get(1);
                int startBattery = sliderBattery.getValues().get(0).intValue();
                int endBattery = sliderBattery.getValues().get(1).intValue();
                double startScreen = sliderScreen.getValues().get(0);
                double endScreen = sliderScreen.getValues().get(1);
                productAPI.filterProduct(startPrice, endPrice, startBattery, endBattery, startScreen, endScreen).enqueue(new Callback<List<ProductGridModel>>() {
                    @Override
                    public void onResponse(Call<List<ProductGridModel>> call, Response<List<ProductGridModel>> response) {
                        listProduct = response.body();
                        productRecycleAdapter = new ProductRecycleAdapter(listProduct, ProductListActivity.this);
                        recyclerView.setAdapter(productRecycleAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<ProductGridModel>> call, Throwable t) {
                    }
                });
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
