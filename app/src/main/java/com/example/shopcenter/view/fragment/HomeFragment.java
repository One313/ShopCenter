package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.ProductListAdapter;
import com.example.shopcenter.adapter.SliderVIPAdapter;
import com.example.shopcenter.databinding.FragmentHomeBinding;
import com.example.shopcenter.utillity.ListType;
import com.example.shopcenter.utillity.State;
import com.example.shopcenter.viewmodel.PopularProductViewModel;
import com.example.shopcenter.viewmodel.ProductStrategyViewModel;
import com.example.shopcenter.viewmodel.RatingProductViewModel;
import com.example.shopcenter.viewmodel.RecentProductViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mHomeBinding;
    private ProductStrategyViewModel mRecentViewModel;
    private ProductStrategyViewModel mPopularViewModel;
    private ProductStrategyViewModel mRatingViewModel;
    private ProductListAdapter mRecentProductAdapter;
    private ProductListAdapter mPopularProductAdapter;
    private ProductListAdapter mRatingProductAdapter;
    private SliderVIPAdapter mSliderVIPAdapter;
    private boolean mNeedToLoad = true;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HomeFragment", "onCreate");
        mRecentViewModel = new ViewModelProvider(this).get(RecentProductViewModel.class);
        mPopularViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
        mRatingViewModel = new ViewModelProvider(this).get(RatingProductViewModel.class);
        initAdapter();
        observers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mHomeBinding.setRecentViewModel((RecentProductViewModel) mRecentViewModel);
        mHomeBinding.setPopularViewModel((PopularProductViewModel) mPopularViewModel);
        mHomeBinding.setRatingViewModel((RatingProductViewModel) mRatingViewModel);
        mHomeBinding.setThisFragment(this);
        mHomeBinding.appBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: ..>
                Navigation.findNavController(mHomeBinding.getRoot()).
                        navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment());
            }
        });
        setRecyclerLayouts();
        return mHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!mNeedToLoad) {
            mHomeBinding.productRoot.setVisibility(View.VISIBLE);
            mHomeBinding.progressBarLoadingFragment.setVisibility(View.GONE);
        }
        mHomeBinding.newsProductRecycler.setAdapter(mRecentProductAdapter);
        mHomeBinding.mostReviewRecycler.setAdapter(mPopularProductAdapter);
        mHomeBinding.mostRateRecycler.setAdapter(mRatingProductAdapter);
        mHomeBinding.imageSlider.setSliderAdapter(mSliderVIPAdapter);
    }

    private void initAdapter() {
        mRecentProductAdapter = new ProductListAdapter(this, mRecentViewModel, ListType.RECENT_PRODUCT);
        mPopularProductAdapter = new ProductListAdapter(this, mPopularViewModel, ListType.POPULAR_PRODUCT);
        mRatingProductAdapter = new ProductListAdapter(this, mRatingViewModel, ListType.RATING_PRODUCT);
        mSliderVIPAdapter = new SliderVIPAdapter(getContext());
    }

    private void setRecyclerLayouts() {
        mHomeBinding.newsProductRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));

        mHomeBinding.mostReviewRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));

        mHomeBinding.mostRateRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        RecyclerView.HORIZONTAL,
                        true));
    }

    private void observers() {
        mRecentViewModel.getProductLiveData().observe(this, products -> {
            mRecentProductAdapter.notifyDataSetChanged();
        });

        mPopularViewModel.getProductLiveData().observe(this, products -> {
            mPopularProductAdapter.notifyDataSetChanged();
        });

        mRatingViewModel.getProductLiveData().observe(this, products -> {
            mRatingProductAdapter.notifyDataSetChanged();
        });

        mRecentViewModel.getProductSelectedLiveData().observe(this, product -> {
            goToDetailFragment(Integer.parseInt(product.getId()));
        });

        mPopularViewModel.getProductSelectedLiveData().observe(this, product -> {
            goToDetailFragment(Integer.parseInt(product.getId()));
        });
        mRatingViewModel.getProductSelectedLiveData().observe(this, product -> {
            goToDetailFragment(Integer.parseInt(product.getId()));
        });

        mRecentViewModel.getFragmentState().observe(this, state -> {
            if (state == State.NAVIGATE) {
                mHomeBinding.productRoot.setVisibility(View.VISIBLE);
                mHomeBinding.progressBarLoadingFragment.setVisibility(View.GONE);

                mNeedToLoad = false;
                mRecentViewModel.setFragmentState(State.LOADING);
            }
        });
    }

    //TODO: ..>
    private void goToDetailFragment(int productId) {
        HomeFragmentDirections.ActionHomeFragmentToProductDetailFragment action =
                HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(productId);
        Navigation.findNavController(mHomeBinding.getRoot()).navigate(action);
    }

    // TODO: ..>
    public void foToProductListFragment(int num) {
        HomeFragmentDirections.ActionHomeFragmentToProductListFragment action;
        if (num == 1) {
            action =
                    HomeFragmentDirections.actionHomeFragmentToProductListFragment(-1,ListType.RECENT_PRODUCT);
        } else if (num == 2) {
            action =
                    HomeFragmentDirections.actionHomeFragmentToProductListFragment(-1,ListType.POPULAR_PRODUCT);
        } else {
            action =
                    HomeFragmentDirections.actionHomeFragmentToProductListFragment(-1,ListType.RATING_PRODUCT);
        }
        Navigation.findNavController(mHomeBinding.getRoot()).navigate(action);
    }
}