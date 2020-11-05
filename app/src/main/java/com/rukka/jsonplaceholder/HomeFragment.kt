package com.rukka.jsonplaceholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rukka.jsonplaceholder.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.textRequireAPI.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOverViewFragment())
        }
        return binding.root
    }

}