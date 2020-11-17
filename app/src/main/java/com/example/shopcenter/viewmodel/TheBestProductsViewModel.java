package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;
import com.example.shopcenter.data.remote.ProductManager;

public class TheBestProductsViewModel extends StrategyProductViewModel {

    public TheBestProductsViewModel(@NonNull Application application) {
        super(application,
                ProductManager.getInstance().getTheBestProductItemsLiveData());
    }

    @Override
    public void onClickProductItems(int position) {

    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_the_best_products);
    }
}
