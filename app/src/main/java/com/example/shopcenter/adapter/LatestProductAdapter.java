package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowLatestProductBinding;
import com.example.shopcenter.viewmodel.LatestProductViewModel;
import com.squareup.picasso.Picasso;

public class LatestProductAdapter extends RecyclerView
        .Adapter<LatestProductAdapter.LatestProductHolder> {

    private LatestProductViewModel mLatestProductViewModel;
    private LifecycleOwner mOwner;

    public LatestProductAdapter(LifecycleOwner owner,
                                LatestProductViewModel latestProductViewModel) {
        mLatestProductViewModel = latestProductViewModel;
        mOwner = owner;
    }

    @NonNull
    @Override
    public LatestProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mLatestProductViewModel.getApplication());
        RowLatestProductBinding productBinding =
                DataBindingUtil.inflate(inflater, R.layout.row_latest_product,
                        parent, false);
        return new LatestProductHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestProductHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLatestProductViewModel.getListSize();
    }

    class LatestProductHolder extends RecyclerView.ViewHolder {

        private RowLatestProductBinding mRowLatestProductBinding;

        public LatestProductHolder(@NonNull RowLatestProductBinding rowLatestProductBinding) {
            super(rowLatestProductBinding.getRoot());
            mRowLatestProductBinding = rowLatestProductBinding;

            mRowLatestProductBinding.setLatestProductViewModel(mLatestProductViewModel);
            mRowLatestProductBinding.setLifecycleOwner(mOwner);
        }

        public void bind(int position) {
            mRowLatestProductBinding.setPosition(position);
            mRowLatestProductBinding.executePendingBindings();

            Picasso.get()
                    .load(mLatestProductViewModel.getProductItems().get(position).getImages()[0].getSrc())
                    .placeholder(R.drawable.ic_navigation_bottom_cart)
                    .into(mRowLatestProductBinding.imageViewRowLatest);
        }
    }
}
