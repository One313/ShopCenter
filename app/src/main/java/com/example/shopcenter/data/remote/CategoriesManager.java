package com.example.shopcenter.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.model.Category;
import com.example.shopcenter.model.ProductItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesManager {

    private static final CategoriesManager INSTANCE = new CategoriesManager();

    public static CategoriesManager getInstance() {
        return INSTANCE;
    }

    private final Map<Integer, MutableLiveData<List<Category>>> mMapCategoriesLivaData =
            new HashMap<>();
    private final MutableLiveData<List<ProductItem>> mProductItemsLivaData =
            new MutableLiveData<>();

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
        // TODO: fetch categories
    }

    public void fetchProductItemsLivaData(int page, int categoryId) {
        // TODO: fetch products
    }
}
