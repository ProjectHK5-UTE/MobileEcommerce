package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.BarChartAdapter;
import com.example.mobileecommerce.adapter.PieChartAdapter;
import com.example.mobileecommerce.api.MonthlyBillAPI;
import com.example.mobileecommerce.api.OrderByBrandAPI;
import com.example.mobileecommerce.api.StatisticAPI;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPanelHomeActivity extends AppCompatActivity {

    ImageView brandImageView;

    ImageView iv_back;
    TextView reviewsQuantityTextView;
    TextView productsQuantityTextView;
    TextView customersQuantityTextView;
    TextView revenueQuantityTextView;
    TextView title;
    List<Float> listStatistic;
    RecyclerView pieChart;
    RecyclerView barChart;
    StatisticAPI statisticAPI;
    MonthlyBillAPI monthlyBillAPI;
    OrderByBrandAPI orderByBrandAPI;

    static android.content.SharedPreferences pres;
    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_panel_home);

        // Ánh xạ các thành phần từ layout
        brandImageView = (ImageView) findViewById(R.id.brand_manage);
        productsQuantityTextView = (TextView) findViewById(R.id.products_quantity_textview);
        customersQuantityTextView = (TextView) findViewById(R.id.customers_quantity_textview);
        revenueQuantityTextView = (TextView) findViewById(R.id.revenue_quantity_textview);
        reviewsQuantityTextView = (TextView) findViewById(R.id.review_quantity_textview);
        title = (TextView) findViewById(R.id.title);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        listStatistic = new ArrayList<Float>();
        pieChart = (RecyclerView) findViewById(R.id.pie_chart_recyclerview);
        barChart = (RecyclerView) findViewById(R.id.bar_chart_recyclerview);

        this.title.setText("Dashboard Home");
        brandImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanelHomeActivity.this, AdminPanelBrandActivity.class);
                startActivity(intent);
            }
        });
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeData();
                gotoLogin();
            }
        });
        // Call API get Data cho số liệu thống kê
        statisticAPI = RetrofitClient.getRetrofit().create(StatisticAPI.class);
        Call<HashMap<String, Float>> call = statisticAPI.getStatistics();
        call.enqueue(new Callback<HashMap<String, Float>>() {
            @Override
            public void onResponse(Call<HashMap<String, Float>> call, Response<HashMap<String, Float>> response) {
                if(response.isSuccessful()){
                    HashMap<String, Float> hashMap = response.body();
                    List<Float> values = new ArrayList<>();
                    Iterator<Map.Entry<String, Float>> it = hashMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Float> pair = (Map.Entry) it.next();
                        float value = pair.getValue();
                        values.add(new Float(value));
                    }
                    listStatistic.addAll(values);
                    productsQuantityTextView.setText(String.valueOf(listStatistic.get(0)));
                    customersQuantityTextView.setText(String.valueOf(listStatistic.get(1)));
                    revenueQuantityTextView.setText('$'+String.valueOf(listStatistic.get(2)));
                    reviewsQuantityTextView.setText(String.valueOf(listStatistic.get(3)));
                }
                else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<HashMap<String, Float>> call, Throwable t) {

            }
        });

        // Call API get Data cho biểu đồ tròn
        orderByBrandAPI = RetrofitClient.getRetrofit().create(OrderByBrandAPI.class);
        Call<HashMap<String, Float>> call1 = orderByBrandAPI.getTotalPriceOfBrand();
        call1.enqueue(new Callback<HashMap<String, Float>>() {
            @Override
            public void onResponse(Call<HashMap<String, Float>> call1, Response<HashMap<String, Float>> response) {
                if(response.isSuccessful()){
                    HashMap<String, Float> hashMap = response.body();
                    List<PieEntry> entries = new ArrayList<>();
                    Iterator it = hashMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        String label = (String) pair.getKey();
                        float value = (Float) pair.getValue();
                        entries.add(new PieEntry(value, label));
                    }
                    PieChartAdapter barChartAdapter = new PieChartAdapter(entries);
                    pieChart.setAdapter(barChartAdapter);
                    pieChart.setLayoutManager(new LinearLayoutManager(AdminPanelHomeActivity.this));
                }
                else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<HashMap<String, Float>> call1, Throwable t) {

            }
        });

        // Call API get Data cho biểu đồ cột
        monthlyBillAPI = RetrofitClient.getRetrofit().create(MonthlyBillAPI.class);
        Call<TreeMap<String,Float>> call2 = monthlyBillAPI.getBillInMonth();
        call2.enqueue(new Callback<TreeMap<String, Float>>() {
            @Override
            public void onResponse(Call<TreeMap<String, Float>> call2, Response<TreeMap<String, Float>> response) {
                if(response.isSuccessful()){
                    TreeMap<String,Float> treeMap = response.body();
                    List<BarEntry> entries = new ArrayList<>();
                    for (Map.Entry<String, Float> entry : treeMap.entrySet()) {
                        float value = entry.getValue().floatValue(); // Chuyển đổi kiểu dữ liệu từ Integer sang float
                        int index = Integer.parseInt(entry.getKey());
                        BarEntry barEntry = new BarEntry(index, value);
                        entries.add(barEntry);
                        index++;
                    }
                    BarChartAdapter barChartAdapter = new BarChartAdapter(entries);
                    barChart.setAdapter(barChartAdapter);
                    barChart.setLayoutManager(new LinearLayoutManager(AdminPanelHomeActivity.this));
                }
                else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<TreeMap<String, Float>> call2, Throwable t) {

            }
        });
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void removeData() {
        SharedPreferences.removeJWT();
        SharedPreferences.removeEmail();
        SharedPreferences.removeUsername();
    }
}