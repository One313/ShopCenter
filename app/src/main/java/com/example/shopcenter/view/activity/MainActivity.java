package com.example.shopcenter.view.activity;

import androidx.fragment.app.Fragment;

import com.example.shopcenter.R;
import com.example.shopcenter.view.fragment.ShopEntryPointFragment;

public class MainActivity extends SingleFragmentActivity {


    @Override
    public int getResourceId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    public Fragment createFragment() {
        return ShopEntryPointFragment.newInstance();
    }
}