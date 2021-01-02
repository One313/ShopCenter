package com.example.digikala.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digikala.R;
import com.example.digikala.databinding.FragmentMyDigikalaBinding;

public class MyDigikalaFragment extends Fragment {

    private FragmentMyDigikalaBinding mBinding;

    public static MyDigikalaFragment newInstance() {
        MyDigikalaFragment fragment = new MyDigikalaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_digikala, container, false);
        return mBinding.getRoot();
    }
}