package com.example.assignment16.login

import android.content.Context
import android.os.Bundle
import android.os.SharedMemory
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assignment16.base.BaseFragment
import com.example.assignment16.databinding.FragmentLoginBinding
import com.example.assignment16.network.Resource
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("userInfo") { _, bundle ->
            binding.etUsername.setText(bundle.getString("email"))
            binding.etPassword.setText(bundle.getString("password"))
        }

        val sharedPreferences =
            requireActivity().getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val isAuth = sharedPreferences.getBoolean("isAuthorized", false)


        if (isAuth) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                    sharedPreferences.getString(
                        "email",
                        ""
                    )!!
                )
            )
        }

        lifecycleScope.launch {
            viewModel.loginResponse.collect {
                when (it) {
                    Resource.Failure -> {
                        Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Resource.Success -> {
                        val email = binding.etUsername.text.toString().trim()

                        editor.putBoolean("isAuthorized", true)
                        editor.putString("email", email)
                        editor.putString("token", it.value.token)
                        editor.commit()

                        findNavController().navigate(
                            LoginFragmentDirections
                                .actionLoginFragmentToHomeFragment(email)
                        )
                        Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT)
                            .show()
                        viewModel.resetState()
                    }

                    Resource.Loading -> {

                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (isValidEmail(email) && password != "")
                viewModel.login(email, password)
            else Toast.makeText(requireContext(), "Please enter valid input", Toast.LENGTH_SHORT)
                .show()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
}