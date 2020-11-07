package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.shopcenter.R;

public class LatestProductsFragment extends Fragment {

    public LatestProductsFragment() {
        // Required empty public constructor
    }

    public static LatestProductsFragment newInstance() {
        LatestProductsFragment fragment = new LatestProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBackPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_products, container, false);
    }

    private void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                replace(CategoriesFragment.newInstance());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
    }

    private void replace(@NonNull Fragment fragment) {
        if (getFragmentManager() != null)
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_upper, fragment)
                    .commit();
    }
}