package com.example.assignment16.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.assignment16.base.BaseFragment
import com.example.assignment16.Inflater
import com.example.assignment16.Network.LoginApi
import com.example.assignment16.Network.Resource
import com.example.assignment16.databinding.FragmentLoginBinding
import com.example.assignment16.repository.LoginRepository
import java.util.zip.Inflater


class LoginFragment(inflate: Inflater) :
    BaseFragment<LoginViewModel, FragmentLoginBinding, LoginRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

                viewModel.loginResponse.observe(
            viewLifecycleOwner,Observer{
                when(it){
                    is Resource.Success<*> ->{
                        Toast.makeText(requireContext(),it.toString, Toast.LENGTH_LONG).show()
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

        binding.btnLogin.setOnClickListener{
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            viewModel.login(username, password)
        }
    }


    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = LoginRepository(remoteDataSource.buildApi(LoginApi::class.java))


}