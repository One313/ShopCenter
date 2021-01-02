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
        val sh = Shipping("majid", "moharami", "scs",
                "iran", "tehran", "varamin", "12344")
        val customer = Customer("majid11111@gmail.com", "majid",
                "moharami", sh)
        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
      //  mViewModel!!.insert(com.example.digikala.data.database.entity.Customer(customer.mFirstName, customer.mLastName, customer.mEmail))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoadUserBinding.inflate(inflater, container, false)
        if (  mViewModel?.getCustomersDatabase() == null || mViewModel!!.getCustomersDatabase()?.isEmpty() == true){
          //  Navigation.findNavController(binding.root).navigate(LoadUserFragmentDirections.actionNavMyDigikalaFragmentToNavMyDigikalaFragment1())
        }else{
           // Navigation.findNavController(binding.root).navigate(LoadUserFragmentDirections.actionNavMyDigikalaFragmentToUserInfoFragment())
        }
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