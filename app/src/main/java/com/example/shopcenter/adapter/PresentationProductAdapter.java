package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowPresentationBinding;
import com.example.shopcenter.databinding.RowPresentationFirstPageBinding;
import com.example.shopcenter.databinding.RowPresentationLastPageBinding;
import com.example.shopcenter.viewmodel.LatestProductsViewModel;
import com.example.shopcenter.viewmodel.StrategyProductViewModel;
import com.squareup.picasso.Picasso;

public class PresentationProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PRESENTATION_SIZE = 7;
    private LifecycleOwner mOwner;
    private StrategyProductViewModel mStrategyProductViewModel;

    public PresentationProductAdapter(LifecycleOwner owner,
                                      StrategyProductViewModel strategyProductViewModel) {
        mOwner = owner;
        mStrategyProductViewModel = strategyProductViewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mStrategyProductViewModel.getApplication());
        switch (viewType) {
            case 0:
                RowPresentationFirstPageBinding firstPageBinding =
                        DataBindingUtil.inflate(inflater, R.layout.row_presentation_first_page,
                                parent, false);
                return new FirstPagePresentationHolder(firstPageBinding);
            case 2:
                RowPresentationLastPageBinding lastPageBinding =
                        DataBindingUtil.inflate(inflater, R.layout.row_presentation_last_page,
                                parent, false);
                return new LastPagePresentationHolder(lastPageBinding);
            default:
                RowPresentationBinding binding =
                        DataBindingUtil.inflate(inflater, R.layout.row_presentation,
                                parent, false);
                return new PresentationHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PresentationHolder)
            ((PresentationHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return PRESENTATION_SIZE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else if (position == PRESENTATION_SIZE - 1) return 2;
        else return 1;
    }

    class FirstPagePresentationHolder extends RecyclerView.ViewHolder {

        public FirstPagePresentationHolder(@NonNull RowPresentationFirstPageBinding binding) {
            super(binding.getRoot());
            binding.setLifecycleOwner(mOwner);
            binding.setStrategyProductViewModel(mStrategyProductViewModel);
        }
    }

    class PresentationHolder extends RecyclerView.ViewHolder {

        private final RowPresentationBinding mRowPresentationBinding;

        public PresentationHolder(@NonNull RowPresentationBinding binding) {
            super(binding.getRoot());
            mRowPresentationBinding = binding;
            mRowPresentationBinding.setLifecycleOwner(mOwner);
            mRowPresentationBinding.setStrategyProductViewModel(mStrategyProductViewModel);
        }

        public void bind(int position) {
            mRowPresentationBinding.setPosition(position - 1);
            mRowPresentationBinding.executePendingBindings();

            Picasso.get()
                    .load(mStrategyProductViewModel.getProductItems().get(position - 1).getImages()[0].getSrc())
                    .placeholder(R.drawable.ic_navigation_bottom_cart)
                    .into(mRowPresentationBinding.imageViewRowPresentation);
        }
    }

    class LastPagePresentationHolder extends RecyclerView.ViewHolder {

        public LastPagePresentationHolder(@NonNull RowPresentationLastPageBinding binding) {
            super(binding.getRoot());
            binding.setLifecycleOwner(mOwner);
            binding.setStrategyProductViewModel(mStrategyProductViewModel);
        }
    }
}
