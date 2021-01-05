package com.example.shopcenter.view.fragment;

import android.app.Application;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.SearchListAdapter;
import com.example.shopcenter.databinding.FragmentSearchResultBinding;
import com.example.shopcenter.utillity.SearchResultViewModelFactory;
import com.example.shopcenter.viewmodel.SearchResultFragmentViewModel;

public class SearchResultFragment extends Fragment {

    private FragmentSearchResultBinding mBinding;
    private String mSearchWord;
    private SearchResultFragmentViewModel mViewModel;
    private SearchListAdapter mAdapter;

    public static SearchResultFragment newInstance() {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchWord = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchWord();
        mViewModel = new
                ViewModelProvider(this,
                new SearchResultViewModelFactory((Application) getContext().getApplicationContext(), mSearchWord))
                .get(SearchResultFragmentViewModel.class);
        initAdapter();
        observer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        mBinding.productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mBinding.productRecycler.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    private void initAdapter() {
        mAdapter = new SearchListAdapter(mViewModel, this, mSearchWord);
    }

    private void observer() {
        mViewModel.getProductLiveData().observe(this, products -> {
            mAdapter.notifyDataSetChanged();
        });
    }
}