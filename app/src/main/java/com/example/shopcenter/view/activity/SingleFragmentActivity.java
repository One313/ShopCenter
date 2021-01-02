package com.example.shopcenter.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.ActivitySingleFragmentBinding;
import com.example.shopcenter.view.fragment.CategoryFragment;
import com.example.shopcenter.view.fragment.HomeFragment;
import com.example.shopcenter.view.fragment.MyShopCenterFragment;
import com.example.shopcenter.view.fragment.ShoppingCartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "fragmentActivity";
    private ActivitySingleFragmentBinding mBinding;

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_single_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container , createFragment() , FRAGMENT_TAG)
                    .commit();
        }

        mBinding.bottomNav.setItemIconTintList(null);
        mBinding.bottomNav.setOnNavigationItemSelectedListener(mReselectedListener);
        mBinding.bottomNav.setSelectedItemId(R.id.home_nav);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.home_nav:
                            fragment = HomeFragment.newInstance();
                            break;
                        case R.id.grouping_nav:
                            fragment = CategoryFragment.newInstance();
                            break;
                        case R.id.basket_nav:
                            fragment = ShoppingCartFragment.newInstance();
                            break;
                        case R.id.my_dijikala_nav:
                            fragment =  MyShopCenterFragment.newInstance();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container , fragment , FRAGMENT_TAG)
                            .commit();
                    return true;
                }
            };

}
