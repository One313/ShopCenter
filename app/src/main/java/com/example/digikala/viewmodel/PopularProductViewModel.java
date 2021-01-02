package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.digikala.data.repository.ProductRepository;

public class PopularProductViewModel extends ProductStrategyViewModel {

    public PopularProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getPopularProductLiveData());
    }
}
