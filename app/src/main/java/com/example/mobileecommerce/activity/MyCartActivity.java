package com.example.mobileecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.CartItemAdapter;
import com.example.mobileecommerce.model.cartRoomDatabase.ItemDatabase;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MyCartActivity extends AppCompatActivity {

    ImageView iv_back;
    RecyclerView rc_view;

    CartItemAdapter cartItemAdapter;

    private List<Item> itemList;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_cart);
        anhXa();
        itemList = new ArrayList<>();
        cartItemAdapter = new CartItemAdapter(itemList,this );
        loadData();
        rc_view.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(linearLayoutManager);
        rc_view.setAdapter(cartItemAdapter);
    }
    private void loadData(){
        //lấy danh sách item trong Room
        itemList= ItemDatabase.getInstance(this).itemDao().getAll();
        cartItemAdapter.setData(itemList);

    }
    void anhXa(){
        rc_view = findViewById(R.id.rc_view);
    }
}
