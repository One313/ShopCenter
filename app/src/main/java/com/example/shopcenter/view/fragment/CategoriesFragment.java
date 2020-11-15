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
import com.example.shopcenter.databinding.FragmentCategoriesBinding;

public class CategoriesFragment extends Fragment {

    private FragmentCategoriesBinding mCategoriesBinding;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCategoriesBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_categories, container, false);
        return mCategoriesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}