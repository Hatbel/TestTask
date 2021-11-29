package com.example.number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.number.R
import com.example.number.adapters.BinaryGroupsAdapter
import com.example.number.adapters.BinaryNumberGroupComparator
import com.example.number.databinding.FragmentBinaryNumbersBinding
import com.example.number.interfaces.ClickListener
import com.example.number.viewmodels.BinaryNumbersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class BinaryNumbersFragment : Fragment(), ClickListener {

    private val viewModel by viewModel<BinaryNumbersViewModel>()
    private val pagingAdapter by lazy { BinaryGroupsAdapter(BinaryNumberGroupComparator, this) }

    private lateinit var binding: FragmentBinaryNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_binary_numbers, container, false)
        binding.binaryGroupRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pagingAdapter
        }
        binding.binaryGroupRecyclerView.adapter = pagingAdapter
        lifecycleScope.launch {
            viewModel.binaryGroups.collectLatest { source -> pagingAdapter.submitData(source) }
        }

        return binding.root
    }

    override fun onCellClickListener(position: Int) {
        viewModel.saveGroupId(position)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.groupNumbersFragment)
    }
}