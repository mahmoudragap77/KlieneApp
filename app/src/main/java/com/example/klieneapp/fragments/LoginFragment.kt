package com.example.klieneapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.klieneapp.R
import com.example.klieneapp.ShoppingActivity
import com.example.klieneapp.databinding.FragmentLoginBinding
import com.example.klieneapp.dialouge.setUpBottomSheetDialog
import com.example.klieneapp.util.Resource
import com.example.klieneapp.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.apply {
            Login.setOnClickListener {
                val email = loginEmail.text.toString().trim()
                val password = loginPassword.text.toString()
                viewModel.login(email, password)
            }
        }

        binding.forgotPassword.setOnClickListener {
            setUpBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect {
                when (it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        Snackbar.make(
                            requireView(),
                            "Reset link was sent to your email",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    is Resource.Error<*> ->
                        Snackbar.make(requireView(), "Error: ${it.message}", Snackbar.LENGTH_LONG)
                            .show()

                    is Resource.Unspecified<*> -> TODO()
                }

                lifecycleScope.launchWhenStarted {
                    viewModel.login.collect {
                        when (it) {
                            is Resource.Loading -> {
                                binding.Login.startAnimation()
                            }

                            is Resource.Success -> {
                                binding.Login.revertAnimation()
                                Intent(
                                    requireActivity(),
                                    ShoppingActivity::class.java
                                ).also { intent ->
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)

                                }
                            }

                            is Resource.Error -> {
                                binding.Login.revertAnimation()
                            }

                            else -> Unit
                        }
                    }
                }

            }

        }
    }
}