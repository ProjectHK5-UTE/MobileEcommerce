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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class AdminPanelHomeActivity extends AppCompatActivity {

    ImageView brandImageView;
    TextView productsQuantityTextView;
    TextView customersQuantityTextView;
    TextView revenueQuantityTextView;
    TextView title;

    private PieChart pieChart;
    private BarChart barChart;

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
        RecyclerView pieChart = (RecyclerView) findViewById(R.id.pie_chart_recyclerview);
        RecyclerView barChart = (RecyclerView) findViewById(R.id.bar_chart_recyclerview);

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

        // Thiết lập dữ liệu cứng cho biểu đồ cột
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 1000));
        barEntries.add(new BarEntry(2f, 2000));
        barEntries.add(new BarEntry(3f, 1500));
        barEntries.add(new BarEntry(4f, 3000));
        barEntries.add(new BarEntry(5f, 2500));
        barEntries.add(new BarEntry(6f, 4000));
        barEntries.add(new BarEntry(7f, 3500));
        BarChartAdapter barChartAdapter = new BarChartAdapter(barEntries);
        barChart.setAdapter(barChartAdapter);
        barChart.setLayoutManager(new LinearLayoutManager(this));
    }
}