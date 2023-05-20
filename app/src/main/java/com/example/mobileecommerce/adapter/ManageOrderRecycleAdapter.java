package com.example.mobileecommerce.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.activity.ManageOrderActivity;
import com.example.mobileecommerce.activity.OrderDetailActivity;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;
import com.example.mobileecommerce.model.dto.OrderResponseDTO;
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
    private ManageOrderActivity mActivity;
    private List<ResponseOrderDTO> OfferList;
    Context context;
    CustomerAPI customerAPI;
    OrderAPI orderAPI;
    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_no;
        TextView price;
        TextView order_date;
        TextView address;
        TextView customer;
        Button btnSubmit, btnReject;
        public MyViewHolder(View view) {
            super(view);
            this.order_date = (TextView) view.findViewById(R.id.tv_order_date);
            this.price = (TextView) view.findViewById(R.id.tv_total_price);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.customer = (TextView) view.findViewById(R.id.tv_customer);
            this.address =(TextView) view.findViewById(R.id.tv_address);
            this.btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
            this.btnReject = (Button) view.findViewById(R.id.btnCancel_);
        }
    }

    public ManageOrderRecycleAdapter(Context context, List<ResponseOrderDTO> list) {
        this.OfferList = list;
        this.context = context;
        this.mActivity = (ManageOrderActivity) getActivity(context);
    }
    public static Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
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
        myViewHolder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
                orderAPI.updateStatus(myOrderModelClass.getOrderId(), Status.PICKING).enqueue(new Callback<OrderResponseDTO>() {
                    @Override
                    public void onResponse(Call<OrderResponseDTO> call, Response<OrderResponseDTO> response) {
                        if(response.isSuccessful()){
                            String message = "Xác nhận Order thành công !";
                            Toast.makeText(myViewHolder.itemView.getContext(),message,Toast.LENGTH_SHORT).show();
                            orderAPI.getOrderByStatus(Status.PENDING).enqueue(new Callback<List<ResponseOrderDTO>>() {
                                @Override
                                public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                                    mActivity.loadData(response.body()); // Reload data in adapter
                                    mActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mActivity.mAdapter2.notifyDataSetChanged(); // Reload RecyclerView
                                        }
                                    });
                                }
                                @Override
                                public void onFailure(Call<List<ResponseOrderDTO>> call, Throwable t) {
                                }
                            });
                        }
                        else{
                            int statusCode = response.code();
                        }
                    }
                    @Override
                    public void onFailure(Call<OrderResponseDTO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        myViewHolder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());
                builder.setMessage("Bạn có chắc chắn muốn hủy Order này?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Xử lý sự kiện khi người dùng chọn Yes
                                orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
                                orderAPI.updateStatus(myOrderModelClass.getOrderId(), Status.CANCELLED).enqueue(new Callback<OrderResponseDTO>() {
                                    @Override
                                    public void onResponse(Call<OrderResponseDTO> call, Response<OrderResponseDTO> response) {
                                        if(response.isSuccessful()){
                                            String message = "Bạn đã từ chối Order này !";
                                            Toast.makeText(myViewHolder.itemView.getContext(),message,Toast.LENGTH_SHORT).show();
                                            orderAPI.getOrderByStatus(Status.PENDING).enqueue(new Callback<List<ResponseOrderDTO>>() {
                                                @Override
                                                public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                                                    mActivity.loadData(response.body()); // Reload data in adapter
                                                    mActivity.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mActivity.mAdapter2.notifyDataSetChanged(); // Reload RecyclerView
                                                        }
                                                    });
                                                }
                                                @Override
                                                public void onFailure(Call<List<ResponseOrderDTO>> call, Throwable t) {
                                                }
                                            });
                                        }
                                        else{
                                            int statusCode = response.code();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<OrderResponseDTO> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Xử lý sự kiện khi người dùng chọn No
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    public void setData(List<ResponseOrderDTO> list){
        this.OfferList = list;
        notifyDataSetChanged();
    }
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }
}
