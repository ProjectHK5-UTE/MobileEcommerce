package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.ProductGridModel;

import java.util.List;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.MyViewHolder> {
    private List<ProductGridModel> listProduct;

    private Context context;

    public ProductRecycleAdapter(List<ProductGridModel> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecycleAdapter.MyViewHolder holder, int position) {
        ProductGridModel productModel = listProduct.get(position);
        if(productModel == null)
            return;
        holder.title.setText(productModel.getProductName());
        Glide.with(context)
                .load(productModel.getOptions().get(0).getImages().get(0).getPath())
                .into(holder.images);
        holder.subTitle.setText(productModel.getCpu());
        holder.price.setText(String.valueOf(productModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(listProduct != null) {
            return listProduct.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView images;
        private TextView title;
        private TextView subTitle;
        private TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_product_list);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.sub_title);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}
