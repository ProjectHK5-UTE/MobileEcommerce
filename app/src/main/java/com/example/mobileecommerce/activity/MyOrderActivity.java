package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.MyOrderRecycleAdapter;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.model.MyOrderModelClass;
import com.example.mobileecommerce.model.dto.LineitemDTO;
import com.example.mobileecommerce.model.dto.RequestOrderDTO;
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
    private ArrayList<MyOrderModelClass> myOrderModelClasses;

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
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        username = SharedPreferences.getUsername();
        Log.e("Username trong myorder","là" + username);
        getOrder();
    }

    void getOrder(){
        orderAPI.getOrderByUsername(username).enqueue(new Callback<List<ResponseOrderDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                responseOrderDTOS = response.body();
                myOrderModelClasses = new ArrayList<>();
                for (int i = 0; i < responseOrderDTOS.size(); i++) {
                    for(int j=0;j<responseOrderDTOS.get(i).getLineitems().size(); j++){
                        LineitemDTO lineitemDTO = responseOrderDTOS.get(i).getLineitems().get(j);
                        myOrderModelClasses.add(new MyOrderModelClass(lineitemDTO.getOption().getImages().get(0).getPath()
                                , lineitemDTO.getOption().getPrice(),lineitemDTO.getQuantity(),
                                responseOrderDTOS.get(i).getOrderId()));

                    }
                }
                mAdapter2 = new MyOrderRecycleAdapter(MyOrderActivity.this, myOrderModelClasses);
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
