package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.fragment.*;
import com.example.mobileecommerce.model.ImagesModel;
import com.example.mobileecommerce.model.OptionModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ImagesModel> imagesModel;

    public ProductDetailPagerAdapter(Context context, List<ImagesModel> imagesModel) {
        this.context = context;
        this.imagesModel = imagesModel;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return null;
    }

    @Override
    public int getCount() {
        if(imagesModel!= null){
            return imagesModel.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
