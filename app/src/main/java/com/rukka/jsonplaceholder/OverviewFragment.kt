package com.rukka.jsonplaceholder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.over_view_fragment, container, false)
    }
}