package com.example.shopcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.shopcenter.R;
import com.example.shopcenter.data.model.poduct.Product;
import com.example.shopcenter.data.model.ProductImage;
import com.example.shopcenter.databinding.SliderItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context mContext;
    private List<ProductImage> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context, Product product) {
        mContext = context;
        mSliderItems = product.getImages();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        SliderItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.slider_item,
                parent,
                false
        );
        return new SliderAdapterVH(itemListBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        viewHolder.onBind(position, mContext);
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        SliderItemBinding mSliderItemBinding;

        public SliderAdapterVH(SliderItemBinding sliderItemBinding) {
            super(sliderItemBinding.getRoot());
            mSliderItemBinding = sliderItemBinding;
        }

        public void onBind(int position, Context context) {
            String imageUrl = mSliderItems.get(position).getImageURL();
            if (imageUrl != null)
                Glide.with(context).load(imageUrl).into(mSliderItemBinding.imageProduct);
        }
    }

}
