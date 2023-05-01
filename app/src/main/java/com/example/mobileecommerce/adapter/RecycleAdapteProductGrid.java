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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;
import com.example.mobileecommerce.model.ProductGridModellClass;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecycleAdapteProductGrid extends RecyclerView.Adapter<RecycleAdapteProductGrid.MyViewHolder> {
    Context context;
    private List<ProductGridModellClass> moviesList;
    int myPos = 0;

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

    public RecycleAdapteProductGrid(Context context, ArrayList<ProductGridModellClass> list) {
        this.moviesList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_grid_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.image.setImageResource(this.moviesList.get(i).getImage().intValue());
        myViewHolder.offer.setText("Rs 60,000");
        myViewHolder.offer.setPaintFlags(myViewHolder.offer.getPaintFlags() | 16);
        if ((i == 2) | (i == 5)) {
            myViewHolder.text.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.text.setVisibility(View.GONE);
        }
        myViewHolder.rl_click_product_detail.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecycleAdapteProductGrid.this.context.startActivity(new Intent(RecycleAdapteProductGrid.this.context, ProductDetailActivity.class));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.moviesList.size();
    }
}
