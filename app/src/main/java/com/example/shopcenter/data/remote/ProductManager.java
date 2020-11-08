package com.example.shopcenter.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.model.ProductItem;
import com.example.shopcenter.network.GetProductItemsDeserializer;
import com.example.shopcenter.network.RetrofitInstance;
import com.example.shopcenter.network.WoocommerceService;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductManager {
    private static ProductManager sProductManager;

    public static ProductManager getInstance() {
        if (sProductManager == null)
            sProductManager = new ProductManager();
        return sProductManager;
    }

    private final WoocommerceService mWoocommerceService;
    private MutableLiveData<List<ProductItem>> mProductItemsLiveData;

    public MutableLiveData<List<ProductItem>> getProductItemsLiveData() {
        Call<List<ProductItem>> call = mWoocommerceService.listItems(RetrofitInstance.QUERY_OPTIONS);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                mProductItemsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
        return mProductItemsLiveData;
    }

    public void setProductItemsLiveData(MutableLiveData<List<ProductItem>> productItemsLiveData) {
        mProductItemsLiveData = productItemsLiveData;
    }

    private ProductManager() {
        mProductItemsLiveData = new MutableLiveData<>();
        Type type = new TypeToken<List<ProductItem>>() {
        }.getType();
        Object typeAdapter = new GetProductItemsDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type, typeAdapter);
        mWoocommerceService = retrofit.create(WoocommerceService.class);
    }
}
