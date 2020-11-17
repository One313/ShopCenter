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
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.PresentationProductAdapter;
import com.example.shopcenter.databinding.FragmentHomeBinding;
import com.example.shopcenter.viewmodel.LatestProductsViewModel;
import com.example.shopcenter.viewmodel.MostVisitedProductsViewModel;
import com.example.shopcenter.viewmodel.StrategyProductViewModel;
import com.example.shopcenter.viewmodel.TheBestProductsViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mFragmentHomeBinding;

    private StrategyProductViewModel[] mViewModels = new StrategyProductViewModel[3];
    private PresentationProductAdapter[] mAdapters = new PresentationProductAdapter[3];
    private RecyclerView[] mRecyclerViews = new RecyclerView[3];

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModels = new StrategyProductViewModel[]{
                new ViewModelProvider(requireActivity()).get(LatestProductsViewModel.class),
                new ViewModelProvider(requireActivity()).get(MostVisitedProductsViewModel.class),
                new ViewModelProvider(requireActivity()).get(TheBestProductsViewModel.class)};

        for (int i = 0; i < mViewModels.length; i++) {
            int counter = i;
            mViewModels[i].getProductItemsListLiveData().observe(this, productItems -> {

                mViewModels[counter].setProductItems(productItems);

                if (mAdapters[counter] == null) {
                    mAdapters[counter] = new PresentationProductAdapter(HomeFragment.this,
                            mViewModels[counter]);
                    mRecyclerViews[counter].setAdapter(mAdapters[counter]);
                } else
                    mAdapters[counter].notifyDataSetChanged();

            });
            mViewModels[i].setCallbackNavigation(() -> replace(ProductItemsFragment.newInstance()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentHomeBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_home, container, false);
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerViews = new RecyclerView[]{
                mFragmentHomeBinding.recyclerViewHomeLatestProducts,
                mFragmentHomeBinding.recyclerViewHomeMostVisitedProducts,
                mFragmentHomeBinding.recyclerViewHomeBestProducts};

        for (RecyclerView recyclerView : mRecyclerViews) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, true));
        }
    }

    private void replace(@NonNull Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_upper, fragment, fragment.getClass().getName())
                .commit();
    }
}