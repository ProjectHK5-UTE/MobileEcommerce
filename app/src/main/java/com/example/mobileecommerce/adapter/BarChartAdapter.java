package com.example.mobileecommerce.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class BarChartAdapter extends RecyclerView.Adapter<BarChartAdapter.BarChartViewHolder> {

    private List<BarEntry> barEntries;

    public BarChartAdapter(List<BarEntry> barEntries) {
        this.barEntries = barEntries;
    }

    @NonNull
    @Override
    public BarChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bar_chart, parent, false);
        return new BarChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarChartViewHolder holder, int position) {
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(new int[] { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED, Color.MAGENTA, Color.CYAN, Color.GRAY });
        barDataSet.setValueTextColor(Color.RED);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        holder.barChart.setData(barData);
        holder.barChart.getDescription().setEnabled(false);
        holder.barChart.animateY(500);
        holder.barChart.invalidate();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class BarChartViewHolder extends RecyclerView.ViewHolder {

        private BarChart barChart;

        public BarChartViewHolder(@NonNull View itemView) {
            super(itemView);
            barChart = itemView.findViewById(R.id.bar_chart);
        }
    }
}