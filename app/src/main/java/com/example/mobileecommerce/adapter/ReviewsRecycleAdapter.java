package com.example.mobileecommerce.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Rating;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.api.ReviewAPI;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.ReviewModel;
import com.example.mobileecommerce.model.ReviewModelClass;
import com.example.mobileecommerce.model.dto.ResponseObject;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class ReviewsRecycleAdapter extends RecyclerView.Adapter<ReviewsRecycleAdapter.MyViewHolder> {
    private List<ReviewModel> listReview;
    Context context;

    ReviewAPI reviewAPI = RetrofitClient.getRetrofit().create(ReviewAPI.class);

    private Dialog dialog;

    private String username;
    static android.content.SharedPreferences pres;

    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);


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
    public void onBindViewHolder(@NonNull ReviewsRecycleAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ReviewModel reviewModel = listReview.get(position);
        if(reviewModel == null)
            return;
        holder.nameReview.setText(reviewModel.getCustomer().getUserName());
        holder.dateReview.setText(reviewModel.getUpdateAt().toString());
        holder.contentReview.setText(reviewModel.getContent());
        holder.ratingBar.setRating(reviewModel.getRate());

        username = SharedPreferences.getUsername();
        Log.e("USERNAME trong Review", "Là" + username);
        // 5 phut
        if(reviewModel.getCustomer().getUserName().equals(username) &&
                System.currentTimeMillis() - reviewModel.getUpdateAt().getTime() < 5 * 60 * 1000) {
            holder.btnEditReview.setVisibility(View.VISIBLE);
            holder.btnRemoveReview.setVisibility(View.VISIBLE);

            holder.btnRemoveReview.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    reviewAPI.deleteReview(reviewModel.getReviewId()).enqueue(new Callback<ResponseObject>() {
                        @Override
                        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                            if(response.isSuccessful()) {
                                listReview.remove(position);
                                notifyItemRemoved(position);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseObject> call, Throwable t) {

                        }
                    });
                }
            });

            holder.btnEditReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                    dialog.show();
                    Button btnNoThank = dialog.findViewById(R.id.btn_no_thank);
                    SeekBar sbRate = dialog.findViewById(R.id.sb_rate_review);
                    EditText edtContent = dialog.findViewById(R.id.edt_content_review);
                    Button btnSendReview = dialog.findViewById(R.id.btn_send_review);

                    edtContent.setText(reviewModel.getContent());
                    sbRate.setProgress(reviewModel.getRate() - 1);
                    btnSendReview.setText("Update");
                    btnNoThank.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    btnSendReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reviewAPI.updateReview(reviewModel.getReviewId(), sbRate.getProgress() + 1, edtContent.getText().toString()).enqueue(new Callback<ReviewModel>() {
                                @Override
                                public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                                    if(response.isSuccessful()) {
                                        ReviewModel newReview = listReview.get(position);
                                        newReview.setContent(edtContent.getText().toString());

                                        notifyItemChanged(position);
                                        dialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ReviewModel> call, Throwable t) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                }
            });

        }
        else {
            holder.btnEditReview.setVisibility(View.GONE);
            holder.btnRemoveReview.setVisibility(View.GONE);
        }
    }

    private void showDialog() {
        dialog = new Dialog(context);
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
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameReview;
        TextView dateReview;
        TextView contentReview;
        Button btnEditReview;
        Button btnRemoveReview;
        RatingBar ratingBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameReview = itemView.findViewById(R.id.txt_name_review);
            dateReview = itemView.findViewById(R.id.txt_date_review);
            contentReview = itemView.findViewById(R.id.txt_content_review);
            btnEditReview = itemView.findViewById(R.id.btn_edit_review);
            btnRemoveReview = itemView.findViewById(R.id.btn_remove_review);
            ratingBar = itemView.findViewById(R.id.ratingbar);
        }
    }
}
