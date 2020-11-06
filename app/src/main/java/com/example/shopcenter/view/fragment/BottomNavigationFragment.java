package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.FragmentBottomNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationFragment extends Fragment {

    private FragmentBottomNavigationBinding mBottomNavigationBinding;

    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    public static BottomNavigationFragment newInstance() {
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBottomNavigationBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.fragment_bottom_navigation,
                        container,
                        false);
        return mBottomNavigationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomNavigationBinding.bottomNavigationShopCenter.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_navigation_my_shop:
                                replace(MyShopFragment.newInstance());
                                return true;
                            case R.id.menu_navigation_cart:
                                replace(CartFragment.newInstance());
                                return true;
                            case R.id.menu_navigation_categories:
                                replace(CategoriesFragment.newInstance());
                                return true;
                            case R.id.menu_navigation_home:
                                replace(HomeFragment.newInstance());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    private void replace(@NonNull Fragment fragment) {
        if (getFragmentManager().findFragmentById(R.id.fragment_container_upper) != fragment)
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                    .commit();
    }
}