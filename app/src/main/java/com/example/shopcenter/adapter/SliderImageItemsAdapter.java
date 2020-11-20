package com.example.shopcenter.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopcenter.view.fragment.SliderImageItemsFragment;
import com.example.shopcenter.viewmodel.ProductDetailViewModel;

public class SliderImageItemsAdapter extends FragmentStateAdapter {

    private final ProductDetailViewModel mProductDetailViewModel;

    public SliderImageItemsAdapter(@NonNull Fragment fragment,
                                   ProductDetailViewModel productDetailViewModel) {
        super(fragment);
        mProductDetailViewModel = productDetailViewModel;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return SliderImageItemsFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return mProductDetailViewModel.getProductItemSubject().getImages().length;
    }
}
