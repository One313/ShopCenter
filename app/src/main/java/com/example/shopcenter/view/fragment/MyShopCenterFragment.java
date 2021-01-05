package com.example.shopcenter.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.FragmentMyShopCenterBinding;
import com.example.shopcenter.viewmodel.CustomerViewModel;

public class MyShopCenterFragment extends Fragment {

    private FragmentMyShopCenterBinding mBinding;
    private CustomerViewModel mViewModel;

    public static MyShopCenterFragment newInstance() {
        MyShopCenterFragment fragment = new MyShopCenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        mViewModel.getIsEmailExistLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    Toast.makeText(getContext(), "ایمیل وارد شده قبلا ثبت شده", Toast.LENGTH_SHORT).show();
                } else {
                    MyShopCenterFragmentDirections.ActionMyShopCenterFragmentToPersonalInfoFragment action =
                            MyShopCenterFragmentDirections.actionMyShopCenterFragmentToPersonalInfoFragment(mBinding.editTextPersian.getText().toString());
                    Navigation.findNavController(mBinding.getRoot()).navigate(action);
                }
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            }
        });
    }

    //TODO: ..>
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_shop_center, container, false);
        mBinding.button.setOnClickListener(v -> {
            mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
            if (!(Patterns.EMAIL_ADDRESS.matcher(mBinding.editTextPersian.getText().toString()).matches())) {
                Toast.makeText(getContext(), "ایمیل وارد شده صحیح نیست", Toast.LENGTH_SHORT).show();
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            } else if (TextUtils.isEmpty(mBinding.editTextPersian.getText().toString())) {
                Toast.makeText(getContext(), "ایمیل خود را وارد کنید", Toast.LENGTH_SHORT).show();
                mBinding.progressBarLoadingFragment.setVisibility(View.INVISIBLE);
            } else {
                mViewModel.checkEmailExist(mBinding.editTextPersian.getText().toString());
            }
        });
        return mBinding.getRoot();
    }
}