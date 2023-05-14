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
public class MyOrderRecycleAdapter extends RecyclerView.Adapter<MyOrderRecycleAdapter.MyViewHolder> {
    private List<MyOrderModelClass> OfferList;
    Context context;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView image;
        TextView order_no;
        TextView price;
        TextView qty;
        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.im_Oimage);
            this.qty = (TextView) view.findViewById(R.id.tv_Oqty);
            this.price = (TextView) view.findViewById(R.id.tv_Oprice);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
        }
    }

    public MyOrderRecycleAdapter(Context context, List<MyOrderModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MyOrderModelClass myOrderModelClass = this.OfferList.get(i);
        Glide.with(context)
                .load(myOrderModelClass.getImage())
                .into(myViewHolder.image);
        myViewHolder.qty.setText(String.valueOf(myOrderModelClass.getQuantity()));
        myViewHolder.price.setText(String.valueOf(myOrderModelClass.getPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrder_no()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
