package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.data.repository.ProductRepository;

public class PopularProductViewModel extends ProductStrategyViewModel {

    public PopularProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getPopularProductLiveData());
    }
}
