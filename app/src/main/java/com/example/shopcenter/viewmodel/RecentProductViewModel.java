package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.data.repository.ProductRepository;

public class RecentProductViewModel extends ProductStrategyViewModel {

    public RecentProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getRecentProductLiveData());
    }
}
