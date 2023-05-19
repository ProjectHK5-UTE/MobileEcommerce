package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.OrderDetailActivity;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/* loaded from: classes.dex */
public class ManageOrderRecycleAdapter extends RecyclerView.Adapter<ManageOrderRecycleAdapter.MyViewHolder> {
    private List<ResponseOrderDTO> OfferList;
    Context context;
    CustomerAPI customerAPI;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_no;
        TextView price;
        TextView order_date;
        TextView address;
        TextView customer;
        public MyViewHolder(View view) {
            super(view);
            this.order_date = (TextView) view.findViewById(R.id.tv_order_date);
            this.price = (TextView) view.findViewById(R.id.tv_total_price);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.customer = (TextView) view.findViewById(R.id.tv_customer);
            this.address =(TextView) view.findViewById(R.id.tv_address);
        }
    }

    public ManageOrderRecycleAdapter(Context context, List<ResponseOrderDTO> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_manager, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        ResponseOrderDTO myOrderModelClass = this.OfferList.get(i);
        customerAPI = RetrofitClient.getRetrofit().create(CustomerAPI.class);
        customerAPI.getCustomerInfor(String.valueOf(myOrderModelClass.getCustomer().getUserName()))
                .enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful()){
                    try {
                        ResponseObject responseObject = response.body();
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(gson.toJson(responseObject.getData()), JsonObject.class);
                        String address = jsonObject.get("address").getAsString();
                        Log.e("Address là: ", address);
                        myViewHolder.address.setText(address);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
        myViewHolder.customer.setText(String.valueOf(myOrderModelClass.getCustomer().getUserName()));
        myViewHolder.price.setText(String.valueOf(myOrderModelClass.getTotalPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrderId()));
        myViewHolder.order_date.setText(String.valueOf(myOrderModelClass.getOrder_date()));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myViewHolder.itemView.getContext(), OrderDetailActivity.class);
                intent.putExtra("orderId", (Integer) myOrderModelClass.getOrderId());
                myViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
