package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.PresentationLatestProductAdapter;
import com.example.shopcenter.databinding.FragmentHomeBinding;
import com.example.shopcenter.viewmodel.LatestProductViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mFragmentHomeBinding;
    private LatestProductViewModel mLatestProductViewModel;
    private PresentationLatestProductAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLatestProductViewModel = new ViewModelProvider(requireActivity())
                .get(LatestProductViewModel.class);

        mLatestProductViewModel.getProductItemsListLiveData()
                .observe(this, productItems -> {
                    mLatestProductViewModel.setProductItems(productItems);
                    UpdateUI();
                });

        mLatestProductViewModel.setCallback(() -> replace(LatestProductsFragment.newInstance()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_home, container, false);
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, true);
        mFragmentHomeBinding.recyclerViewHomeLatestProducts.setLayoutManager(layoutManager);
    }

    private void UpdateUI() {
        if (mAdapter == null) {
            mAdapter = new PresentationLatestProductAdapter(this, mLatestProductViewModel);
            mFragmentHomeBinding.recyclerViewHomeLatestProducts.setAdapter(mAdapter);
        } else
            mAdapter.notifyDataSetChanged();
    }

    private void replace(@NonNull Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                .commit();
    }
}