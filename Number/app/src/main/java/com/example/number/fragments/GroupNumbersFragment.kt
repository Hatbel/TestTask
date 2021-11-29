package com.example.number.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.number.R
import com.example.number.adapters.BinaryNumberComparator
import com.example.number.adapters.BinaryNumbersAdapter
import com.example.number.databinding.FragmentGroupNumbersBinding
import com.example.number.interfaces.ClickListener
import com.example.number.viewmodels.GroupNumbersViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GroupNumbersFragment : Fragment(), ClickListener {

    private val viewModel by viewModel<GroupNumbersViewModel>()
    private val pagingAdapter by lazy { BinaryNumbersAdapter(BinaryNumberComparator, this) }

    private lateinit var binding: FragmentGroupNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_group_numbers, container, false)
        binding.binaryNumbersRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pagingAdapter
        }
        binding.binaryNumbersRecyclerview.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.binaryNumbersRecyclerview.adapter = pagingAdapter
        lifecycleScope.launch {
            viewModel.binaryNumbers.collectLatest { source -> pagingAdapter.submitData(source) }
        }

        return binding.root
    }

    override fun onCellClickListener(position: Int) {
        /*viewModel.saveGroupId(position)
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.groupNumbersFragment)*/
    }
}