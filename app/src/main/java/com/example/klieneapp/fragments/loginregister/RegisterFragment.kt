package com.example.klieneapp.fragments.loginregister

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.klieneapp.R
import com.example.klieneapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.klieneapp.util.Resource
import com.example.klieneapp.data.User
import com.example.klieneapp.util.RegisterValidation
import com.example.klieneapp.viewmodels.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doYouHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.apply {
            registerButtonId.setOnClickListener {
                val user = User(
                    firstName =firstName.text.toString().trim(),
                    lastName = lastName.text.toString().trim(),
                    email = email.text.toString().trim(),

                )
                val password = password.text.toString()
                viewModel.registerWithEmailAndPassword(user, password)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when(it) {
                    is Resource.Success -> {
                      Log.d("TAG", "onViewCreated: Success ${it.data}")
                        binding.registerButtonId.revertAnimation()
                    }

                    is Resource.Error -> {
                        Log.d("TAG", "onViewCreated: Error ${it.message}")
                        binding.registerButtonId.revertAnimation()

                    }

                    is Resource.Loading -> {
                        binding.registerButtonId.startAnimation()
                    }

                    else -> Unit
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.failedValidations) {
                    withContext(Dispatchers.Main){
                        binding.email.apply {
                            requestFocus()
                            error = validation.email.massage
                        }
                    }
                }
                if (validation.password is RegisterValidation.failedValidations) {
                    binding.password.apply {
                        requestFocus()
                        error = validation.password.massage
                    }
                }
            }
        }
    }


}