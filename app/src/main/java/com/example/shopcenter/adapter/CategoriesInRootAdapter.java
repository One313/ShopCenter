package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowRecyclerViewCategoriesRootBinding;
import com.example.shopcenter.viewmodel.CategoriesViewModel;

public class CategoriesInRootAdapter extends RecyclerView.Adapter<CategoriesInRootAdapter.CategoryInRootHolder> {

    private final int mParent;
    private final LifecycleOwner mOwner;
    private final CategoriesViewModel mCategoriesViewModel;

    public CategoriesInRootAdapter(int parent, LifecycleOwner owner,
                                   CategoriesViewModel categoriesViewModel) {
        mParent = parent;
        mOwner = owner;
        mCategoriesViewModel = categoriesViewModel;
    }

    @NonNull
    @Override
    public CategoryInRootHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCategoriesViewModel.getApplication());
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
        return mCategoriesViewModel.getListSize(mParent);
    }

    class CategoryInRootHolder extends RecyclerView.ViewHolder {

        private RowRecyclerViewCategoriesRootBinding mCategoriesRootBinding;

        public CategoryInRootHolder(
                @NonNull RowRecyclerViewCategoriesRootBinding binding) {
            super(binding.getRoot());
            mCategoriesRootBinding = binding;
            mCategoriesRootBinding.setParent(mParent);
            mCategoriesRootBinding.setLifecycleOwner(mOwner);
            mCategoriesRootBinding.setCategoriesViewModel(mCategoriesViewModel);

            mCategoriesRootBinding.recyclerViewCategoriesRow.setLayoutManager(
                    new LinearLayoutManager(
                            mCategoriesViewModel.getApplication(),
                            LinearLayoutManager.HORIZONTAL,
                            true)
            );
        }

        public void bind(int position) {
            mCategoriesRootBinding.setPosition(position);
            mCategoriesRootBinding.executePendingBindings();

            int parent = mCategoriesViewModel.getCategories(mParent).get(position).getId();
            mCategoriesViewModel.getCategoriesLiveData(1, parent)
                    .observe(mOwner, categories -> {

                        mCategoriesViewModel.setCategories(parent, categories);
                        mCategoriesRootBinding.recyclerViewCategoriesRow
                                .setAdapter(
                                        new CategoriesAdapter(parent, mOwner, mCategoriesViewModel));
                    });

            mCategoriesViewModel.getCategorySelectedLiveData()
                    .observe(mOwner, category -> {

                        mCategoriesViewModel.setCategorySubject(category);
                        // TODO: ??
                    });
        }
    }
}
