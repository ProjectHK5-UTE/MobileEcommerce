package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductGridActivity;
import com.example.mobileecommerce.model.BrandsModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/* loaded from: classes.dex */
public class RecycleAdapterBrandsList extends RecyclerView.Adapter<RecycleAdapterBrandsList.MyViewHolder> {
    private List<BrandsModel> brandsList;
    private Context context;

    public RecycleAdapterBrandsList(List<BrandsModel> brandsList, Context context) {
        this.brandsList = brandsList;
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleAdapterBrandsList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories_list,
                parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterBrandsList.MyViewHolder holder, int position) {
        BrandsModel brand = brandsList.get(position);
        holder.title.setText(brand.getName());
        Glide.with(context)
                .load(brand.getLogo())
                .into(holder.images);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductGridActivity.class);
                intent.putExtra("idBrand", brand.getBrandId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(brandsList!= null){
            return brandsList.size();
        }
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView images;
        private TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);

        }
    }
}
