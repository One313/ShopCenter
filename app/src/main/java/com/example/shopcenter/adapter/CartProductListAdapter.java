package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopcenter.R;
import com.example.shopcenter.data.database.entity.CartProduct;
import com.example.shopcenter.data.model.ProductImage;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.databinding.CartProductItemBinding;
import com.example.shopcenter.utillity.DeleteProductHelper;
import com.example.shopcenter.viewmodel.CartFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.CartProductHolder> {

    private List<CartProduct> mCartProductList = new ArrayList<>();
    private LiveData<List<Product>> mProductLiveData;
    private LifecycleOwner mOwner;
    private CartFragmentViewModel mViewModel;

    public CartProductListAdapter(CartFragmentViewModel viewModel, LifecycleOwner owner) {
        mViewModel = viewModel;
        mCartProductList = viewModel.getCartProducts();
        mOwner = owner;
    }

    @NonNull
    @Override
    public CartProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartProductItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cart_product_item,
                parent,
                false
        );
        return new CartProductListAdapter.CartProductHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductHolder holder, int position) {
        holder.onBind2(position);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getCartProducts().size();
    }

    class CartProductHolder extends RecyclerView.ViewHolder {
        private CartProductItemBinding mBinding;

        public CartProductHolder(CartProductItemBinding cartProductItemBinding) {
            super(cartProductItemBinding.getRoot());
            mBinding = cartProductItemBinding;
            mBinding.setViewModel(mViewModel);


            mViewModel.getDeleteProductHelperMutableLiveData().observe(mOwner, new Observer<DeleteProductHelper>() {
                @Override
                public void onChanged(DeleteProductHelper deleteProductHelper) {
                    if (deleteProductHelper == DeleteProductHelper.SUBMISSION) {
                        mBinding.imageViewSubtrack.setImageResource(R.drawable.ic_subtrack_product);
                    } else
                        mBinding.imageViewSubtrack.setImageResource(R.drawable.ic_delete_product);
                }
            });
        }

        public void onBind2(int position) {
            mBinding.setPosition(position);
            mBinding.executePendingBindings();

            List<ProductImage> images = mViewModel.getProducts().getValue().get(position).getImages();
            if (images != null && images.size() > 0)
                Glide.with(mViewModel.getApplication()).load(images.get(0).getImageURL()).into(mBinding.imageViewProduct);
        }
    }


}
