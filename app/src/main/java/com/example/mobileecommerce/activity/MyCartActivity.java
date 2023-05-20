package com.example.mobileecommerce.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    TextView title;
    TextView tv_subTotal, tv_Total, text_checkout;
    RecyclerView rc_view;
    CartItemAdapter cartItemAdapter;
    private List<Item> itemList;
    double total;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_cart);
        anhXa();
        itemList = new ArrayList<>();
        cartItemAdapter = new CartItemAdapter(itemList, this, new CartItemAdapter.iClickListener() {
            @Override
            public void plusQuantity(Item item) {
                clickplusQuantity(item);
            }
            @Override
            public void minusQuantity(Item item) {
                clickminusQuantity(item);
            }
            @Override
            public void deleteItem(Item item) {
                clickDeleteItem(item);
            }
        });
        loadData();
        rc_view.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(linearLayoutManager);
        rc_view.setAdapter(cartItemAdapter);

        text_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCartActivity.this, PaymentActivity.class);
                if(itemList.size()==0){
                    Toast.makeText(MyCartActivity.this, "Chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("total", total);
                startActivity(intent);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void clickplusQuantity(Item item){
        item.setQuantity(item.getQuantity()+1);
        ItemDatabase.getInstance(this).itemDao().updateItem(item);
        loadData();
    }
    private void clickminusQuantity(Item item){
        int quantity = item.getQuantity();
        if(quantity<=1){
            clickDeleteItem(item);
            return;
        }
        item.setQuantity(quantity-1);
        ItemDatabase.getInstance(this).itemDao().updateItem(item);
        loadData();
    }

    private void clickDeleteItem(Item item){
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete product")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ItemDatabase.getInstance(MyCartActivity.this).itemDao().delete(item);
                        Toast.makeText(MyCartActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    private void loadData(){
        //lấy danh sách item trong Room
        total=0;
        itemList= ItemDatabase.getInstance(this).itemDao().getAll();
        for(int i=0;i<itemList.size();i++){
            total += itemList.get(i).getPrice()*itemList.get(i).getQuantity();
        }
        tv_subTotal.setText('$'+String.valueOf(total));
        tv_Total.setText('$'+String.valueOf(total));
        cartItemAdapter.setData(itemList);
    }
    void anhXa(){
        rc_view = findViewById(R.id.rc_view);
        tv_subTotal = findViewById(R.id.tv_subTotal);
        tv_Total = findViewById(R.id.tv_Total);
        text_checkout = findViewById(R.id.text_checkout);
        iv_back = findViewById(R.id.iv_back);
        title = findViewById(R.id.title);
        title.setText("My Cart");
    }
}
