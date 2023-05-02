package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;
import com.example.mobileecommerce.model.HomeViewModelClass;

import java.util.List;

/* loaded from: classes.dex */
public class SmartPhoneRecycleAdapter extends RecyclerView.Adapter<SmartPhoneRecycleAdapter.MyViewHolder> {
    private List<HomeViewModelClass> OfferList;
    Context context;
    boolean showingfirst = true;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView like;
        TextView price;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.like = (ImageView) view.findViewById(R.id.like);
            this.title = (TextView) view.findViewById(R.id.title);
            this.price = (TextView) view.findViewById(R.id.price);
        }
    }

    public SmartPhoneRecycleAdapter(Context context, List<HomeViewModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_smartphone_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final HomeViewModelClass homeViewModelClass = this.OfferList.get(i);
        //myViewHolder.image.setImageResource(homeViewModelClass.getImage().intValue());
        myViewHolder.title.setText(homeViewModelClass.getTitle());
        //myViewHolder.price.setText(homeViewModelClass.getPrice());
        myViewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (homeViewModelClass.isSelected()) {
                    homeViewModelClass.setSelected(false);
                    myViewHolder.like.setImageResource(R.drawable.ic_dark_like);
                    return;
                }
                homeViewModelClass.setSelected(true);
                myViewHolder.like.setImageResource(R.drawable.ic_heart_light);
            }
        });
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SmartPhoneRecycleAdapter.this.context.startActivity(new Intent(SmartPhoneRecycleAdapter.this.context, ProductDetailActivity.class));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
