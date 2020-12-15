package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.data.remote.CategoriesManager;
import com.example.shopcenter.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesViewModel extends AndroidViewModel {

    private Category mCategorySubject;
    private final Map<Integer, List<Category>> mMapCategoriesLivaData = new HashMap<>();
    private MutableLiveData<Category> mCategorySelectedLiveData;
    private final CategoriesManager mCategoriesManager;

    public Category getCategorySubject() {
        return mCategorySubject;
    }

    public void setCategorySubject(Category categorySubject) {
        mCategorySubject = categorySubject;
    }

    public Map<Integer, List<Category>> getMapCategoriesLivaData() {
        return mMapCategoriesLivaData;
    }

    public List<Category> getCategories(int parent) {
        return mMapCategoriesLivaData.get(parent);
    }

    public void setCategories(int parent, List<Category> categories) {
        mMapCategoriesLivaData.put(parent, categories);
    }

    public LiveData<List<Category>> getCategoriesLiveData(int page, int parent) {
        return mCategoriesManager.getCategoriesLiveData(page, parent);
    }

    public MutableLiveData<Category> getCategorySelectedLiveData() {
        mCategorySelectedLiveData = new MutableLiveData<>();
        return mCategorySelectedLiveData;
    }

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        mCategoriesManager = CategoriesManager.getInstance();
    }

    public void onClickCategory(int parent, int position) {
        mCategorySelectedLiveData.setValue(getCategories(parent).get(position));
    }

    public int getListSize(int parent) {
        return getCategories(parent).size();
    }
}
