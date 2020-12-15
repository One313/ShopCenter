package com.example.shopcenter.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.RowRecyclerViewCategoriesBinding;
import com.example.shopcenter.viewmodel.CategoriesViewModel;
import com.squareup.picasso.Picasso;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder> {

    private int mParent;
    private LifecycleOwner mOwner;
    private CategoriesViewModel mCategoriesViewModel;

    public CategoriesAdapter(int parent, LifecycleOwner owner,
                             CategoriesViewModel categoriesViewModel) {
        mParent = parent;
        mOwner = owner;
        mCategoriesViewModel = categoriesViewModel;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCategoriesViewModel.getApplication());
        RowRecyclerViewCategoriesBinding binding =
                DataBindingUtil.inflate(
                        inflater, R.layout.row_recycler_view_categories,
                        parent, false);
        return new CategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCategoriesViewModel.getListSize(mParent);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        private RowRecyclerViewCategoriesBinding mCategoriesBinding;

        public CategoryHolder(@NonNull RowRecyclerViewCategoriesBinding binding) {
            super(binding.getRoot());
            mCategoriesBinding = binding;
            mCategoriesBinding.setParent(mParent);
            mCategoriesBinding.setLifecycleOwner(mOwner);
            mCategoriesBinding.setCategoriesViewModel(mCategoriesViewModel);
        }

        public void bind(int position) {
            mCategoriesBinding.setPosition(position);
            mCategoriesBinding.executePendingBindings();

            Picasso.get()
                    .load(mCategoriesViewModel.getCategories(mParent).get(position).getImageCategory().getSrc())
                    .resize(0, 100)
                    .placeholder(R.drawable.ic_navigation_bottom_cart)
                    .into(mCategoriesBinding.imageViewPhotoCategory);
        }
    }
}
