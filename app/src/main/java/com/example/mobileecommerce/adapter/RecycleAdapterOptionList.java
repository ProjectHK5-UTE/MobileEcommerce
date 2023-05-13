package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.OptionModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleAdapterOptionList extends RecyclerView.Adapter<RecycleAdapterOptionList.MyViewHolder>{

    private Context context;
    private List<OptionModel> optionModelList;
    ItemClickListener itemClickListener;
    int selectedPosition = -1;

    public RecycleAdapterOptionList(Context context, List<OptionModel> optionModelList, ItemClickListener itemClickListener) {
        this.context = context;
        this.optionModelList = optionModelList;
        this.itemClickListener = itemClickListener;
    }

    public RecycleAdapterOptionList(Context context, List<OptionModel> optionModelList) {
        this.context = context;
        this.optionModelList = optionModelList;
    }

    @NonNull
    @Override
    public RecycleAdapterOptionList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option,
                parent, false);
        return new RecycleAdapterOptionList.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapterOptionList.MyViewHolder holder, int position) {
        OptionModel optionModel = optionModelList.get(position);
        holder.radioButton.setText(optionModel.getRam() + " Ram, "+optionModel.getRom()+ " Rom");
        /*if(position==0){
            holder.radioButton.setChecked(true);
        }else{
            holder.radioButton.setChecked(position==selectedPosition);
        }*/
        holder.radioButton.setChecked(position==selectedPosition);
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectedPosition = holder.getAdapterPosition();
                    itemClickListener.onClick(selectedPosition);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(optionModelList!= null){
            return optionModelList.size();
        }
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private RadioButton radioButton;
        private TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.radio1);
        }
    }
    public interface ItemClickListener{
        void onClick(int id);

    }
}
