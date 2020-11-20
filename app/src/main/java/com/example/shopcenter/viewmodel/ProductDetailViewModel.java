package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shopcenter.data.remote.ProductManager;
import com.example.shopcenter.model.ProductItem;

public class ProductDetailViewModel extends AndroidViewModel {

    private ProductManager mProductManager;
    private ProductItem mProductItemSubject;
    private LiveData<ProductItem> mProductItemLiveData;

    public ProductItem getProductItemSubject() {
        return mProductItemSubject;
    }

    public void setProductItemSubject(ProductItem productItemSubject) {
        mProductItemSubject = productItemSubject;
    }

    public LiveData<ProductItem> getProductItemLiveData(String productId) {
        mProductItemLiveData = mProductManager.getProductItemMutableLiveData(productId);
        return mProductItemLiveData;
    }

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProductManager = ProductManager.getInstance();
    }
}
