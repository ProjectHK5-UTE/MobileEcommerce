package com.example.mobileecommerce.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.MyOrderRecycleAdapter;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.model.MyOrderModelClass;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.cartRoomDatabase.ItemDatabase;
import com.example.mobileecommerce.model.dto.LineitemDTO;
import com.example.mobileecommerce.model.dto.RequestOrderDTO;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class MyOrderActivity extends AppCompatActivity {
    FrameLayout fl_ecart;
    ImageView iv_back;
    private MyOrderRecycleAdapter mAdapter2;
    private List<MyOrderModelClass> myOrderModelClasses;
    private List<ResponseOrderDTO> responseOrderDTOS;
    private RecyclerView recyclerview;
    TextView title;
    OrderAPI orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
    private String username;
    static android.content.SharedPreferences pres;
    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_order);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("My Order");
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        username = SharedPreferences.getUsername();
        Log.e("Username trong myorder","là" + username);
        myOrderModelClasses = new ArrayList<>();
        mAdapter2 = new MyOrderRecycleAdapter(myOrderModelClasses, MyOrderActivity.this,
    new MyOrderRecycleAdapter.iClickListener() {
        @Override
        public void save(int id, int orderId) {
            new AlertDialog.Builder(MyOrderActivity.this)
                    .setTitle("Sản phẩm đã giao tới bạn")
                    .setMessage("Bạn chắc chứ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            orderAPI.updateStatus(orderId, Status.SUCCESSFUL).enqueue(new Callback<ResponseObject>() {
                                @Override
                                public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                    myOrderModelClasses.get(id).setStatus(Status.SUCCESSFUL);
                                    mAdapter2.setData(myOrderModelClasses);
                                }

                                @Override
                                public void onFailure(Call<ResponseObject> call, Throwable t) {

                                }
                            });
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
                    @Override
                    public void cancel(int id, int orderId) {
                        new AlertDialog.Builder(MyOrderActivity.this)
                                .setTitle("Bạn muốn hủy đơn hàng này?")
                                .setMessage("Bạn chắc chứ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        orderAPI.updateStatus(orderId, Status.CANCELLED).enqueue(new Callback<ResponseObject>() {
                                            @Override
                                            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                                myOrderModelClasses.get(id).setStatus(Status.CANCELLED);
                                                mAdapter2.setData(myOrderModelClasses);
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseObject> call, Throwable t) {

                                            }
                                        });
                                        Toast.makeText(MyOrderActivity.this, "Đã hủy thành công", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();

                    }
                });

        getOrder();
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrderActivity.this.finish();
            }
        });
        this.fl_ecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyOrderActivity.this.startActivity(new Intent(MyOrderActivity.this, MyCartActivity.class));
            }
        });

    }



    void getOrder(){
        orderAPI.getOrderByUsername(username).enqueue(new Callback<List<ResponseOrderDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                responseOrderDTOS = response.body();
                for (int i = 0; i < responseOrderDTOS.size(); i++) {
                    for(int j=0;j<responseOrderDTOS.get(i).getLineitems().size(); j++){
                        LineitemDTO lineitemDTO = responseOrderDTOS.get(i).getLineitems().get(j);
                        myOrderModelClasses.add(new MyOrderModelClass(lineitemDTO.getOption().getImages().get(0).getPath()
                                , lineitemDTO.getOption().getPrice(),responseOrderDTOS.get(i).getStatus(),lineitemDTO.getQuantity(),
                                responseOrderDTOS.get(i).getOrderId()));

                    }
                }
                recyclerview.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this));
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(mAdapter2);
            }
            @Override
            public void onFailure(Call<List<ResponseOrderDTO>> call, Throwable t) {
            }
        });
    }
}
