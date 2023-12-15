package com.example.assignment16.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assignment16.base.BaseFragment
import com.example.assignment16.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private val args: HomeFragmentArgs by navArgs()

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =
            requireContext().getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        binding.email.text = args.email
        binding.logout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            findNavController().navigateUp()
        }
    }
}
