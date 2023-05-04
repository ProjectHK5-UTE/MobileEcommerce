package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ItemHolder> {
    private List<Item> itemList;
    private  Context context;
    //private  iClickListener listener;
    public CartItemAdapter(List<Item> itemList, Context context, iClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        //this.listener = listener;
    }

    public CartItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setData(List<Item> list){
        this.itemList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,
                parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvProductName.setText(item.getName());
        Glide.with(context)
                .load(item.getImage())
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        if(itemList!= null){
            return itemList.size();
        }
        return 0;
    }
    public class ItemHolder extends RecyclerView.ViewHolder{
        private ImageView ivImage, ivPlus, ivMinus;
        private TextView tvProductName, tvQuantity, tvPrice, tvDelete;

        public ItemHolder(@NonNull View itemView){
            super(itemView);
            ivImage = itemView.findViewById(R.id.image);
            ivPlus = itemView.findViewById(R.id.iv_plus);
            ivMinus = itemView.findViewById(R.id.iv_minus);
            tvProductName = itemView.findViewById(R.id.tv_title);
            tvQuantity = itemView.findViewById(R.id.tv_number);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    public interface iClickListener{
        void plusQuantity(Item item);
        void minusQuantity(Item item);

        void deleteItem(Item item);
    }
}
