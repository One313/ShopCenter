package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowProductItemBinding;
import com.example.shopcenter.viewmodel.StrategyProductViewModel;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends RecyclerView
        .Adapter<ProductAdapter.ProductItemHolder> {

    private LifecycleOwner mOwner;
    private StrategyProductViewModel mStrategyProductViewModel;

    public ProductAdapter(LifecycleOwner owner,
                          StrategyProductViewModel strategyProductViewModel) {
        mOwner = owner;
        mStrategyProductViewModel = strategyProductViewModel;
    }

    @NonNull
    @Override
    public ProductItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mStrategyProductViewModel.getApplication());
        RowProductItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.row_product_item,
                        parent, false);
        return new ProductItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mStrategyProductViewModel.getListSize();
    }


    class ProductItemHolder extends RecyclerView.ViewHolder {

        private RowProductItemBinding mRowProductItemBinding;

        public ProductItemHolder(@NonNull RowProductItemBinding binding) {
            super(binding.getRoot());
            mRowProductItemBinding = binding;
            mRowProductItemBinding.setLifecycleOwner(mOwner);
            mRowProductItemBinding.setStrategyProductViewModel(mStrategyProductViewModel);
        }

        public void bind(int position) {
            mRowProductItemBinding.setPosition(position);
            mRowProductItemBinding.executePendingBindings();

            Picasso.get()
                    .load(mStrategyProductViewModel.getProductItems().get(position).getImages()[0].getSrc())
                    .resize(0,100)
                    .placeholder(R.drawable.ic_navigation_bottom_cart)
                    .into(mRowProductItemBinding.imageViewRowLatest);
        }
    }
}
