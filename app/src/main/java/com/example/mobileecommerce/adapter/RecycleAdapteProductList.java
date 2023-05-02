package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;
import com.example.mobileecommerce.model.ProductListModelClass;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecycleAdapteProductList extends RecyclerView.Adapter<RecycleAdapteProductList.MyViewHolder> {
    Context context;
    private List<ProductListModelClass> moviesList;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView like;
        LinearLayout linear;
        TextView offer;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.like = (ImageView) view.findViewById(R.id.like);
            this.title = (TextView) view.findViewById(R.id.title);
            this.offer = (TextView) view.findViewById(R.id.offer);
        }
    }

    public RecycleAdapteProductList(Context context, ArrayList<ProductListModelClass> list) {
        this.moviesList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        ProductListModelClass productListModellClass = this.moviesList.get(i);
        myViewHolder.image.setImageResource(productListModellClass.getImage().intValue());
        myViewHolder.like.setImageResource(productListModellClass.getLike().intValue());
        myViewHolder.title.setText(productListModellClass.getTitle());
        myViewHolder.offer.setText("Rs 40000");
        myViewHolder.offer.setPaintFlags(myViewHolder.offer.getPaintFlags() | 16);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecycleAdapteProductList.this.context.startActivity(new Intent(RecycleAdapteProductList.this.context, ProductDetailActivity.class));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.moviesList.size();
    }
}
