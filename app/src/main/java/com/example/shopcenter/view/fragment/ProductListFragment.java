package com.example.shopcenter.view.fragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.CategoryProductListAdapter;
import com.example.shopcenter.adapter.ProductListAdapter;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.databinding.FragmentProductListBinding;
import com.example.shopcenter.utillity.CategoryListViewModelFactory;
import com.example.shopcenter.utillity.ListType;
import com.example.shopcenter.viewmodel.CategoryProductListViewModel;
import com.example.shopcenter.viewmodel.PopularProductViewModel;
import com.example.shopcenter.viewmodel.ProductStrategyViewModel;
import com.example.shopcenter.viewmodel.RatingProductViewModel;
import com.example.shopcenter.viewmodel.RecentProductViewModel;

import java.util.List;

public class ProductListFragment extends Fragment {
    public static final String BUNDLE_KYE_LIST_TYPE = "com.example.digikala.ui_ListType";
    public static final String BUNDLE_KYE_CATEGORY_ID = "com.example.digikala.ui_CategoryId";

    private FragmentProductListBinding mBinding;
    private ListType mListType;
    private ProductStrategyViewModel mViewModel;

    private ProductListAdapter mAdapterHomeLists;
    private CategoryProductListAdapter mAdapterCategoryLists;
    int categoryId;


    public static ProductListFragment newInstance( ListType hemeListType , int categoryId) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KYE_CATEGORY_ID, categoryId);
        args.putSerializable(BUNDLE_KYE_LIST_TYPE, hemeListType);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if user come from home fragment
        mListType = ProductListFragmentArgs.fromBundle(getArguments()).getListType();
        if (mListType != null && mListType != ListType.NONE){
            switch (mListType){
                case RECENT_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(RecentProductViewModel.class);
                    break;
                case POPULAR_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(PopularProductViewModel.class);
                    break;
                case RATING_PRODUCT:
                    mViewModel = new ViewModelProvider(this).get(RatingProductViewModel.class);
            }
            mAdapterHomeLists = new ProductListAdapter(
                    ProductListFragment.this ,
                    mViewModel,
                    mListType);
            observersHomeLists();
        }else {
            categoryId = ProductListFragmentArgs.fromBundle(getArguments()).getCategoryId();
            mViewModel = new
                    ViewModelProvider(this ,
                    new CategoryListViewModelFactory((Application) getContext().getApplicationContext(),categoryId))
                    .get(CategoryProductListViewModel.class);
            mAdapterCategoryLists = new CategoryProductListAdapter(ProductListFragment.this  , mViewModel, mListType , categoryId);
            observerCategoryLists();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_product_list , container , false);
        setRecyclerLayoutManager();
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * when user select a product and go to DetailFragment and
         * when back to this fragment we should set the adapter to recycler view.
         * if we dont this set , fragment show empty list.
         */
        if (mListType != null && mListType != ListType.NONE)
            mBinding.productsRecycler.setAdapter(mAdapterHomeLists);
        else {
            mBinding.productsRecycler.setAdapter(mAdapterCategoryLists);
        }

    }

    private void setRecyclerLayoutManager() {
        mBinding.productsRecycler.setLayoutManager(new GridLayoutManager(getContext() , 2));
    }


    private void observersHomeLists(){
        mViewModel.getProductLiveData().observe(this , products -> {
            mAdapterHomeLists.notifyDataSetChanged();
        });
        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                // TODO: ..>
                ProductListFragmentDirections.ActionProductListFragmentToProductDetailFragment action =
                        ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(Integer.parseInt(product.getId()));
                Navigation.findNavController(mBinding.getRoot()).navigate(action);
            }
        });
    }

    private void observerCategoryLists(){
        mViewModel.getProductLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapterCategoryLists.notifyDataSetChanged();
            }
        });
        mViewModel.getProductSelectedLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                //TODO: ..>
                ProductListFragmentDirections.ActionProductListFragmentToProductDetailFragment action =
                        ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(Integer.parseInt(product.getId()));
                Navigation.findNavController(mBinding.getRoot()).navigate(action);
            }
        });
    }
}