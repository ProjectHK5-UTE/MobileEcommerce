package com.example.mobileecommerce.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.ProductDetailPagerAdapter;
import com.example.mobileecommerce.adapter.RecycleAdapterOptionList;
import com.example.mobileecommerce.adapter.ReviewsRecycleAdapter;
import com.example.mobileecommerce.api.ReviewAPI;
import com.example.mobileecommerce.model.CustomerModel;
import com.example.mobileecommerce.model.OptionModel;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.ReviewModel;
import com.example.mobileecommerce.model.ReviewModelClass;
import com.example.mobileecommerce.model.cartRoomDatabase.ItemDatabase;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;
import com.example.mobileecommerce.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ProductDetailActivity extends AppCompatActivity {
    ImageView iv_back;
    TextView title,tvName, tvPrice, tvDescription;
    Button addToCart;
    ViewPager viewPager;
    ProductGridModel product;
    RecyclerView rc_view;
    RecycleAdapterOptionList recycleAdapterOptionList;
    private int oId;

    RecyclerView rcvReview;

    private ReviewsRecycleAdapter reviewsRecycleAdapter;

    ReviewAPI reviewAPI = RetrofitClient.getRetrofit().create(ReviewAPI.class);

    List<ReviewModel> listReview;

    Button btnAddReview;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_detail);
        anhXa();
        Intent intent = getIntent();
        product = (ProductGridModel) intent.getSerializableExtra("product");

        loadProductDetail();

        // Recycle Review Set Up
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvReview.setLayoutManager(linearLayoutManager1);
        rcvReview.setItemAnimator(new DefaultItemAnimator());
        AddReviews();
        recycleAdapterOptionList = new RecycleAdapterOptionList(this, product.getOptions(), new RecycleAdapterOptionList.ItemClickListener() {
            @Override
            public void onClick(int id) {
                oId= id;
                Toast.makeText(ProductDetailActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                rc_view.post(new Runnable() {
                    @Override
                    public void run() {
                        recycleAdapterOptionList.notifyDataSetChanged();
                    }
                });
            }
        });
        rc_view.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(linearLayoutManager);
        rc_view.setAdapter(recycleAdapterOptionList);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, MyCartActivity.class);
                //intent.putExtra("product", product);
                addItem(oId);
                startActivity(intent);
            }
        });

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReviewDialog();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void openReviewDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_review_product);
        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        Button btnNoThank = dialog.findViewById(R.id.btn_no_thank);
        SeekBar sbRate = dialog.findViewById(R.id.sb_rate_review);
        EditText edtContent = dialog.findViewById(R.id.edt_content_review);
        Button btnSendReview = dialog.findViewById(R.id.btn_send_review);

        btnNoThank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        btnSendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewModel reviewModel = new ReviewModel();
                reviewModel.setContent(edtContent.getText().toString());
                reviewModel.setRate(sbRate.getProgress());
                reviewModel.setProduct(new ProductGridModel(product.getProductId()));
                reviewModel.setCustomer(new CustomerModel("thangpham"));
                reviewAPI.insertReview(reviewModel).enqueue(new Callback<ReviewModel>() {
                    @Override
                    public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                        listReview.add(0, response.body());
                        reviewsRecycleAdapter.notifyItemInserted(0);
                        Log.d("OKE", "OKE");
                    }

                    @Override
                    public void onFailure(Call<ReviewModel> call, Throwable t) {

                    }
                });


                dialog.dismiss();

            }
        });

        dialog.show();

    }

    private void AddReviews() {
        reviewAPI.getReviewsByProductId(product.getProductId(), "thangpham").enqueue(new Callback<List<ReviewModel>>() {
            @Override
            public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                listReview = response.body();
                reviewsRecycleAdapter = new ReviewsRecycleAdapter(listReview, ProductDetailActivity.this);
                rcvReview.setAdapter(reviewsRecycleAdapter);
            }

            @Override
            public void onFailure(Call<List<ReviewModel>> call, Throwable t) {

            }
        });
    }

    private void addItem(int optionId){
        String pName = product.getProductName();
        double pPrice = product.getPrice();
        int pQuantity = 1;
        OptionModel optionModel = null;
        for(int i=0;i<product.getOptions().size(); i++){
            if(optionId==product.getOptions().get(i).getOptionId()){
                optionModel = product.getOptions().get(i);
            }
        }
        String image = optionModel.getImages().get(0).getPath();
        Item item = new Item(product.getProductId(), optionId,pName,image,1,
                optionModel.getRam(),optionModel.getRom(),pPrice);
        Log.e("dfsdfsdafasfsd",item.toString());
        //add vào room
        ItemDatabase.getInstance(this).itemDao().insertAll(item);
        Toast.makeText(this, "Thêm product thành công", Toast.LENGTH_SHORT).show();
    }
    void loadProductDetail(){
        tvName.setText(product.getProductName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
    }

    void anhXa(){
        title = findViewById(R.id.title);
        iv_back = findViewById(R.id.iv_back);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        addToCart = findViewById(R.id.btn_addtocart);
        viewPager = findViewById(R.id.viewPager);
        rc_view = findViewById(R.id.rc_view_option);
        rcvReview = findViewById(R.id.recyclerview_review);
        btnAddReview = findViewById(R.id.btn_add_review);
    }

}
