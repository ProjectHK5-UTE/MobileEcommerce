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
import com.example.mobileecommerce.activity.MyOrderActivity;
import com.example.mobileecommerce.activity.OrderDetailActivity;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.dto.OrderResponseDTO;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

/* loaded from: classes.dex */
public class MyOrderRecycleAdapter extends RecyclerView.Adapter<MyOrderRecycleAdapter.MyViewHolder> {
    private MyOrderActivity mActivity;
    private List<ResponseOrderDTO> OfferList;
    Context context;
    OrderAPI orderAPI;
    static android.content.SharedPreferences pres;
    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);
    String username = SharedPreferences.getUsername();
    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_no;
        TextView price;
        TextView order_date;
        TextView status;
        Button btnGet, btnCancel;
        public MyViewHolder(View view) {
            super(view);
            this.order_date = (TextView) view.findViewById(R.id.tv_order_date);
            this.price = (TextView) view.findViewById(R.id.tv_total_price);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.status = (TextView) view.findViewById(R.id.tv_status);
            this.btnGet = (Button) view.findViewById(R.id.btnGet);
            this.btnCancel = (Button) view.findViewById(R.id.btnCancel_);
        }
    }

    public MyOrderRecycleAdapter(Context context, List<ResponseOrderDTO> list) {
        this.OfferList = list;
        this.context = context;
        this.mActivity = (MyOrderActivity) getActivity(context);
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
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        ResponseOrderDTO myOrderModelClass = this.OfferList.get(i);

        myViewHolder.price.setText('$'+String.valueOf(myOrderModelClass.getTotalPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrderId()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        myViewHolder.order_date.setText(String.valueOf(dateFormat.format(myOrderModelClass.getOrder_date())));
        myViewHolder.status.setText(String.valueOf(myOrderModelClass.getStatus()));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myViewHolder.itemView.getContext(), OrderDetailActivity.class);
                intent.putExtra("orderId", (Integer) myOrderModelClass.getOrderId());
                intent.putExtra("status", myOrderModelClass.getStatus());
                myViewHolder.itemView.getContext().startActivity(intent);
            }
        });

        Log.e("myOrderModelClass.getStatus()", String.valueOf(myOrderModelClass.getStatus()) );
        if(myOrderModelClass.getStatus()==Status.PENDING){
            myViewHolder.btnGet.setVisibility(View.GONE);
            myViewHolder.btnGet.setEnabled(false);
            myViewHolder.status.setText("Đang chờ duyệt");
        }else if(myOrderModelClass.getStatus()==Status.SUCCESSFUL){
            myViewHolder.btnGet.setVisibility(View.GONE);
            myViewHolder.btnGet.setEnabled(false);
            myViewHolder.btnCancel.setVisibility(View.GONE);
            myViewHolder.btnCancel.setEnabled(false);
            myViewHolder.status.setText("Đã nhận hàng");
        }else if(myOrderModelClass.getStatus()==Status.TRANSIT){
            myViewHolder.status.setText("Đang giao");
            myViewHolder.btnCancel.setVisibility(View.GONE);
            myViewHolder.btnCancel.setEnabled(false);
        }else if(myOrderModelClass.getStatus()==Status.PICKING){
            myViewHolder.btnGet.setVisibility(View.GONE);
            myViewHolder.btnGet.setEnabled(false);
            myViewHolder.status.setText("Đang lấy hàng");
        }else{
            myViewHolder.btnGet.setVisibility(View.GONE);
            myViewHolder.btnGet.setEnabled(false);
            myViewHolder.btnCancel.setVisibility(View.GONE);
            myViewHolder.btnCancel.setEnabled(false);
            myViewHolder.status.setText("Đã hủy");
        }

        myViewHolder.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());
                builder.setMessage("Sản phẩm đã giao tới bạn, chắc chắn chứ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Xử lý khi chọn Yes
                                orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
                                orderAPI.updateStatus(myOrderModelClass.getOrderId(), Status.PICKING).enqueue(new Callback<OrderResponseDTO>() {
                                    @Override
                                    public void onResponse(Call<OrderResponseDTO> call, Response<OrderResponseDTO> response) {
                                        if(response.isSuccessful()){
                                            String message = "Đơn hàng đã hoàn tất !";
                                            Toast.makeText(myViewHolder.itemView.getContext(),message,Toast.LENGTH_SHORT).show();
                                            orderAPI.getOrderByUsername(username).enqueue(new Callback<List<ResponseOrderDTO>>() {
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
        myViewHolder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());
                builder.setMessage("Bạn muốn hủy Order này?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Xử lý sự kiện khi người dùng chọn Yes
                                orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
                                orderAPI.updateStatus(myOrderModelClass.getOrderId(), Status.CANCELLED).enqueue(new Callback<OrderResponseDTO>() {
                                    @Override
                                    public void onResponse(Call<OrderResponseDTO> call, Response<OrderResponseDTO> response) {
                                        if(response.isSuccessful()){
                                            String message = "Hủy đơn hàng thành công !";
                                            Toast.makeText(myViewHolder.itemView.getContext(),message,Toast.LENGTH_SHORT).show();
                                            orderAPI.getOrderByUsername(username).enqueue(new Callback<List<ResponseOrderDTO>>() {
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
