package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;
import com.example.shopcenter.data.remote.ProductManager;

public class LatestProductsViewModel extends StrategyProductViewModel {

    public LatestProductsViewModel(@NonNull Application application) {
        super(application,
                ProductManager.getInstance().getLatestProductItemsLiveData());
    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_latest_products);
    }
}
