package com.example.mobileecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mobileecommerce.R;
import com.example.mobileecommerce.model.ImagesModel;
import com.example.mobileecommerce.model.OptionModel;
import com.example.mobileecommerce.model.ProductGridModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    // Context object
    Context context;

    // Array of images
    ProductGridModel productGridModel;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ProductGridModel productGridModel, LayoutInflater mLayoutInflater) {
        this.context = context;
        this.productGridModel = productGridModel;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getCount() {
        int count=0;
        List<OptionModel> oList = productGridModel.getOptions();
        for(int i=0;i<oList.size();i++){
            count += oList.get(i).getImages().size();
        }
        return count;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.item_view_pager, container, false);
        // referencing the image view from the item.xml file
        ImageView imageView = itemView.findViewById(R.id.imageViewMain);
        // get the list of image paths
        List<String> imagePaths = new ArrayList<>();
        List<OptionModel> options = productGridModel.getOptions();
        for (OptionModel option : options) {
            List<ImagesModel> images = option.getImages();
            for (ImagesModel image : images) {
                imagePaths.addAll(Collections.singleton(image.getPath()));
            }
        }
        // use Glide to load the image from the file path and set it to the imageView
        Glide.with(context)
                .load(imagePaths.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        // Adding the View
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
