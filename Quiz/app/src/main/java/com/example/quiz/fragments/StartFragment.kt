package com.example.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.example.quiz.R

class StartFragment : Fragment() {

    private lateinit var startButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val currentView = inflater.inflate(R.layout.fragment_start, null)
        startButton = currentView.findViewById(R.id.start_button)
        startButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.fragment_quiz)
        }
        return currentView
    }


}