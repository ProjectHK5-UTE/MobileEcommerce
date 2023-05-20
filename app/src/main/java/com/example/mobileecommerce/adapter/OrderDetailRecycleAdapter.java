package com.example.mobileecommerce.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.api.ReviewAPI;
import com.example.mobileecommerce.model.CustomerModel;
import com.example.mobileecommerce.model.MyOrderModelClass;
import com.example.mobileecommerce.model.ProductGridModel;
import com.example.mobileecommerce.model.ReviewModel;
import com.example.mobileecommerce.model.Status;
import com.example.mobileecommerce.retrofit.RetrofitClient;
import com.example.mobileecommerce.sharedpreferences.SharedPreferencesManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes.dex */
public class OrderDetailRecycleAdapter extends RecyclerView.Adapter<OrderDetailRecycleAdapter.MyViewHolder> {
    private List<MyOrderModelClass> OfferList;
    Context context;
    static android.content.SharedPreferences pres;
    private String username;
    SharedPreferencesManager SharedPreferences = SharedPreferencesManager.getInstance(pres);

    ReviewAPI reviewAPI = RetrofitClient.getRetrofit().create(ReviewAPI.class);

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        TextView order_no;
        TextView price;
        TextView quantity;
        Button btnReview;
        public MyViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.title);
            this.image = (ImageView) view.findViewById(R.id.im_Oimage);
            this.price = (TextView) view.findViewById(R.id.tv_Oprice);
            this.order_no = (TextView) view.findViewById(R.id.tv_order_no);
            this.quantity = (TextView) view.findViewById(R.id.tv_Oqty);
            this.btnReview = (Button) view.findViewById(R.id.btnReviewProduct);
        }
    }

    public OrderDetailRecycleAdapter(Context context, List<MyOrderModelClass> list) {
        this.OfferList = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_order_product_list, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MyOrderModelClass myOrderModelClass = this.OfferList.get(i);
        Glide.with(context)
                .load(myOrderModelClass.getImage())
                .into(myViewHolder.image);
        myViewHolder.quantity.setText(String.valueOf(myOrderModelClass.getQuantity()));
        myViewHolder.price.setText('$'+String.valueOf(myOrderModelClass.getPrice()));
        myViewHolder.order_no.setText(String.valueOf(myOrderModelClass.getOrder_no()));
        myViewHolder.name.setText(String.valueOf(myOrderModelClass.getName()));
        username = SharedPreferences.getUsername();

        if(myOrderModelClass.getStatus() == Status.SUCCESSFUL) {
            myViewHolder.btnReview.setVisibility(View.VISIBLE);
            myViewHolder.btnReview.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View view) {
                    final Dialog dialog = new Dialog(context);
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
                            dialog.dismiss();
                        }
                    });
                    btnSendReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ReviewModel reviewModel = new ReviewModel();
                            reviewModel.setContent(edtContent.getText().toString());
                            reviewModel.setRate(sbRate.getProgress() + 1);
                            reviewModel.setProduct(new ProductGridModel(myOrderModelClass.getProductId()));
                            reviewModel.setCustomer(new CustomerModel(username));
                            reviewAPI.insertReview(reviewModel).enqueue(new Callback<ReviewModel>() {
                                @Override
                                public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                                    Log.d("Review", "Thanh cong");
                                }
                                @Override
                                public void onFailure(Call<ReviewModel> call, Throwable t) {
                                    Log.d("Review", t.getMessage());
                                }
                            });
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    }
            });
        }
        else {
            myViewHolder.btnReview.setVisibility(View.GONE);
        }

    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.OfferList.size();
    }


}
