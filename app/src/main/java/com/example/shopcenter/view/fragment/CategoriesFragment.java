package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.CategoriesInRootAdapter;
import com.example.shopcenter.databinding.FragmentCategoriesBinding;
import com.example.shopcenter.viewmodel.CategoriesViewModel;

public class CategoriesFragment extends Fragment {

    private FragmentCategoriesBinding mCategoriesBinding;
    private CategoriesViewModel mCategoriesViewModel;
    private CategoriesInRootAdapter mCategoriesInRootAdapter;

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

        mCategoriesViewModel = new ViewModelProvider(this)
                .get(CategoriesViewModel.class);

        registerObservers();
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

        mCategoriesBinding.recyclerViewCategoriesRoot
                .setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void updateUI() {
        if (mCategoriesInRootAdapter == null) {
            mCategoriesInRootAdapter =
                    new CategoriesInRootAdapter(0,this, mCategoriesViewModel);
            mCategoriesBinding.recyclerViewCategoriesRoot.setAdapter(mCategoriesInRootAdapter);
        } else mCategoriesInRootAdapter.notifyDataSetChanged();
    }

    private void registerObservers() {

        mCategoriesViewModel.getCategoriesLiveData(1, 0)
                .observe(this, categories -> {

                    mCategoriesViewModel.setCategories(0, categories);
                    updateUI();
                });

        mCategoriesViewModel.getCategorySelectedLiveData()
                .observe(this, category -> {

                    mCategoriesViewModel.setCategorySubject(category);
                    replace(CategoryItemsFragment.newInstance());
                });
    }

    private void replace(@NonNull Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                .commit();
    }
}