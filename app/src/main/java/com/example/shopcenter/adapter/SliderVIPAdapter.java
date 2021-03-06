package com.example.shopcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.shopcenter.R;
import com.example.shopcenter.databinding.SliderItemBinding;
import com.example.shopcenter.utillity.ImageConverterResource;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderVIPAdapter extends
        SliderViewAdapter<SliderVIPAdapter.SliderAdapterVH2> {

    private Context mContext;
    List<String> stringsResource = new ArrayList<>();


    public SliderVIPAdapter(Context context) {
        mContext = context;
        ImageConverterResource imageConverterResource = new ImageConverterResource();
        stringsResource = imageConverterResource.getAllImage();
    }

    @Override
    public SliderAdapterVH2 onCreateViewHolder(ViewGroup parent) {
        SliderItemBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.slider_item,
                parent,
                false
        );
        return new SliderVIPAdapter.SliderAdapterVH2(itemListBinding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH2 viewHolder, int position) {
        viewHolder.onBind(position, mContext);
    }

    @Override
    public int getCount() {
        return 5;
    }

    class SliderAdapterVH2 extends SliderViewAdapter.ViewHolder {
        SliderItemBinding mSliderItemBinding;

        public SliderAdapterVH2(SliderItemBinding sliderItemBinding) {
            super(sliderItemBinding.getRoot());
            mSliderItemBinding = sliderItemBinding;
        }

        public void onBind(int position, Context context) {
            String imageUrl = stringsResource.get(position);
            if (imageUrl != null)
                Glide.with(context).load(imageUrl).into(mSliderItemBinding.imageProduct);
        }
    }
}
