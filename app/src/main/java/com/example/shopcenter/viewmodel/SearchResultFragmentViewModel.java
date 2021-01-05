package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.data.repository.ProductRepository;

public class SearchResultFragmentViewModel extends ProductStrategyViewModel {

    public SearchResultFragmentViewModel(@NonNull Application application , String word) {
        super(application, ProductRepository.getInstance().getProductsSearchResultLiveData(word));
    }
}
