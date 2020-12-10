package com.example.shopcenter.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopcenter.data.remote.CategoriesManager;
import com.example.shopcenter.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesInRootViewModel extends AndroidViewModel {

    private Category mCategorySubject;
    private List<Category> mCategories = new ArrayList<>();
    private LiveData<List<Category>> mCategoriesLiveData;
    private MutableLiveData<Category> mCategorySelectedLiveData;
    private CategoriesManager mCategoriesManager;

    public Category getCategorySubject() {
        return mCategorySubject;
    }

    public void setCategorySubject(Category categorySubject) {
        mCategorySubject = categorySubject;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public LiveData<List<Category>> getCategoriesLiveData() {
        return mCategoriesLiveData;
    }

    public MutableLiveData<Category> getCategorySelectedLiveData() {
        mCategorySelectedLiveData = new MutableLiveData<>();
        return mCategorySelectedLiveData;
    }

    public CategoriesInRootViewModel(@NonNull Application application) {
        super(application);
        mCategoriesManager = CategoriesManager.getInstance();
        mCategoriesLiveData = mCategoriesManager.getCategoriesLiveData(1,0);
    }

    public void onClickCategory(int position) {
        mCategorySelectedLiveData.setValue(mCategories.get(position));
    }

    public int getListSize() {
        return mCategories.size();
    }

    // TODO: some works and edits
}