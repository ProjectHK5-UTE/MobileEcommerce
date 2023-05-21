package com.example.mobileecommerce.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.CustomerAPI;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.cartRoomDatabase.ItemDatabase;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;
import com.example.mobileecommerce.model.dto.RequestCustomerDTO;
import com.example.mobileecommerce.model.dto.LineitemDTO;
import com.example.mobileecommerce.model.dto.RequestOrderDTO;
import com.example.mobileecommerce.model.dto.OrderResponseDTO;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class PaymentActivity extends AppCompatActivity {
    ImageView iv_address;
    ImageView iv_back;
    TextView text_paynow, total;
    TextView text_address;
    RequestOrderDTO orderDTO;
    private List<Item> itemList;
    private List<LineitemDTO> lineitemDTO;
    private RequestCustomerDTO customerDTO;
    private List<ProductGridModel> product;
    OrderAPI orderAPI;
    ProductAPI productAPI;
    CustomerAPI customerAPI;
    double ptotal;
    ProgressDialog mProgressDialog;
    int i;
    private String username;
    private String address;
    static android.content.SharedPreferences pres;

    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_payment);
        anhXa();
        Intent intent = getIntent();
        ptotal = (double) intent.getSerializableExtra("total");
        username = SharedPreferences.getUsername();
        total.setText(String.valueOf(ptotal)+" $");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentActivity.this.finish();
            }
        });

        text_paynow.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if(address==null || address.equals("")){
                    Toast.makeText(PaymentActivity.this, "Vui lòng cập nhật địa chỉ ở Profile", Toast.LENGTH_SHORT).show();
                    return;
                }
                orderAPI = RetrofitClient.getRetrofit60TimeOut().create(OrderAPI.class);
                mProgressDialog = ProgressDialog.show(PaymentActivity.this, "Loading", "Please wait...", true);
                orderAPI.order(orderDTO).enqueue(new Callback<OrderResponseDTO>() {
                    @Override
                    public void onResponse(Call<OrderResponseDTO> call, Response<OrderResponseDTO> response) {
                        ItemDatabase.getInstance(PaymentActivity.this).itemDao().deleteAll();

                        PaymentActivity.this.startActivity(new Intent(PaymentActivity.this, SucessfullActivity.class));
                        mProgressDialog.dismiss();
                    }
                    @Override
                    public void onFailure(Call<OrderResponseDTO> call, Throwable t) {
                        Log.e("ERROR API ORDER", t.getMessage());
                        mProgressDialog.dismiss();
                    }
                });
            }
        });
        customerAPI = RetrofitClient.getRetrofit60TimeOut().create(CustomerAPI.class);
        customerAPI.getCustomerInfor(username)
                .enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        if(response.isSuccessful()){
                            try {
                                ResponseObject responseObject = response.body();
                                Gson gson = new Gson();
                                JsonObject jsonObject = gson.fromJson(gson.toJson(responseObject.getData()), JsonObject.class);
                                address = jsonObject.get("address").getAsString();
                                Log.e("Address là: ", address);
                                text_address.setText(address);
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
        get();
    }

    class GetProductTask extends AsyncTask<Integer, Void, Void> {
        private int i;
        public GetProductTask(int i) {
            this.i = i;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            Call<ProductGridModel> callSync = productAPI.getProduct(itemList.get(i).getProductId());
            try
            {
                Response<ProductGridModel> response = callSync.execute();
                ProductGridModel apiResponse = response.body();
                product.add(apiResponse);
                LineitemDTO lineitemDTO1 = new LineitemDTO(itemList.get(i).getQuantity(),
                        product.get(i).getOptions().get(itemList.get(i).getOptionId()));
                lineitemDTO.add(lineitemDTO1);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return null;
        }
    }
    void get() {
        itemList = ItemDatabase.getInstance(this).itemDao().getAll();
        productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);
        product = new ArrayList<ProductGridModel>();
        lineitemDTO = new ArrayList<LineitemDTO>();
        for (i = 0; i < itemList.size(); i++) {
            new GetProductTask(i).execute();
        }

        customerDTO = new RequestCustomerDTO(username, null, null, null, null);
        orderDTO = new RequestOrderDTO(ptotal, Status.PENDING, lineitemDTO, customerDTO);
    }

    void anhXa(){
        iv_back = findViewById(R.id.iv_back);
        text_paynow = findViewById(R.id.text_paynow);
        total = findViewById(R.id.ptotal);
        text_address = findViewById(R.id.tv_address_payment);
        iv_address = findViewById(R.id.img_edit_address);
        iv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, ProfileActivity.class));
            }
        });
    }
}
