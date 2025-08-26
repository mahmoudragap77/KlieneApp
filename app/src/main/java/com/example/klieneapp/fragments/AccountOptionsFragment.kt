package com.example.klieneapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.klieneapp.R
import com.example.klieneapp.databinding.FragmentAccountOptionsBinding

class AccountOptionsFragment : Fragment(R.layout.fragment_account_options) {
    lateinit var binding: FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountOptionsBinding.bind(view)

        binding.apply {
            reqisterButtonId.setOnClickListener {
                val action = AccountOptionsFragmentDirections.actionAccountOptionsFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
            loginButtonId.setOnClickListener {
                val action = AccountOptionsFragmentDirections.actionAccountOptionsFragmentToLoginFragment()
                findNavController().navigate(action)
            }

        }

    }

}