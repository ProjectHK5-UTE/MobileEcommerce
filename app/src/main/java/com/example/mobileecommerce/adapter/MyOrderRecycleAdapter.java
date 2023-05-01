package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

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
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.qty = (TextView) view.findViewById(R.id.qty);
            this.price = (TextView) view.findViewById(R.id.price);
            this.date = (TextView) view.findViewById(R.id.date);
            this.order_no = (TextView) view.findViewById(R.id.order_no);
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
        myViewHolder.image.setImageResource(myOrderModelClass.getImage().intValue());
        myViewHolder.title.setText(myOrderModelClass.getTitle());
        myViewHolder.qty.setText(myOrderModelClass.getQuantity());
        myViewHolder.price.setText(myOrderModelClass.getPrice());
        myViewHolder.date.setText(myOrderModelClass.getDate());
        myViewHolder.order_no.setText(myOrderModelClass.getOrder_no());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
