package com.example.shopcenter.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.model.Category;
import com.example.shopcenter.model.ProductItem;
import com.example.shopcenter.network.WoocommerceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.shopcenter.network.RetrofitInstance.*;
import static com.example.shopcenter.network.RetrofitParams.*;

public class CategoriesManager {

    private static final CategoriesManager INSTANCE = new CategoriesManager();

    public static CategoriesManager getInstance() {
        return INSTANCE;
    }

    private final Map<Integer, MutableLiveData<List<Category>>> mMapCategoriesLivaData
            = new HashMap<>();

    private final MutableLiveData<List<ProductItem>> mProductItemsLivaData
            = new MutableLiveData<>();

    public Map<Integer, MutableLiveData<List<Category>>> getMapCategoriesLivaData() {
        return mMapCategoriesLivaData;
    }

    public MutableLiveData<List<Category>> getCategoriesLiveData(int page, int parent) {
        fetchCategoriesLivaData(page, parent);
        return mMapCategoriesLivaData.get(parent);
    }

    public MutableLiveData<List<ProductItem>> getProductItemsLivaData(int page, int categoryId) {
        fetchProductItemsLivaData(page, categoryId);
        return mProductItemsLivaData;
    }

    private CategoriesManager() {
    }

    public void fetchCategoriesLivaData(int page, int parent) {

        Call<List<Category>> call = RETROFIT_LIST_CATEGORY
                .create(WoocommerceService.class)
                .listCategories(page, parent, QUERY_OPTIONS);

        if (mMapCategoriesLivaData.get(parent) == null)
            mMapCategoriesLivaData.put(parent, new MutableLiveData<>());

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                fillOnCategoriesLivaData(mMapCategoriesLivaData.get(parent), response, page);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    public void fetchProductItemsLivaData(int page, int categoryId) {
        // TODO: fetch products
    }

    private void fillOnProductsLiveData(MutableLiveData<List<ProductItem>> itemsLiveData,
                                        Response<List<ProductItem>> response, int page) {
        if (page == 1) itemsLiveData.setValue(response.body());
        else {
            List<ProductItem> list = itemsLiveData.getValue();
            if (list != null && response.body() != null) list.addAll(response.body());
            itemsLiveData.setValue(list);
        }
    }

    private void fillOnCategoriesLivaData(MutableLiveData<List<Category>> itemsLiveData,
                                          Response<List<Category>> response, int page) {
        if (page == 1) itemsLiveData.setValue(response.body());
        else {
            List<Category> list = itemsLiveData.getValue();
            if (list != null && response.body() != null) list.addAll(response.body());
            itemsLiveData.setValue(list);
        }
    }
}
