package com.example.assignment16

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assignment16.base.BaseFragment
import com.example.assignment16.databinding.FragmentRegisterBinding
import com.example.assignment16.network.Resource
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun getViewModel() = RegisterViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.registerResponse.collect {
                when (it) {
                    Resource.Failure -> {
                        Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                    }

                    Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                        val email = binding.etMail.text.toString().trim()
                        val password = binding.etPass.text.toString().trim()
                        setFragmentResult(
                            "userInfo",
                            bundleOf("email" to email, "password" to password)
                        )
                        findNavController().navigateUp()
                    }
                }
            }
        }

        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvRegisterReg.setOnClickListener {
            viewModel.register(
                binding.etMail.text.toString().trim(),
                binding.etPass.text.toString().trim(),
            )
        }
    }
}
