package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecycleAdapteProductGrid extends RecyclerView.Adapter<RecycleAdapteProductGrid.MyViewHolder> {
    Context context;
    //private List<ProductGridModel> moviesList;
    int myPos = 0;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        LinearLayout linear;
        TextView offer;
        RelativeLayout rl_click_product_detail;
        TextView text;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.offer = (TextView) view.findViewById(R.id.offer);
            this.text = (TextView) view.findViewById(R.id.text);
            this.rl_click_product_detail = (RelativeLayout) view.findViewById(R.id.rl_click_product_detail);
        }
    }


}
