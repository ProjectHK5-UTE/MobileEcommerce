package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.BarChartAdapter;
import com.example.mobileecommerce.adapter.PieChartAdapter;
import com.example.mobileecommerce.api.MonthlyBillAPI;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPanelHomeActivity extends AppCompatActivity {

    ImageView brandImageView;
    TextView productsQuantityTextView;
    TextView customersQuantityTextView;
    TextView revenueQuantityTextView;
    TextView title;
    RecyclerView pieChart;
    RecyclerView barChart;
    BarChartAdapter mAdapter;
    MonthlyBillAPI monthlyBillAPI;

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
        title = (TextView) findViewById(R.id.title);
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

        // Thiết lập dữ liệu cứng cho các thành phần
        this.productsQuantityTextView.setText("1000");
        this.customersQuantityTextView.setText("500");
        this.revenueQuantityTextView.setText("$10,000");

        // Thiết lập dữ liệu cứng cho biểu đồ tròn
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(30f, "Brand 1"));
        pieEntries.add(new PieEntry(40f, "Brand 2"));
        pieEntries.add(new PieEntry(20f, "Brand 3"));
        pieEntries.add(new PieEntry(10f, "Brand 4"));
        PieChartAdapter pieChartAdapter = new PieChartAdapter(pieEntries);
        pieChart.setAdapter(pieChartAdapter);
        pieChart.setLayoutManager(new LinearLayoutManager(this));

        // Call API get Data cho biểu đồ cột
        monthlyBillAPI = RetrofitClient.getRetrofit().create(MonthlyBillAPI.class);
        Call<TreeMap<String, Float>> call = monthlyBillAPI.getBillInMonth();
        call.enqueue(new Callback<TreeMap<String, Float>>() {
            @Override
            public void onResponse(Call<TreeMap<String, Float>> call, Response<TreeMap<String, Float>> response) {
                if(response.isSuccessful()){
                    TreeMap<String, Float> treeMap = response.body();
                    List<BarEntry> entries = new ArrayList<>();
                    int index = -2;
                    for (Map.Entry<String, Float> entry : treeMap.entrySet()) {
                        float value = entry.getValue().floatValue(); // Chuyển đổi kiểu dữ liệu từ Integer sang float
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
            public void onFailure(Call<TreeMap<String, Float>> call, Throwable t) {

            }
        });
    }
}