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
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.ReviewModel;
import com.example.mobileecommerce.model.ReviewModelClass;

import java.util.List;

/* loaded from: classes.dex */
public class ReviewsRecycleAdapter extends RecyclerView.Adapter<ReviewsRecycleAdapter.MyViewHolder> {
    private List<ReviewModel> listReview;
    Context context;


    public ReviewsRecycleAdapter(List<ReviewModel> listReview, Context context) {
        this.listReview = listReview;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewsRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_list,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(listReview != null) {
            return listReview.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsRecycleAdapter.MyViewHolder holder, int position) {
        ReviewModel reviewModel = listReview.get(position);
        if(reviewModel == null)
            return;
        holder.nameReview.setText(reviewModel.getCustomer().getFullname());
        holder.dateReview.setText(reviewModel.getUpdateAt().toString());
        holder.contentReview.setText(reviewModel.getContent());
        // 5 phut
        if(reviewModel.getCustomer().getUserName().equals("thangpham") &&
                System.currentTimeMillis() - reviewModel.getUpdateAt().getTime() < 5 * 60 * 1000) {
            holder.btnEditReview.setVisibility(View.VISIBLE);
            holder.btnRemoveReview.setVisibility(View.VISIBLE);
        }
        else {
            holder.btnEditReview.setVisibility(View.GONE);
            holder.btnRemoveReview.setVisibility(View.GONE);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameReview;
        TextView dateReview;
        TextView contentReview;
        Button btnEditReview;
        Button btnRemoveReview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameReview = itemView.findViewById(R.id.txt_name_review);
            dateReview = itemView.findViewById(R.id.txt_date_review);
            contentReview = itemView.findViewById(R.id.txt_content_review);
            btnEditReview = itemView.findViewById(R.id.btn_edit_review);
            btnRemoveReview = itemView.findViewById(R.id.btn_remove_review);
        }
    }
}
