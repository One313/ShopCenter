package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.LatestProductAdapter;
import com.example.shopcenter.databinding.FragmentLatestProductsBinding;
import com.example.shopcenter.viewmodel.LatestProductViewModel;

public class LatestProductsFragment extends Fragment {

    public static final int SPAN_COUNT = 2;
    private FragmentLatestProductsBinding mLatestProductsBinding;
    private LatestProductViewModel mLatestProductViewModel;
    private LatestProductAdapter mLatestProductAdapter;


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
        mLatestProductViewModel =
                new ViewModelProvider(requireActivity()).get(LatestProductViewModel.class);

        registerObservers();
        onBackPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLatestProductsBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_latest_products, container, false);
        return mLatestProductsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLatestProductsBinding.recyclerViewLatestProducts
                .setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
    }

    private void registerObservers() {
        mLatestProductViewModel.getProductItemsListLiveData()
                .observe(this, productItems -> {
                    mLatestProductViewModel.setProductItems(productItems);
                    updateUI();
                });
    }

    private void updateUI() {
        if (mLatestProductAdapter == null) {
            mLatestProductAdapter =
                    new LatestProductAdapter(this, mLatestProductViewModel);
            mLatestProductsBinding.recyclerViewLatestProducts
                    .setAdapter(mLatestProductAdapter);
        } else {
            mLatestProductAdapter.notifyDataSetChanged();
        }
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
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                .commit();
    }
}