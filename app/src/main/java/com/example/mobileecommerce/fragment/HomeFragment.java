package com.example.mobileecommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileecommerce.R;
import com.example.mobileecommerce.adapter.HomeRecycleAdapter;
import com.example.mobileecommerce.api.ProductAPI;
import com.example.mobileecommerce.model.HomeViewModelClass;
import com.example.mobileecommerce.retrofit.RetrofitClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/* loaded from: classes.dex */
public class HomeFragment extends Fragment {
//    private FavouriteRecycleAdapter bAdapter;
//    private SmartPhoneRecycleAdapter bAdapter1;
    EditText editext;
    private ArrayList<HomeViewModelClass> homeViewModelClasses;
    private ArrayList<HomeViewModelClass> homeViewModelClasses1;
    private RecyclerView LastedProductRecyclerView;
    private RecyclerView PopularProductRecyclerView;
    private View view;

    private HomeRecycleAdapter bAdapter;
    private HomeRecycleAdapter bAdapter1;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        this.view = inflate;
        this.editext = (EditText) inflate.findViewById(R.id.editext);
        getLastedProduct();
        getPopularProduct();
        return this.view;
    }

    private void getPopularProduct() {
        ProductAPI productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);
        productAPI.PopularProduct().enqueue(new Callback<List<HomeViewModelClass>>() {
            @Override
            public void onResponse(Call<List<HomeViewModelClass>> call, Response<List<HomeViewModelClass>> response) {
                ArrayList<HomeViewModelClass> list = (ArrayList<HomeViewModelClass>) response.body();
                SetAdapterPopularProduct(list);
            }

            @Override
            public void onFailure(Call<List<HomeViewModelClass>> call, Throwable t) {
                Toast.makeText(getActivity() , "Fail To Load Product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLastedProduct() {
        ProductAPI productAPI = RetrofitClient.getRetrofit().create(ProductAPI.class);
        productAPI.LastedProduct().enqueue(new Callback<List<HomeViewModelClass>>() {
            @Override
            public void onResponse(Call<List<HomeViewModelClass>> call, Response<List<HomeViewModelClass>> response) {
                ArrayList<HomeViewModelClass> list = (ArrayList<HomeViewModelClass>) response.body();
                SetAdapterLastedProduct(list);
            }

            @Override
            public void onFailure(Call<List<HomeViewModelClass>> call, Throwable t) {
                Toast.makeText(getActivity() , "Fail To Load Product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SetAdapterLastedProduct(ArrayList<HomeViewModelClass> list) {
        this.LastedProductRecyclerView = (RecyclerView) this.view.findViewById(R.id.lasted_product_recyclerview);
        new LinearLayoutManager(getActivity());
        this.LastedProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.LastedProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.homeViewModelClasses = list;
        HomeRecycleAdapter lastedProductRecycleAdapter = new HomeRecycleAdapter(getActivity(), this.homeViewModelClasses);
        this.bAdapter = lastedProductRecycleAdapter;
        this.LastedProductRecyclerView.setAdapter(lastedProductRecycleAdapter);
    }
    private void SetAdapterPopularProduct(ArrayList<HomeViewModelClass> list) {
        this.PopularProductRecyclerView = (RecyclerView) this.view.findViewById(R.id.pupular_product_recyclerview);
        new LinearLayoutManager(getActivity());
        this.PopularProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        this.PopularProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.homeViewModelClasses1 = list;
        HomeRecycleAdapter PopularProductRecycleAdapter = new HomeRecycleAdapter(getActivity(), this.homeViewModelClasses1);
        this.bAdapter1 = PopularProductRecycleAdapter;
        this.PopularProductRecyclerView.setAdapter(PopularProductRecycleAdapter);
    }
}
