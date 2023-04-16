package com.example.mobileecommerce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.mobileecommerce.R;

/* loaded from: classes.dex */
public class FifthFragment extends Fragment {
    private View view;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_fifth, viewGroup, false);
        this.view = inflate;
        return inflate;
    }
}
