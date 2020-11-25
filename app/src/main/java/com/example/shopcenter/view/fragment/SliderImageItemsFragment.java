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
import com.example.shopcenter.databinding.FragmentSliderImageItemsBinding;
import com.example.shopcenter.viewmodel.ProductDetailViewModel;
import com.squareup.picasso.Picasso;

public class SliderImageItemsFragment extends Fragment {

    public static final String ARG_POSITION = "com.example.shopcenter.view.fragment.position";
    private FragmentSliderImageItemsBinding mSliderImageItemsBinding;
    private ProductDetailViewModel mProductDetailViewModel;
    private int mPosition;

    public SliderImageItemsFragment() {
        // Required empty public constructor
    }

    public static SliderImageItemsFragment newInstance(int position) {
        SliderImageItemsFragment fragment = new SliderImageItemsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
        mProductDetailViewModel = new ViewModelProvider(requireActivity())
                .get(ProductDetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSliderImageItemsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_slider_image_items, container, false);
        return mSliderImageItemsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSliderImageItemsBinding.setProductDetailViewModel(mProductDetailViewModel);

        Picasso.get()
                .load(mProductDetailViewModel.getProductItemSubject().getImages()[mPosition].getSrc())
                .resize(0, 200)
                .placeholder(R.drawable.ic_navigation_bottom_cart)
                .into(mSliderImageItemsBinding.imageViewSlider);
    }
}