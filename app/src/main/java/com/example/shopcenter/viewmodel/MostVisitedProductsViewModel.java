package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;
import com.example.shopcenter.data.remote.ProductManager;

public class MostVisitedProductsViewModel extends StrategyProductViewModel {

    public MostVisitedProductsViewModel(@NonNull Application application) {
        super(application,
                ProductManager.getInstance().getMostVisitedProductItemsLiveData());
    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_the_most_visited_products);
    }
}
