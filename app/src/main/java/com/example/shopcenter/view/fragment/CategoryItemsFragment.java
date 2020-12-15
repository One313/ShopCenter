package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.FragmentCategoryItemsBinding;

public class CategoryItemsFragment extends Fragment {

    private FragmentCategoryItemsBinding mCategoryItemsBinding;

    public CategoryItemsFragment() {
        // Required empty public constructor
    }

    public static CategoryItemsFragment newInstance() {
        CategoryItemsFragment fragment = new CategoryItemsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCategoryItemsBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_category_items, container, false);
        return mCategoryItemsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}