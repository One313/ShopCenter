package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.data.remote.ProductManager;
import com.example.shopcenter.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class LatestProductViewModel extends AndroidViewModel {

    private ProductManager mProductManager;
    private List<ProductItem> mProductItems;
    private LiveData<List<ProductItem>> mProductItemsListLiveData;
    private MutableLiveData<ProductItem> mProductItemSelectedMutableLiveData;

    public List<ProductItem> getProductItems() {
        return mProductItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        mProductItems = productItems;
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

    public LatestProductViewModel(@NonNull Application application) {
        super(application);
        mProductItems = new ArrayList<>();
        mProductManager = ProductManager.getInstance();
        mProductItemsListLiveData = mProductManager.getProductItemsLiveData();
    }

    public void onClickProductItems(int position) {

    }

    public int getListSize() {
        return mProductItems == null ? 0 : mProductItems.size();
    }

}
