package com.example.shopcenter.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.shopcenter.databinding.FragmentUserInfoBinding
import com.example.shopcenter.viewmodel.CustomerViewModel

class UserInfoFragment : Fragment() {

    private lateinit var mViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val c = mViewModel.getCustomers()?.get(0)
        if (c != null) {
            binding.textViewUsetName.text = c.mFistName + " " + c.mLastName
            binding.textViewUserEmail.text = c.mEmail
        }
        binding.imageViewNotifSetting.setOnClickListener {
            Navigation.findNavController(binding.root)
                    .navigate(UserInfoFragmentDirections.actionUserInfoFragmentToNotificationSettingFragment())
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                UserInfoFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}