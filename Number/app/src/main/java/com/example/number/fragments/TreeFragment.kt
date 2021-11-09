package com.example.number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.number.R
import com.example.number.databinding.FragmentTreeBinding
import com.example.number.viewmodels.TreeViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TreeFragment : Fragment() {

    private lateinit var binding: FragmentTreeBinding
    private val viewModel by viewModel<TreeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tree, container, false)
        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_binary_tree, viewModel.getTheme())
        binding.tree.setImageDrawable(drawable)
        return binding.root
    }
}