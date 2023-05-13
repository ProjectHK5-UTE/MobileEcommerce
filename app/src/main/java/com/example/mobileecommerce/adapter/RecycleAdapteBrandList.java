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

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductGridActivity;
import com.example.mobileecommerce.model.BrandsModel;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class RecycleAdapteBrandList extends RecyclerView.Adapter<RecycleAdapteBrandList.MyViewHolder> {
    private ArrayList<BrandsModel> brandListModelClassList;
    Context context;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        LinearLayout linear;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image_brand);
            this.title = (TextView) view.findViewById(R.id.title_brand);
        }
    }

    public RecycleAdapteBrandList(Context context, ArrayList<BrandsModel> list) {
        this.brandListModelClassList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_brand_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final BrandsModel brandsModelClass = this.brandListModelClassList.get(i);
        Glide.with(this.context).load(brandsModelClass.getLogo()).into(myViewHolder.image);
        myViewHolder.title.setText(brandsModelClass.getName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(myViewHolder.itemView.getContext(), ProductGridActivity.class);
                intent.putExtra("idBrand", brandsModelClass.getBrandId());
                myViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.brandListModelClassList.size();
    }
}
