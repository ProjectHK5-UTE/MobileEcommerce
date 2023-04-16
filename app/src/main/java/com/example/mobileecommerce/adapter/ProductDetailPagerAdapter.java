package com.example.mobileecommerce.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mobileecommerce.fragment.*;

/* loaded from: classes.dex */
public class ProductDetailPagerAdapter extends FragmentStatePagerAdapter {
    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 5;
    }

    public ProductDetailPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return null;
                        }
                        return new FifthFragment();
                    }
                    return new FourthFragment();
                }
                return new ThirdFragment();
            }
            return new SecondFragment();
        }
        return new FirstFragment();
    }
}
