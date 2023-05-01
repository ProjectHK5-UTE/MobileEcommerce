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
import com.example.mobileecommerce.activity.ProductGridActivity;
import com.example.mobileecommerce.model.CategoriesListModellClass;

import java.util.List;

/* loaded from: classes.dex */
public class RecycleAdapteCategoriesList extends RecyclerView.Adapter<RecycleAdapteCategoriesList.MyViewHolder> {
    private List<CategoriesListModellClass> categoriesListModellClassList;
    Context context;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        LinearLayout linear;
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
        }
    }

    public RecycleAdapteCategoriesList(Context context, List<CategoriesListModellClass> list) {
        this.categoriesListModellClassList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categories_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        CategoriesListModellClass categoriesListModellClass = this.categoriesListModellClassList.get(i);
        myViewHolder.image.setImageResource(categoriesListModellClass.getImage().intValue());
        myViewHolder.title.setText(categoriesListModellClass.getTitle());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.adapter.RecycleAdapteCategoriesList.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecycleAdapteCategoriesList.this.context.startActivity(new Intent(RecycleAdapteCategoriesList.this.context, ProductGridActivity.class));
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.categoriesListModellClassList.size();
    }
}
