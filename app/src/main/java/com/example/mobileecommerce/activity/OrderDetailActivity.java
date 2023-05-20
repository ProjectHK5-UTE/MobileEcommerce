package com.example.mobileecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.ManageOrderRecycleAdapter;
import com.example.mobileecommerce.adapter.OrderDetailRecycleAdapter;
import com.example.mobileecommerce.api.OptionAPI;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.MyOrderModelClass;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.dto.LineitemDTO;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
    TextView title;
    FrameLayout fl_ecart;
    ImageView iv_back;
    RecyclerView recyclerView;
    OrderAPI orderAPI;
    Integer orderId;

    OptionAPI optionAPI;
    Integer productId;
    ProductAPI productAPI;
    String productName="";
    private ArrayList<MyOrderModelClass> myOrderModelClasses;
    ResponseOrderDTO responseOrderDTOS;
    OrderDetailRecycleAdapter mAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order_detail);

        title = (TextView) findViewById(R.id.title);
        title.setText("Order Details");
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.fl_ecart.setVisibility(View.GONE);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailActivity.this.finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        orderId = (Integer) intent.getSerializableExtra("orderId");
        getOrderDetail(orderId);
    }

    void getOrderDetail(Integer orderId) {
        myOrderModelClasses = new ArrayList<>();
        orderAPI = RetrofitClient.getRetrofit().create(OrderAPI.class);
        orderAPI.getOrderByID(orderId).enqueue(new Callback<ResponseOrderDTO>() {
            @Override
            public void onResponse(Call<ResponseOrderDTO> call, Response<ResponseOrderDTO> response) {
                ResponseOrderDTO responseOrderDTOS = response.body();
                myOrderModelClasses = new ArrayList<>();
                for(int i = 0; i < responseOrderDTOS.getLineitems().size(); i++) {
                    LineitemDTO lineitemDTO = responseOrderDTOS.getLineitems().get(i);
                    int optionId = lineitemDTO.getOption().getOptionId();
                    Log.e("optionId là: ", String.valueOf(optionId));
                    optionAPI = RetrofitClient.getRetrofit().create(OptionAPI.class);
                    optionAPI.findProductIdByOptionId(optionId).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            int productId = response.body();
                            Log.e("productId là: ", String.valueOf(productId));
                            productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);
                            productAPI.getProduct(productId).enqueue(new Callback<ProductGridModel>() {
                                @Override
                                public void onResponse(Call<ProductGridModel> call, Response<ProductGridModel> response) {
                                    ProductGridModel productGridModel = response.body();
                                    String productName = productGridModel.getProductName();
                                    Log.e("productName là: ", productName);
                                    MyOrderModelClass myOrderModelClass = new MyOrderModelClass(productName,
                                            lineitemDTO.getOption().getImages().get(0).getPath()
                                            , lineitemDTO.getOption().getPrice(),lineitemDTO.getQuantity(),
                                            responseOrderDTOS.getOrderId(), responseOrderDTOS.getStatus(),
                                            productGridModel.getProductId());
                                    myOrderModelClasses.add(myOrderModelClass);
                                    // Kiểm tra xem đã thêm hết sản phẩm vào danh sách chưa
                                    if (myOrderModelClasses.size() == responseOrderDTOS.getLineitems().size()) {
                                        // Nếu đã thêm hết, hiển thị danh sách sản phẩm
                                        showOrderDetails(myOrderModelClasses);
                                    }
                                }
                                @Override
                                public void onFailure(Call<ProductGridModel> call, Throwable t) {
                                    // Xử lý lỗi
                                }
                            });
                        }
                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            // Xử lý lỗi
                        }
                    });
                }
                mAdapter2 = new OrderDetailRecycleAdapter(OrderDetailActivity.this, myOrderModelClasses);
                recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter2);
            }
            @Override
            public void onFailure(Call<ResponseOrderDTO> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }
    private void showOrderDetails(ArrayList<MyOrderModelClass> myOrderModelClasses) {
        mAdapter2 = new OrderDetailRecycleAdapter(OrderDetailActivity.this, myOrderModelClasses);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter2);
    }
}