package com.example.mobileecommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.FavouriteRecycleAdapter;
import com.example.mobileecommerce.adapter.SmartPhoneRecycleAdapter;
import com.example.mobileecommerce.model.FavouriteModelClass;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class HomeFragment extends Fragment {
//    private FavouriteRecycleAdapter bAdapter;
//    private SmartPhoneRecycleAdapter bAdapter1;
    EditText editext;
    private ArrayList<FavouriteModelClass> favouriteModelClasses;
    private ArrayList<FavouriteModelClass> favouriteModelClasses1;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private View view;
    private int[] image = {R.drawable.headphone, R.drawable.reybane, R.drawable.shoesfila, R.drawable.headphone, R.drawable.reybane, R.drawable.shoesfila};
    private String[] title = {"Headphone", "Rayban", "Shoes", "Headphone", "Rayban", "Shoes"};
    private String[] price = {"Rs 500", "Rs 550", "Rs 750", "Rs 500", "Rs 550", "Rs 750"};
    private int[] image1 = {R.drawable.s9plus, R.drawable.iphnx, R.drawable.vivo11, R.drawable.googlepixel, R.drawable.s9plus, R.drawable.iphnx};
    private String[] title1 = {"Samsung S9+", "I Phone X", "Vivo V11", "Google Pixel 3", "Samsung S9+", "I Phone X"};
    private String[] price1 = {"Rs 45,000", "Rs 55,000", "Rs 25,000", "Rs 40,000", "Rs 43,000", "Rs 51,000"};
    private FavouriteRecycleAdapter bAdapter;
    private SmartPhoneRecycleAdapter bAdapter1;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        this.view = inflate;
        this.editext = (EditText) inflate.findViewById(R.id.editext);
        this.recyclerView = (RecyclerView) this.view.findViewById(R.id.favourit_recyclerview);
        new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.favouriteModelClasses = new ArrayList<>();
        for (int i = 0; i < this.image.length; i++) {
            this.favouriteModelClasses.add(new FavouriteModelClass(Integer.valueOf(this.image[i]), this.title[i], this.price[i]));
        }
        FavouriteRecycleAdapter favouriteRecycleAdapter = new FavouriteRecycleAdapter(getActivity(), this.favouriteModelClasses);
        this.bAdapter = favouriteRecycleAdapter;
        this.recyclerView.setAdapter(favouriteRecycleAdapter);
        this.recyclerView1 = (RecyclerView) this.view.findViewById(R.id.smartphone_recyclerview);
        new LinearLayoutManager(getActivity());
        this.recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        this.recyclerView1.setItemAnimator(new DefaultItemAnimator());
        this.favouriteModelClasses1 = new ArrayList<>();
        for (int i2 = 0; i2 < this.image1.length; i2++) {
            this.favouriteModelClasses1.add(new FavouriteModelClass(Integer.valueOf(this.image1[i2]), this.title1[i2], this.price1[i2]));
        }

        SmartPhoneRecycleAdapter smartPhoneRecycleAdapter = new SmartPhoneRecycleAdapter(getActivity(), this.favouriteModelClasses1);
        this.bAdapter1 = smartPhoneRecycleAdapter;
        this.recyclerView1.setAdapter(smartPhoneRecycleAdapter);
        return this.view;
    }
}
