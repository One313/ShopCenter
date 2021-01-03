package com.example.digikala.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.digikala.data.model.customer.Customer
import com.example.digikala.data.model.customer.Shipping
import com.example.digikala.databinding.FragmentLoadUserBinding
import com.example.digikala.viewmodel.CustomerViewModel

class LoadUserFragment : Fragment() {


    private var mViewModel : CustomerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoadUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LoadUserFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}