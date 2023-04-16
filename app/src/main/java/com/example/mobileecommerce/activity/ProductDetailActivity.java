package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.ProductDetailPagerAdapter;
import com.example.mobileecommerce.adapter.ReviewsRecycleAdapter;
import com.example.mobileecommerce.model.ReviewModelClass;

import java.util.ArrayList;
import me.relex.circleindicator.CircleIndicator;

/* loaded from: classes.dex */
public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NUM_PAGES = 5;
    ImageView black_tick;
    Button btn_addtocart;
    private int currentPage;
    ImageView green_tick;
    ImageView iv_back;
    LinearLayout linear1;
    LinearLayout linear2;
    LinearLayout linear3;
    LinearLayout linear4;
    LinearLayout linear5;
    LinearLayout linear6;
    LinearLayout linear_black;
    LinearLayout linear_green;
    LinearLayout linear_red;
    private ReviewsRecycleAdapter mAdapter2;
    private ProductDetailPagerAdapter productDetailPagerAdapter;
    private RecyclerView recyclerview;
    ImageView red_tick;
    private ArrayList<ReviewModelClass> reviewModelClasses;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView title;
    private String[] title1 = {"Mark Henry", "Jemes Bond", "Steve Hong"};
    private ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_detail);
        this.recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        this.reviewModelClasses = new ArrayList<>();
        for (int i = 0; i < this.title1.length; i++) {
            this.reviewModelClasses.add(new ReviewModelClass(this.title1[i]));
        }
        this.mAdapter2 = new ReviewsRecycleAdapter(this, this.reviewModelClasses);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerview.setItemAnimator(new DefaultItemAnimator());
        this.recyclerview.setAdapter(this.mAdapter2);
        this.linear1 = (LinearLayout) findViewById(R.id.linear1);
        this.linear2 = (LinearLayout) findViewById(R.id.linear2);
        this.linear3 = (LinearLayout) findViewById(R.id.linear3);
        this.linear4 = (LinearLayout) findViewById(R.id.linear4);
        this.linear5 = (LinearLayout) findViewById(R.id.linear5);
        this.linear6 = (LinearLayout) findViewById(R.id.linear6);
        this.text1 = (TextView) findViewById(R.id.text1);
        this.text2 = (TextView) findViewById(R.id.text2);
        this.text3 = (TextView) findViewById(R.id.text3);
        this.text4 = (TextView) findViewById(R.id.text4);
        this.text5 = (TextView) findViewById(R.id.text5);
        this.text6 = (TextView) findViewById(R.id.text6);
        this.linear_red = (LinearLayout) findViewById(R.id.linear_red);
        this.linear_green = (LinearLayout) findViewById(R.id.linear_green);
        this.linear_black = (LinearLayout) findViewById(R.id.linear_black);
        this.red_tick = (ImageView) findViewById(R.id.red_tick);
        this.green_tick = (ImageView) findViewById(R.id.green_tick);
        this.black_tick = (ImageView) findViewById(R.id.black_tick);
        this.linear_red.setOnClickListener(this);
        this.linear_green.setOnClickListener(this);
        this.linear_black.setOnClickListener(this);
        this.linear1.setOnClickListener(this);
        this.linear2.setOnClickListener(this);
        this.linear3.setOnClickListener(this);
        this.linear4.setOnClickListener(this);
        this.linear5.setOnClickListener(this);
        this.linear6.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.title);
        this.title = textView;
        textView.setText("Product Detail");
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.activity.ProductDetailActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProductDetailActivity.this.finish();
            }
        });
        Button button = (Button) findViewById(R.id.btn_addtocart);
        this.btn_addtocart = button;
//        button.setOnClickListener(new View.OnClickListener() { // from class: com.ecommerce.template.activity.ProductDetailActivity.2
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                ProductDetailActivity.this.startActivity(new Intent(ProductDetailActivity.this, MyCartActivity.class));
//            }
//        });
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        ProductDetailPagerAdapter productDetailPagerAdapter = new ProductDetailPagerAdapter(getSupportFragmentManager());
        this.productDetailPagerAdapter = productDetailPagerAdapter;
        this.viewPager.setAdapter(productDetailPagerAdapter);
        this.productDetailPagerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        circleIndicator.setViewPager(this.viewPager);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ecommerce.template.activity.ProductDetailActivity.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                ProductDetailActivity productDetailActivity = ProductDetailActivity.this;
                productDetailActivity.currentPage = productDetailActivity.viewPager.getCurrentItem();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear1 /* 2131296486 */:
                this.linear1.setBackgroundResource(R.drawable.size_red_rect);
                this.linear2.setBackgroundResource(R.drawable.size_rect);
                this.linear3.setBackgroundResource(R.drawable.size_rect);
                this.linear4.setBackgroundResource(R.drawable.size_rect);
                this.linear5.setBackgroundResource(R.drawable.size_rect);
                this.linear6.setBackgroundResource(R.drawable.size_rect);
                this.text1.setTextColor(Color.parseColor("#dc143c"));
                this.text2.setTextColor(Color.parseColor("#8f909e"));
                this.text3.setTextColor(Color.parseColor("#8f909e"));
                this.text4.setTextColor(Color.parseColor("#8f909e"));
                this.text5.setTextColor(Color.parseColor("#8f909e"));
                this.text6.setTextColor(Color.parseColor("#8f909e"));
                return;
            case R.id.linear2 /* 2131296487 */:
                this.linear1.setBackgroundResource(R.drawable.size_rect);
                this.linear2.setBackgroundResource(R.drawable.size_red_rect);
                this.linear3.setBackgroundResource(R.drawable.size_rect);
                this.linear4.setBackgroundResource(R.drawable.size_rect);
                this.linear5.setBackgroundResource(R.drawable.size_rect);
                this.linear6.setBackgroundResource(R.drawable.size_rect);
                this.text1.setTextColor(Color.parseColor("#8f909e"));
                this.text2.setTextColor(Color.parseColor("#dc143c"));
                this.text3.setTextColor(Color.parseColor("#8f909e"));
                this.text4.setTextColor(Color.parseColor("#8f909e"));
                this.text5.setTextColor(Color.parseColor("#8f909e"));
                this.text6.setTextColor(Color.parseColor("#8f909e"));
                return;
            case R.id.linear3 /* 2131296488 */:
                this.linear1.setBackgroundResource(R.drawable.size_rect);
                this.linear2.setBackgroundResource(R.drawable.size_rect);
                this.linear3.setBackgroundResource(R.drawable.size_red_rect);
                this.linear4.setBackgroundResource(R.drawable.size_rect);
                this.linear5.setBackgroundResource(R.drawable.size_rect);
                this.linear6.setBackgroundResource(R.drawable.size_rect);
                this.text1.setTextColor(Color.parseColor("#8f909e"));
                this.text2.setTextColor(Color.parseColor("#8f909e"));
                this.text3.setTextColor(Color.parseColor("#dc143c"));
                this.text4.setTextColor(Color.parseColor("#8f909e"));
                this.text5.setTextColor(Color.parseColor("#8f909e"));
                this.text6.setTextColor(Color.parseColor("#8f909e"));
                return;
            case R.id.linear4 /* 2131296489 */:
                this.linear1.setBackgroundResource(R.drawable.size_rect);
                this.linear2.setBackgroundResource(R.drawable.size_rect);
                this.linear3.setBackgroundResource(R.drawable.size_rect);
                this.linear4.setBackgroundResource(R.drawable.size_red_rect);
                this.linear5.setBackgroundResource(R.drawable.size_rect);
                this.linear6.setBackgroundResource(R.drawable.size_rect);
                this.text1.setTextColor(Color.parseColor("#8f909e"));
                this.text2.setTextColor(Color.parseColor("#8f909e"));
                this.text3.setTextColor(Color.parseColor("#8f909e"));
                this.text4.setTextColor(Color.parseColor("#dc143c"));
                this.text5.setTextColor(Color.parseColor("#8f909e"));
                this.text6.setTextColor(Color.parseColor("#8f909e"));
                return;
            case R.id.linear5 /* 2131296490 */:
                this.linear1.setBackgroundResource(R.drawable.size_rect);
                this.linear2.setBackgroundResource(R.drawable.size_rect);
                this.linear3.setBackgroundResource(R.drawable.size_rect);
                this.linear4.setBackgroundResource(R.drawable.size_rect);
                this.linear5.setBackgroundResource(R.drawable.size_red_rect);
                this.linear6.setBackgroundResource(R.drawable.size_rect);
                this.text1.setTextColor(Color.parseColor("#8f909e"));
                this.text2.setTextColor(Color.parseColor("#8f909e"));
                this.text3.setTextColor(Color.parseColor("#8f909e"));
                this.text4.setTextColor(Color.parseColor("#8f909e"));
                this.text5.setTextColor(Color.parseColor("#dc143c"));
                this.text6.setTextColor(Color.parseColor("#8f909e"));
                return;
            case R.id.linear6 /* 2131296491 */:
                this.linear1.setBackgroundResource(R.drawable.size_rect);
                this.linear2.setBackgroundResource(R.drawable.size_rect);
                this.linear3.setBackgroundResource(R.drawable.size_rect);
                this.linear4.setBackgroundResource(R.drawable.size_rect);
                this.linear5.setBackgroundResource(R.drawable.size_rect);
                this.linear6.setBackgroundResource(R.drawable.size_red_rect);
                this.text1.setTextColor(Color.parseColor("#8f909e"));
                this.text2.setTextColor(Color.parseColor("#8f909e"));
                this.text3.setTextColor(Color.parseColor("#8f909e"));
                this.text4.setTextColor(Color.parseColor("#8f909e"));
                this.text5.setTextColor(Color.parseColor("#8f909e"));
                this.text6.setTextColor(Color.parseColor("#dc143c"));
                return;
            case R.id.linear_black /* 2131296492 */:
                this.red_tick.setVisibility(View.GONE);
                this.green_tick.setVisibility(View.GONE);
                this.black_tick.setVisibility(View.VISIBLE);
                return;
            default:
                return;
            case R.id.linear_green /* 2131296494 */:
                this.red_tick.setVisibility(View.GONE);
                this.green_tick.setVisibility(View.VISIBLE);
                this.black_tick.setVisibility(View.GONE);
                return;
            case R.id.linear_red /* 2131296495 */:
                this.red_tick.setVisibility(View.VISIBLE);
                this.green_tick.setVisibility(View.GONE);
                this.black_tick.setVisibility(View.GONE);
                return;
        }
    }
}
