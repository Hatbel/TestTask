package com.example.quiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.quiz.R
import com.example.quiz.viewModels.ResultViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ResultFragment : Fragment() {
    private lateinit var tryAgainButton: Button
    private lateinit var result: TextView
    private val viewModel by viewModel<ResultViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val currentView = inflater.inflate(R.layout.fragment_result, null)

        tryAgainButton = currentView.findViewById(R.id.tryAgain_button)
        result = currentView.findViewById(R.id.result_textView)
        result.text = viewModel.result
        tryAgainButton.setOnClickListener {
            viewModel.clear()
            NavHostFragment.findNavController(this).navigate(R.id.fragment_start)
        }

        return currentView
    }

}