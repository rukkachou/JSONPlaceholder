package com.rukka.jsonplaceholder.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rukka.jsonplaceholder.databinding.OverViewFragmentBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OverViewFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = GridLayoutManager(context, 4)
        val adapter = Adapter(Adapter.OnclickListener { property ->
            viewModel.displayDetailProperty(property)
        })
        binding.recyclerView.adapter = adapter
        viewModel.allData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer { property ->
                property?.let {
                    findNavController().navigate(OverviewFragmentDirections.actionOverViewFragmentToDetailFragment(property))
                    viewModel.displayDetailPropertyCompleted()
                }
            })
        return binding.root
    }
}