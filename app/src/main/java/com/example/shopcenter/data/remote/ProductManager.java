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

import static com.example.shopcenter.network.RetrofitParams.QUERY_OPTIONS;
import static com.example.shopcenter.network.RetrofitParams.QUERY_POPULARITY;
import static com.example.shopcenter.network.RetrofitParams.QUERY_RATING;

public class ProductManager {
    private static ProductManager sProductManager;

    public static ProductManager getInstance() {
        if (sProductManager == null)
            sProductManager = new ProductManager();
        return sProductManager;
    }

    private final WoocommerceService mWoocommerceService;
    private MutableLiveData<List<ProductItem>>
            mLatestProductItemsLiveData = new MutableLiveData<>(),
            mMostVisitedProductItemsLiveData = new MutableLiveData<>(),
            mTheBestProductItemsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<ProductItem>> getLatestProductItemsLiveData() {
        Call<List<ProductItem>> call = mWoocommerceService.listItems(QUERY_OPTIONS);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call,
                                   Response<List<ProductItem>> response) {
                mLatestProductItemsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
        return mLatestProductItemsLiveData;
    }

    public void setLatestProductItemsLiveData(MutableLiveData<List<ProductItem>> latestProductItemsLiveData) {
        mLatestProductItemsLiveData = latestProductItemsLiveData;
    }

    public MutableLiveData<List<ProductItem>> getMostVisitedProductItemsLiveData() {
        Call<List<ProductItem>> call = mWoocommerceService.listItems(QUERY_POPULARITY);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call,
                                   Response<List<ProductItem>> response) {
                mMostVisitedProductItemsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
        return mMostVisitedProductItemsLiveData;
    }

    public void setMostVisitedProductItemsLiveData(MutableLiveData<List<ProductItem>> mostVisitedProductItemsLiveData) {
        mMostVisitedProductItemsLiveData = mostVisitedProductItemsLiveData;
    }

    public MutableLiveData<List<ProductItem>> getTheBestProductItemsLiveData() {
        Call<List<ProductItem>> call = mWoocommerceService.listItems(QUERY_RATING);
        call.enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call,
                                   Response<List<ProductItem>> response) {
                mTheBestProductItemsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
        return mTheBestProductItemsLiveData;
    }

    public void setTheBestProductItemsLiveData(MutableLiveData<List<ProductItem>> theBestProductItemsLiveData) {
        mTheBestProductItemsLiveData = theBestProductItemsLiveData;
    }

    private ProductManager() {
        Type type = new TypeToken<List<ProductItem>>() {
        }.getType();
        Object typeAdapter = new GetProductItemsDeserializer();

        Retrofit retrofit = RetrofitInstance.getInstance(type, typeAdapter);
        mWoocommerceService = retrofit.create(WoocommerceService.class);
    }
}
