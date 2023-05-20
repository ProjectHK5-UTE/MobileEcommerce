package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.MyOrderModelClass;

import java.util.List;

/* loaded from: classes.dex */
public class OrderDetailRecycleAdapter extends RecyclerView.Adapter<OrderDetailRecycleAdapter.MyViewHolder> {
    private List<MyOrderModelClass> OfferList;
    Context context;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView order_no;
        TextView price;
        TextView quantity;
        public MyViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.title);
            this.image = (ImageView) view.findViewById(R.id.im_Oimage);
            this.price = (TextView) view.findViewById(R.id.tv_Oprice);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.quantity = (TextView) view.findViewById(R.id.tv_Oqty);
        }
    }

    public OrderDetailRecycleAdapter(Context context, List<MyOrderModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_product_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MyOrderModelClass myOrderModelClass = this.OfferList.get(i);
        Glide.with(context)
                .load(myOrderModelClass.getImage())
                .into(myViewHolder.image);
        myViewHolder.quantity.setText(String.valueOf(myOrderModelClass.getQuantity()));
        myViewHolder.price.setText('$'+String.valueOf(myOrderModelClass.getPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrder_no()));
        myViewHolder.name.setText(String.valueOf(myOrderModelClass.getName()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
