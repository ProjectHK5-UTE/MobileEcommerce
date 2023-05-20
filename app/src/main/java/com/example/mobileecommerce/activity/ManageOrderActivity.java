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
import com.example.mobileecommerce.adapter.ManageOrderRecycleAdapter;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ManageOrderActivity extends AppCompatActivity {
    FrameLayout fl_ecart;
    ImageView iv_back;
    private ManageOrderRecycleAdapter mAdapter2;
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
        setContentView(R.layout.activity_order_manager);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("All Waiting Orders ");
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.fl_ecart.setVisibility(View.GONE);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageOrderActivity.this.finish();
            }
        });
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        username = SharedPreferences.getUsername();
        Log.e("Username trong myorder","là" + username);
        getOrder();
    }

    void getOrder(){
        orderAPI.getOrderByStatus("PENDING").enqueue(new Callback<List<ResponseOrderDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                if(response.isSuccessful()){
                    responseOrderDTOS = response.body();
                    mAdapter2 = new ManageOrderRecycleAdapter(ManageOrderActivity.this, responseOrderDTOS);
                    recyclerview.setLayoutManager(new LinearLayoutManager(ManageOrderActivity.this));
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                } else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<ResponseOrderDTO>> call, Throwable t) {
            }
        });
    }
}
