package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ProductDetailActivity;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.HomeViewModelClass;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.MyViewHolder> {
    private List<HomeViewModelClass> OfferList;

    private ProductGridModel productDetail;
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

    public HomeRecycleAdapter(Context context, List<HomeViewModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_product_list, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final HomeViewModelClass homeViewModelClass = this.OfferList.get(i);
        Glide.with(this.context).load(homeViewModelClass.getImage()).into(myViewHolder.image);
        myViewHolder.title.setText(homeViewModelClass.getTitle());
        if (homeViewModelClass.getPrice() == Math.floor(homeViewModelClass.getPrice())){
            myViewHolder.price.setText(""+ homeViewModelClass.getPrice().intValue() + " $");
        }else {
            myViewHolder.price.setText(""+ homeViewModelClass.getPrice() + " $");
        }
        myViewHolder.like.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.adapter.FavouriteRecycleAdapter.1
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
                ProductAPI productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);
                productAPI.getProduct(homeViewModelClass.getId()).enqueue(new Callback<ProductGridModel>() {
                    @Override
                    public void onResponse(Call<ProductGridModel> call, Response<ProductGridModel> response) {
                        if (response.isSuccessful()){
                            productDetail = response.body();
                            Intent intent = new Intent(myViewHolder.itemView.getContext(), ProductDetailActivity.class);
                            intent.putExtra("product", productDetail);
                            myViewHolder.itemView.getContext().startActivity(intent);
                        } else {
                            Log.e("callAPIgetPRODUCT","Gọi ko thành công" );
                        }

                    }

                    @Override
                    public void onFailure(Call<ProductGridModel> call, Throwable t) {
                        Log.e("getPRODUCT","getAPI product fail");
                    }
                });
            }
        });
    }
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
