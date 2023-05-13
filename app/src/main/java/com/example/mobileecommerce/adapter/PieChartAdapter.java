package com.example.mobileecommerce.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.List;

public class PieChartAdapter extends RecyclerView.Adapter<PieChartAdapter.PieChartViewHolder> {

    private List<PieEntry> pieEntries;

    public PieChartAdapter(List<PieEntry> pieEntries) {
        this.pieEntries = pieEntries;
    }

    @NonNull
    @Override
    public PieChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pie_chart, parent, false);
        return new PieChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PieChartViewHolder holder, int position) {
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);

        // Set colors for slices
        pieDataSet.setColors(new int[] { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED, Color.MAGENTA, Color.CYAN, Color.GRAY });

        PieData pieData = new PieData(pieDataSet);
        holder.pieChart.setData(pieData);
        holder.pieChart.setEntryLabelColor(Color.WHITE);
        holder.pieChart.setEntryLabelTextSize(12f);
        holder.pieChart.getDescription().setEnabled(false);
        holder.pieChart.animateY(500);
        holder.pieChart.invalidate();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class PieChartViewHolder extends RecyclerView.ViewHolder {

        private PieChart pieChart;

        public PieChartViewHolder(@NonNull View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.pie_chart);
        }
    }
}