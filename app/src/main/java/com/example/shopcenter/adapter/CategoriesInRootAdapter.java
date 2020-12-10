package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowRecyclerViewCategoriesRootBinding;
import com.example.shopcenter.viewmodel.CategoriesInRootViewModel;

public class CategoriesInRootAdapter extends RecyclerView.Adapter<CategoriesInRootAdapter.CategoryInRootHolder> {

    private LifecycleOwner mOwner;
    private CategoriesInRootViewModel mCategoriesInRootViewModel;

    public CategoriesInRootAdapter(LifecycleOwner owner, CategoriesInRootViewModel categoriesInRootViewModel) {
        mOwner = owner;
        mCategoriesInRootViewModel = categoriesInRootViewModel;
    }

    @NonNull
    @Override
    public CategoryInRootHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCategoriesInRootViewModel.getApplication());
        RowRecyclerViewCategoriesRootBinding binding =
                DataBindingUtil.inflate(
                        inflater, R.layout.row_recycler_view_categories_root,
                        parent, false);
        return new CategoryInRootHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryInRootHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCategoriesInRootViewModel.getListSize();
    }

    class CategoryInRootHolder extends RecyclerView.ViewHolder {

        private RowRecyclerViewCategoriesRootBinding mCategoriesRootBinding;

        public CategoryInRootHolder(@NonNull RowRecyclerViewCategoriesRootBinding binding) {
            super(binding.getRoot());
            mCategoriesRootBinding = binding;
            mCategoriesRootBinding.setLifecycleOwner(mOwner);
            mCategoriesRootBinding.setCategoriesInRootViewModel(mCategoriesInRootViewModel);
        }

        public void bind(int position) {
            mCategoriesRootBinding.setPosition(position);
            mCategoriesRootBinding.executePendingBindings();

            // TODO: handling recycler views in rows ...
        }
    }
}
