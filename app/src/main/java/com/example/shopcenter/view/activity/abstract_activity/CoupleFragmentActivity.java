package com.example.shopcenter.view.activity.abstract_activity;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shopcenter.R;
import com.example.shopcenter.view.fragment.BottomNavigationFragment;

public abstract class CoupleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_couple_fragment);

        add(R.id.fragment_container_upper, createFragment());
        add(R.id.fragment_container_lower, BottomNavigationFragment.newInstance());
    }

    private void add(@IdRes int resourceId, @NonNull Fragment fragment) {
        if (getSupportFragmentManager().findFragmentById(resourceId) == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(resourceId, fragment)
                    .commit();
    }
}
