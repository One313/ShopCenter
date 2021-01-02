package com.example.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.digikala.data.repository.ProductRepository;

public class RatingProductViewModel extends ProductStrategyViewModel {
    public RatingProductViewModel(@NonNull Application application) {
        super(application, ProductRepository.getInstance().getRatingProductLiveData());
    }
}
