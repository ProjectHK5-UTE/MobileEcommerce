package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.FavouriteModelClass;

import java.util.List;

/* loaded from: classes.dex */
public class FavouriteRecycleAdapter extends RecyclerView.Adapter<FavouriteRecycleAdapter.MyViewHolder> {
    private List<FavouriteModelClass> OfferList;
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

    public FavouriteRecycleAdapter(Context context, List<FavouriteModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favourite_list, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final FavouriteModelClass favouriteModelClass = this.OfferList.get(i);
        myViewHolder.image.setImageResource(favouriteModelClass.getImage().intValue());
        myViewHolder.title.setText(favouriteModelClass.getTitle());
        myViewHolder.price.setText(favouriteModelClass.getPrice());
        myViewHolder.like.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.adapter.FavouriteRecycleAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (favouriteModelClass.isSelected()) {
                    favouriteModelClass.setSelected(false);
                    myViewHolder.like.setImageResource(R.drawable.ic_dark_like);
                    return;
                }
                favouriteModelClass.setSelected(true);
                myViewHolder.like.setImageResource(R.drawable.ic_heart_light);
            }
        });
//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.adapter.FavouriteRecycleAdapter.2
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                FavouriteRecycleAdapter.this.context.startActivity(new Intent(FavouriteRecycleAdapter.this.context, ProductDetailActivity.class));
//            }
//        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
