package com.example.mobileecommerce.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.ManageOrderRecycleAdapter;
import com.example.mobileecommerce.api.OrderAPI;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.model.dto.ResponseOrderDTO;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ManageOrderActivity extends AppCompatActivity {
    Spinner spinnerStatus;
    FrameLayout fl_ecart;
    ImageView iv_back;
    public ManageOrderRecycleAdapter mAdapter2;
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
        textView.setText("Orders Management");
        this.fl_ecart = (FrameLayout) findViewById(R.id.fl_ecart);
        this.fl_ecart.setVisibility(View.GONE);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManageOrderActivity.this.finish();
            }
        });

        spinnerStatus = findViewById(R.id.spinner_status);
        List<String> statusList = Arrays.asList("Đang chờ duyệt", "Đang chờ lấy hàng", "Đang vận chuyển", "Đã hoàn tất", "Đã hủy");
        List<Integer> drawableList = Arrays.asList(R.drawable.ic_pending,R.drawable.ic_pending,R.drawable.ic_transit,R.drawable.ic_successful,R.drawable.ic_cancelled);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < statusList.size(); i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("text", statusList.get(i));
            item.put("image", drawableList.get(i));
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.spinner_item,
                new String[]{"text", "image"}, new int[]{R.id.textView, R.id.imageView});
        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Lấy trạng thái được chọn và cập nhật danh sách đơn hàng tương ứng
                String status = (String) ((Map<String, Object>) adapterView.getItemAtPosition(position)).get("text");
                getOrder(Status.fromString(status));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                getOrder(Status.PENDING);
            }
        });

        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        username = SharedPreferences.getUsername();
        Log.e("Username trong myorder","là" + username);
    }
    void getOrder(Status status){
        orderAPI.getOrderByStatus(status).enqueue(new Callback<List<ResponseOrderDTO>>() {
            @Override
            public void onResponse(Call<List<ResponseOrderDTO>> call, Response<List<ResponseOrderDTO>> response) {
                if(response.isSuccessful()){
                    responseOrderDTOS = response.body();
                    mAdapter2 = new ManageOrderRecycleAdapter(ManageOrderActivity.this, responseOrderDTOS);
                    recyclerview.setLayoutManager(new LinearLayoutManager(ManageOrderActivity.this));
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter2);
                    loadData(responseOrderDTOS);
                } else{
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<ResponseOrderDTO>> call, Throwable t) {
            }
        });

    }
    public void loadData(List<ResponseOrderDTO> responseOrderDTOS){
        mAdapter2.setData(responseOrderDTOS);
    }
}
