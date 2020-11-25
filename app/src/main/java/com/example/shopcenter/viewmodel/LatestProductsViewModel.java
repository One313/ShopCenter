package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;

import static com.example.shopcenter.data.remote.ProductManager.getInstance;

public class LatestProductsViewModel extends StrategyProductViewModel {

    public LatestProductsViewModel(@NonNull Application application) {
        super(application, getInstance().getLatestProductItemsLiveData(1));
    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_latest_products);
    }

    @Override
    public void fetchItems(int page) {
        getInstance().fetchItemsLatestProduct(page);
    }
}
