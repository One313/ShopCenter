package com.example.shopcenter.view.activity;

import androidx.fragment.app.Fragment;

import com.example.shopcenter.view.activity.abstract_activity.CoupleFragmentActivity;
import com.example.shopcenter.view.fragment.HomeFragment;

public class EntryShopActivity extends CoupleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}