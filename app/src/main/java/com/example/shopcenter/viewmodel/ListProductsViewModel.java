package com.example.shopcenter.viewmodel;

import androidx.lifecycle.ViewModel;

public class ListProductsViewModel extends ViewModel {

    private int mPage = 1;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getNextPage() {
        return ++mPage;
    }
}
