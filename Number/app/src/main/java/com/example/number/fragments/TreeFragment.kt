package com.example.number.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.number.R
import com.example.number.databinding.FragmentTreeBinding
import com.example.number.viewmodels.TreeViewModel
import com.example.number.viewmodels.states.TreeState
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
        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                is TreeState.TreeFirstOpen -> {
                    showGoal(getString(R.string.treeDescription))
                }
                is TreeState.Leafs -> {
                    val drawable =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_binary_tree, it.theme)
                    binding.tree.setImageDrawable(drawable)
                }
                is TreeState.LeafsAll -> {
                    val drawable =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_binary_tree, it.theme)
                    binding.tree.setImageDrawable(drawable)
                    val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
                    builder.setTitle(getString(R.string.end))
                    builder.setMessage(
                        getString(R.string.yourTime) + viewModel.getPlayTime() + getString(
                            R.string.endDescription
                        )
                    )
                    builder.setNegativeButton(getString(R.string.confirm)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = builder.create()
                    val message: TextView? = view?.findViewById(android.R.id.message)
                    val customFont = Typeface.createFromAsset(activity?.assets, "montserrat.ttf")
                    message?.typeface = customFont
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alertDialog.show()
                }
            }
        })
        binding.showRulesButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
            builder.setTitle(getString(R.string.goal))
            builder.setMessage(getString(R.string.goalDescription))
            builder.setNegativeButton(getString(R.string.confirm)) { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            val message: TextView? = view?.findViewById(android.R.id.message)
            val customFont = Typeface.createFromAsset(activity?.assets, "montserrat.ttf")
            message?.typeface = customFont
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }
        return binding.root
    }
}