package com.example.shopcenter.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.HorizontalRecentProductListAdapter;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.databinding.FragmentHomeBinding;
import com.example.shopcenter.view.activity.ProductDetailActivity;
import com.example.shopcenter.utillity.ListType;
import com.example.shopcenter.viewmodel.HomeFragmentViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mHomeBinding;
    private HomeFragmentViewModel mViewModel;
    private HorizontalRecentProductListAdapter mRecentProductAdapter;
    private HorizontalRecentProductListAdapter mPopularProductAdapter;
    private HorizontalRecentProductListAdapter mRatingProductAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        observers();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setFont();
        setRecyclerLayouts();
        getActivity().setActionBar(mHomeBinding.toolbar);
        return mHomeBinding.getRoot();
    }

    private void setFont() {
        Typeface typeFaceTitle = Typeface.createFromAsset(getActivity().getAssets() , "fonts/Far_Casablanca.ttf");
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets() , "fonts/Far_Zar.ttf");
        mHomeBinding.textViewNewsTitle.setTypeface(typeFaceTitle);
        mHomeBinding.textViewPopularTitle.setTypeface(typeFaceTitle);
        mHomeBinding.textViewRatingTitle.setTypeface(typeFaceTitle);
        mHomeBinding.newsSeeMoreText.setTypeface(typeFace);
        mHomeBinding.mostViewSeeMoreText.setTypeface(typeFace);
        mHomeBinding.mostRateSeeMoreText.setTypeface(typeFace);
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

    private void setRecentProductAdapter(List<Product> products) {
        mRecentProductAdapter = new HorizontalRecentProductListAdapter(this, mViewModel, ListType.RECENT_PRODUCT);
        mHomeBinding.newsProductRecycler.setAdapter(mRecentProductAdapter);
    }

    private void setPopularProductAdapter(List<Product> products) {
        mPopularProductAdapter = new HorizontalRecentProductListAdapter(this, mViewModel,ListType.POPULAR_PRODUCT);
        mHomeBinding.mostReviewRecycler.setAdapter(mPopularProductAdapter);
    }

    private void setRatingProductAdapter(List<Product> products) {
        mRatingProductAdapter = new HorizontalRecentProductListAdapter(this, mViewModel , ListType.RATING_PRODUCT);
        mHomeBinding.mostRateRecycler.setAdapter(mRatingProductAdapter);
    }

    private void observers() {
        mViewModel.getRecentProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setRecentProductAdapter(products);
            }
        });

        mViewModel.getPopularProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setPopularProductAdapter(products);
            }
        });

        mViewModel.getRatingProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setRatingProductAdapter(products);
            }
        });

        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                startActivity(ProductDetailActivity.newIntent(getContext() ,Integer.parseInt(product.getId())));
//                getParentFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container ,
//                                ProductDetailFragment.newInstance(Integer.parseInt(product.getId())),
//                                SingleFragmentActivity.FRAGMENT_TAG)
//                        .commit();
            }
        });
    }
}