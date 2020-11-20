package com.example.shopcenter.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.model.ProductItem;
import com.example.shopcenter.network.WoocommerceService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.shopcenter.network.RetrofitInstance.RETROFIT_LIST_PRODUCT;
import static com.example.shopcenter.network.RetrofitInstance.RETROFIT_PRODUCT;
import static com.example.shopcenter.network.RetrofitParams.QUERY_OPTIONS;
import static com.example.shopcenter.network.RetrofitParams.QUERY_POPULARITY;
import static com.example.shopcenter.network.RetrofitParams.QUERY_RATING;

public class ProductManager {

    private static final ProductManager INSTANCE = new ProductManager();

    public static ProductManager getInstance() {
        return INSTANCE;
    }

    private final MutableLiveData<List<ProductItem>>
            mLatestProductItemsLiveData = new MutableLiveData<>(),
            mMostVisitedProductItemsLiveData = new MutableLiveData<>(),
            mTheBestProductItemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<ProductItem> mProductItemMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<ProductItem>> getLatestProductItemsLiveData() {
        Call<List<ProductItem>> call = RETROFIT_LIST_PRODUCT.create(WoocommerceService.class)
                .listItems(QUERY_OPTIONS);
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

    public MutableLiveData<List<ProductItem>> getMostVisitedProductItemsLiveData() {
        Call<List<ProductItem>> call = RETROFIT_LIST_PRODUCT.create(WoocommerceService.class)
                .listItems(QUERY_POPULARITY);
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

    public MutableLiveData<List<ProductItem>> getTheBestProductItemsLiveData() {
        Call<List<ProductItem>> call = RETROFIT_LIST_PRODUCT.create(WoocommerceService.class)
                .listItems(QUERY_RATING);
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

    public MutableLiveData<ProductItem> getProductItemMutableLiveData(String productId) {
        Call<ProductItem> call = RETROFIT_PRODUCT.create(WoocommerceService.class)
                .item(productId, QUERY_OPTIONS);
        call.enqueue(new Callback<ProductItem>() {
            @Override
            public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
                mProductItemMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductItem> call, Throwable t) {

            }
        });
        return mProductItemMutableLiveData;
    }

    private ProductManager() {
    }
}
