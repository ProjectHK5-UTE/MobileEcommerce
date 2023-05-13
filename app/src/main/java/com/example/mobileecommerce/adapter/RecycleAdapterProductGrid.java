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

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;
import com.example.mobileecommerce.activity.ProductGridActivity;
import com.example.mobileecommerce.model.ProductGridModel;

import java.util.List;

/* loaded from: classes.dex */
public class RecycleAdapterProductGrid extends RecyclerView.Adapter<RecycleAdapterProductGrid.MyViewHolder> {
    private List<ProductGridModel> productList;
    private Context context;

    public RecycleAdapterProductGrid(List<ProductGridModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grid_list,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int id = position;
        ProductGridModel product = productList.get(id);
        holder.title.setText(product.getProductName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.os.setText(product.getOs());
        Glide.with(context)
                .load(product.getOptions().get(0).getImages().get(0).getPath())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                intent.putExtra("product", productList.get(id));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(productList!= null){
            return productList.size();
        }
        return 0;
    }

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        RelativeLayout rl_click_product_detail;
        TextView title, price, os;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image_product_grid);
            title =  view.findViewById(R.id.tv_pGrid_title);
            price = view.findViewById(R.id.tv_pGrid_price);
            os = view.findViewById(R.id.tv_pGrid_os);
            rl_click_product_detail = (RelativeLayout) view.findViewById(R.id.rl_click_product_detail);
        }
    }
    public void setProductList(List<ProductGridModel> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
}
