package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.digikala.data.repository.ProductRepository;

public class RecentProductViewModel extends ProductStrategyViewModel {
    public RecentProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getRecentProductLiveData());
    }
}
