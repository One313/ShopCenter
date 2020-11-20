package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.ProductAdapter;
import com.example.shopcenter.databinding.FragmentProductItemsBinding;
import com.example.shopcenter.viewmodel.LatestProductsViewModel;
import com.example.shopcenter.viewmodel.MostVisitedProductsViewModel;
import com.example.shopcenter.viewmodel.StrategyProductViewModel;
import com.example.shopcenter.viewmodel.TheBestProductsViewModel;

public class ProductItemsFragment extends Fragment {

    public static final int SPAN_COUNT = 2;
    public static final String ARG_I = "com.example.shopcenter.view.fragment.i";
    private FragmentProductItemsBinding mProductItemsBinding;
    private StrategyProductViewModel[] mViewModels = new StrategyProductViewModel[3];
    private ProductAdapter mAdapter;
    private int mI;


    public ProductItemsFragment() {
        // Required empty public constructor
    }

    public static ProductItemsFragment newInstance(int i) {
        ProductItemsFragment fragment = new ProductItemsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_I, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mI = getArguments().getInt(ARG_I);
        }
        initializeViewModels();
        registerObserver(mI);
        onBackPressed();
    }

    private void initializeViewModels() {
        switch (mI) {
            case 0:
                mViewModels[mI] = new ViewModelProvider(requireActivity())
                        .get(LatestProductsViewModel.class);
                break;
            case 1:
                mViewModels[mI] =
                        new ViewModelProvider(requireActivity())
                                .get(MostVisitedProductsViewModel.class);
                break;
            default:
                mViewModels[mI] =
                        new ViewModelProvider(requireActivity())
                                .get(TheBestProductsViewModel.class);
                break;
        }
    }

    private void updateUI(int i) {
        if (mAdapter == null) {
            mAdapter = new ProductAdapter(ProductItemsFragment.this, mViewModels[i]);
            mProductItemsBinding.recyclerViewProductItems.setAdapter(mAdapter);
        } else mAdapter.notifyDataSetChanged();
    }

    private void registerObserver(int i) {
        mViewModels[i].getProductItemsListLiveData().observe(this, productItems -> {
            updateUI(i);
        });

        mViewModels[i].getProductItemSelectedMutableLiveData()
                .observe(this, productItem -> {
                    mViewModels[i].setProductItemSubject(productItem);
                    replace(ProductDetailFragment.newInstance(productItem.getID()));
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProductItemsBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_product_items, container, false);
        return mProductItemsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductItemsBinding.recyclerViewProductItems
                .setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
    }

    private void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                replace(CategoriesFragment.newInstance());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);
    }

    private void replace(@NonNull Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                .commit();
    }
}