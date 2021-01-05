package com.example.shopcenter.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.shopcenter.databinding.FragmentChoosingCustomerBinding
import com.example.shopcenter.viewmodel.CustomerViewModel

class ChoosingCustomerFragment : Fragment() {

    private var mViewModel: CustomerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentChoosingCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    // TODO: ..>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mViewModel?.getCustomersDatabase() == null || mViewModel!!.getCustomersDatabase()?.isEmpty() == true) {
            Navigation.findNavController(view).navigate(ChoosingCustomerFragmentDirections.actionChoosingCustomerFragment2ToMyShopCenterFragment())

        } else {
            Navigation.findNavController(view).navigate(ChoosingCustomerFragmentDirections.actionChoosingCustomerFragment2ToUserInfoFragment())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ChoosingCustomerFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}