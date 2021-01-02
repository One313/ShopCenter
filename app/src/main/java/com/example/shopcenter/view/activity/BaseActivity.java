package com.example.shopcenter.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shopcenter.view.fragment.HomeFragment;

public class BaseActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}