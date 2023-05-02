package com.example.mobileecommerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.BrandsListActivity;
import com.example.mobileecommerce.activity.HomePageActivity;
import com.example.mobileecommerce.activity.MyCartActivity;
import com.example.mobileecommerce.activity.MyOrderActivity;
import com.example.mobileecommerce.activity.ProductListActivity;
import com.example.mobileecommerce.activity.ProfileActivity;
import com.example.mobileecommerce.model.EShoppingModelClass;
import com.squareup.picasso.Picasso;

import java.util.List;

/* loaded from: classes.dex */
public class NavigationRecycleAdapter extends RecyclerView.Adapter<NavigationRecycleAdapter.MyViewHolder> {
    private List<EShoppingModelClass> OfferList;
    Context context;
    int myPos = 0;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);
            //NavigationRecycleAdapter.this = r1;
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
        }
    }

    public NavigationRecycleAdapter(Context context, List<EShoppingModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_navigation_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        myViewHolder.title.setText(this.OfferList.get(i).getTitle());
        Picasso.get().load(this.OfferList.get(i).getImage()).into(myViewHolder.image);
        if (this.myPos == i) {
            myViewHolder.title.setTextColor(Color.parseColor("#38393f"));
        } else {
            myViewHolder.title.setTextColor(Color.parseColor("#acacac"));
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationRecycleAdapter.this.myPos = i;
                int i2 = i;
                if (i2 == 0) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, HomePageActivity.class));
                } else if (1 == i2) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, MyCartActivity.class));
                } else if (2 == i2) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, MyOrderActivity.class));
                } else if (3 == i2) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, BrandsListActivity.class));
                } else if (4 == i2) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, ProductListActivity.class));
                } else if (5 == i2) {
                    NavigationRecycleAdapter.this.context.startActivity(new Intent(NavigationRecycleAdapter.this.context, ProfileActivity.class));
                }
                NavigationRecycleAdapter.this.notifyDataSetChanged();
            }
        });
    }
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
