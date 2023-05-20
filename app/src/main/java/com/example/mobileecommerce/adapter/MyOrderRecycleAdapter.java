package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.MyOrderActivity;
import com.example.mobileecommerce.model.MyOrderModelClass;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;

import java.util.List;

/* loaded from: classes.dex */
public class MyOrderRecycleAdapter extends RecyclerView.Adapter<MyOrderRecycleAdapter.MyViewHolder> {
    private List<MyOrderModelClass> OfferList;
    Context context;
    private MyOrderRecycleAdapter.iClickListener listener;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView image;
        TextView order_no;
        TextView status;
        TextView price;
        TextView order_date;
        Button btnSave_, btnCancel_;
        public MyViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.im_Oimage);
            this.order_date = (TextView) view.findViewById(R.id.tv_order_date);
            this.price = (TextView) view.findViewById(R.id.tv_total_price);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.status = (TextView) view.findViewById(R.id.tv_status);
            this.btnSave_ = (Button) view.findViewById(R.id.btnSave_);
            this.btnCancel_ = (Button) view.findViewById(R.id.btnCancel_);
        }
    }

    public void setData(List<MyOrderModelClass> list){
        this.OfferList = list;
        notifyDataSetChanged();
    }

    public MyOrderRecycleAdapter(Context context, List<MyOrderModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    public MyOrderRecycleAdapter(List<MyOrderModelClass> offerList, Context context, MyOrderRecycleAdapter.iClickListener listener) {
        OfferList = offerList;
        this.context = context;
        this.listener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MyOrderModelClass myOrderModelClass = this.OfferList.get(i);
        myViewHolder.order_date.setText(String.valueOf(myOrderModelClass.getQuantity()));
        myViewHolder.price.setText(String.valueOf(myOrderModelClass.getPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrder_no()));
        Log.e("myOrderModelClass.getStatus()", String.valueOf(myOrderModelClass.getStatus()) );
        if(myOrderModelClass.getStatus()==Status.PENDING){
            myViewHolder.btnSave_.setVisibility(View.GONE);
            myViewHolder.status.setText("Đang chờ duyệt");
        }
        else if(myOrderModelClass.getStatus()==Status.SUCCESSFUL){
            myViewHolder.btnSave_.setVisibility(View.GONE);
            myViewHolder.btnCancel_.setVisibility(View.GONE);
            myViewHolder.status.setText("Đã nhận hàng");
        }else if(myOrderModelClass.getStatus()==Status.TRANSIT){
            myViewHolder.status.setText("Đang giao");
        }else if(myOrderModelClass.getStatus()==Status.PICKING){
            myViewHolder.btnSave_.setVisibility(View.GONE);
            myViewHolder.status.setText("Đang lấy hàng");
        }else{
            myViewHolder.btnSave_.setVisibility(View.GONE);
            myViewHolder.btnCancel_.setVisibility(View.GONE);
            myViewHolder.status.setText("Đã hủy");
        }


        myViewHolder.btnSave_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.save(i,myOrderModelClass.getOrder_no());
            }
        });

        myViewHolder.btnCancel_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel(i,myOrderModelClass.getOrder_no());
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }

    public interface iClickListener{
        void save(int id, int orderId);
        void cancel(int id,int orderId);
    }
}
