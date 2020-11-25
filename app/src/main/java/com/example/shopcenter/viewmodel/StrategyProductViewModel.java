package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.callback.CallbackNavigation;
import com.example.shopcenter.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public abstract class StrategyProductViewModel extends AndroidViewModel {

    private List<ProductItem> mProductItems = new ArrayList<>();
    private ProductItem mProductItemSubject;
    private final LiveData<List<ProductItem>> mProductItemsListLiveData;
    private MutableLiveData<ProductItem> mProductItemSelectedMutableLiveData;
    private CallbackNavigation mCallbackNavigation;

    public List<ProductItem> getProductItems() {
        return mProductItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        mProductItems = productItems;
    }

    public ProductItem getProductItemSubject() {
        return mProductItemSubject;
    }

    public void setProductItemSubject(ProductItem productItemSubject) {
        mProductItemSubject = productItemSubject;
    }

    public LiveData<List<ProductItem>> getProductItemsListLiveData() {
        return mProductItemsListLiveData;
    }

    public MutableLiveData<ProductItem> getProductItemSelectedMutableLiveData() {
        mProductItemSelectedMutableLiveData = new MutableLiveData<>();
        return mProductItemSelectedMutableLiveData;
    }

    public void setProductItemSelectedMutableLiveData(MutableLiveData<ProductItem> productItemSelectedMutableLiveData) {
        mProductItemSelectedMutableLiveData = productItemSelectedMutableLiveData;
    }

    public void setCallbackNavigation(CallbackNavigation callbackNavigation) {
        mCallbackNavigation = callbackNavigation;
    }

    public StrategyProductViewModel(@NonNull Application application,
                                    MutableLiveData<List<ProductItem>> productItemsListLiveData) {
        super(application);
        mProductItemsListLiveData = productItemsListLiveData;
    }

    public void onClickNavigation() {
        mCallbackNavigation.onClickNavigation();
    }

    public void onClickProductItems(int position) {
        ProductItem productItem = getProductItems().get(position);
        mProductItemSelectedMutableLiveData.setValue(productItem);
    }

    public int getListSize() {
        return mProductItems == null ? 0 : mProductItems.size();
    }

    public abstract String getMessageViewModel();

    public abstract void fetchItems(int page);
}