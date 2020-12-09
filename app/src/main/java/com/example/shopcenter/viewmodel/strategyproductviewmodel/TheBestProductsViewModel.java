package com.example.shopcenter.viewmodel.strategyproductviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;

import static com.example.shopcenter.data.remote.ProductManager.getInstance;

public class TheBestProductsViewModel extends StrategyProductViewModel {

    public TheBestProductsViewModel(@NonNull Application application) {
        super(application, getInstance().getTheBestProductItemsLiveData(1));
    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_the_best_products);
    }

    @Override
    public void fetchItems(int page) {
        getInstance().fetchItemsTheBestProduct(page);
    }
}
