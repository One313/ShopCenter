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

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.SliderImageItemsAdapter;
import com.example.shopcenter.databinding.FragmentProductDetailBinding;
import com.example.shopcenter.viewmodel.ProductDetailViewModel;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_PRODUCT_ID = "com.example.shopcenter.view.fragment.productId";
    private FragmentProductDetailBinding mProductDetailBinding;
    private ProductDetailViewModel mProductDetailViewModel;
    private SliderImageItemsAdapter mAdapter;

    private int mProductId;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getInt(ARG_PRODUCT_ID);
        }

        mProductDetailViewModel = new ViewModelProvider(requireActivity()).get(ProductDetailViewModel.class);
        mProductDetailViewModel.getProductItemLiveData(mProductId)
                .observe(this, productItem -> {
                    mProductDetailViewModel.setProductItemSubject(productItem);

                    mProductDetailBinding.setProductDetailViewModel(mProductDetailViewModel);

                    mAdapter = new SliderImageItemsAdapter(this, mProductDetailViewModel);
                    mProductDetailBinding.viewPagerSlider.setAdapter(mAdapter);
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail,
                container, false);
        return mProductDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}