package com.example.shopcenter.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shopcenter.R;
import com.example.shopcenter.adapter.SliderAdapter;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.databinding.FragmentProductDetailBinding;
import com.example.shopcenter.viewmodel.DetailFragmentViewModel;

public class ProductDetailFragment extends Fragment {


    public static final String BUNDLE_KEY_PRODUCT_ID = "productID";
    private DetailFragmentViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private Product mProduct;
    private SliderAdapter mSliderAdapter;
    private int mId;

    public static ProductDetailFragment newInstance(int id) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_PRODUCT_ID , id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(DetailFragmentViewModel.class);
        mId = ProductDetailFragmentArgs.fromBundle(getArguments()).getProductId();

        mViewModel.fetchProduct(String.valueOf(mId));
        mViewModel.getProductLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mViewModel.setProductSubjectLiveData(product);
                mBinding.productSlider.setSliderAdapter(
                        new SliderAdapter(getContext() ,
                                mViewModel.getProductSubjectLiveData().getValue()));
                formatTexts();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        mBinding.buttonAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addProductToCart(mId);
            }
        });
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }



    private void formatTexts() {
        mBinding.textViewProductDescription.setText(
                Html.fromHtml(
                        mViewModel.getProductSubjectLiveData().getValue().getDescription(),
                        Html.FROM_HTML_MODE_COMPACT));
    }
}