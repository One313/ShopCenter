package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.data.repository.ProductRepository;

public class CategoryProductListViewModel extends ProductStrategyViewModel {
    private int mCategoryId;
    public CategoryProductListViewModel(@NonNull Application application, int id) {
        super(application, ProductRepository.getInstance().getProductCategoryLiveData(id), id);
        mCategoryId = id;
    }

    public int getCategoryId() {
        return mCategoryId;
    }
}
