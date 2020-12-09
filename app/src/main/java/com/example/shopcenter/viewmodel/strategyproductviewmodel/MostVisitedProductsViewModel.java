package com.example.shopcenter.viewmodel.strategyproductviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.shopcenter.R;

import static com.example.shopcenter.data.remote.ProductManager.getInstance;

public class MostVisitedProductsViewModel extends StrategyProductViewModel {

    public MostVisitedProductsViewModel(@NonNull Application application) {
        super(application, getInstance().getMostVisitedProductItemsLiveData(1));
    }

    @Override
    public String getMessageViewModel() {
        return getApplication().getString(R.string.message_the_most_visited_products);
    }

    @Override
    public void fetchItems(int page) {
        getInstance().fetchItemsMostVisitedProduct(page);
    }
}
