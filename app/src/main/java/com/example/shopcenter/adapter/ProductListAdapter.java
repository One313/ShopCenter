package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopcenter.R;
import com.example.shopcenter.data.model.ProductImage;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.databinding.ProductItemListBinding;
import com.example.shopcenter.utillity.ListType;
import com.example.shopcenter.viewmodel.ProductStrategyViewModel;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductHolder> {

    private List<Product> mProductList;
    private LifecycleOwner mOwner;
    private ListType mListType;
    private ProductStrategyViewModel mViewModel;
    private int mPage = 2;

    public ProductListAdapter(
            LifecycleOwner owner,
            ProductStrategyViewModel viewModel,
            ListType listType) {
        mOwner = owner;
        mViewModel = viewModel;
        mListType = listType;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemListBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mViewModel.getApplication()),
                R.layout.product_item_list,
                parent,
                false
        );
        return new ProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.onBind(position);

        if (position == mViewModel.getProductLiveData().getValue().size() - 1) {
            mViewModel.updateList(mListType, mPage);
            mPage++;
        }

    }

    @Override
    public int getItemCount() {
        final int[] size = {0};
        mViewModel.getProductLiveData().observe(mOwner, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                size[0] = products.size();
            }
        });
        return size[0];
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        private ProductItemListBinding mItemListBinding;

        public ProductHolder(ProductItemListBinding productItemListBinding) {
            super(productItemListBinding.getRoot());
            mItemListBinding = productItemListBinding;
            mItemListBinding.setViewModel(mViewModel);
            mItemListBinding.setListType(mListType);
        }

        public void onBind(int position) {
            mItemListBinding.setPosition(position);
            mItemListBinding.executePendingBindings();

            List<ProductImage> images = mViewModel.getProductLiveData().getValue().get(position).getImages();
            if (images != null && images.size() > 0)
                Glide.with(mViewModel.getApplication()).load(images.get(0).getImageURL()).into(mItemListBinding.imageViewProductImage);
        }
    }
}
