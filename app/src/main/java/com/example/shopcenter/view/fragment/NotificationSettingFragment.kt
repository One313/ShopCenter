package com.example.shopcenter.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shopcenter.databinding.FragmentNotificationSettingBinding
import com.example.shopcenter.viewmodel.NotificationSettingViewModel

class NotificationSettingFragment : Fragment() {

    private lateinit var mViewModel: NotificationSettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(NotificationSettingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentNotificationSettingBinding.inflate(inflater, container, false)
        binding.buttonSale.setOnClickListener {
            if (binding.switch15min.isChecked) {
                mViewModel.scheduleNotification(15)
            } else if (binding.switch30min.isChecked) {
                mViewModel.scheduleNotification(30)
            } else if (binding.switch40min.isChecked) {
                mViewModel.scheduleNotification(40)
            }
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                NotificationSettingFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}