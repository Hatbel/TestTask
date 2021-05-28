package com.example.quiz.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.quiz.R
import com.example.quiz.repositories.ScreenState
import com.example.quiz.viewModels.QuizViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.viewmodel.ext.android.viewModel


class QuizFragment : Fragment() {

    private lateinit var answer1: Button
    private lateinit var answer2: Button
    private lateinit var answer3: Button
    private lateinit var answer4: Button
    private lateinit var nextQuestion: FloatingActionButton
    private lateinit var question: TextView
    private val viewModel by viewModel<QuizViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_quiz, null)
        answer1 = currentView.findViewById(R.id.firstQuestion_button)
        answer2 = currentView.findViewById(R.id.secondQuestion_button)
        answer3 = currentView.findViewById(R.id.thirdQuestion_button)
        answer4 = currentView.findViewById(R.id.fourthQuestion_button)
        question = currentView.findViewById(R.id.question_textView)
        nextQuestion = currentView.findViewById(R.id.next_button)
        nextQuestion.setOnClickListener {
            viewModel.loadQuiz()
        }
        answer1.setOnClickListener {
            answer1.setBackgroundColor(resources.getColor(R.color.red))
            answer2.isClickable = false
            answer3.isClickable = false
            answer4.isClickable = false
            nextQuestion.visibility = View.VISIBLE
        }
        answer2.setOnClickListener {
            answer2.setBackgroundColor(resources.getColor(R.color.red))
            answer1.isClickable = false
            answer3.isClickable = false
            answer4.isClickable = false
            nextQuestion.visibility = View.VISIBLE
        }
        answer3.setOnClickListener {
            answer3.setBackgroundColor(resources.getColor(R.color.red))
            answer1.isClickable = false
            answer3.isClickable = false
            answer4.isClickable = false
            nextQuestion.visibility = View.VISIBLE
        }
        answer4.setOnClickListener {
            answer4.setBackgroundColor(resources.getColor(R.color.red))
            answer1.isClickable = false
            answer3.isClickable = false
            answer4.isClickable = false
            nextQuestion.visibility = View.VISIBLE
        }
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ScreenState.Finish -> {
                    NavHostFragment.findNavController(this).navigate(R.id.fragment_result)
                }
                is ScreenState.NextQuestion -> {
                    answer1.setBackgroundColor(resources.getColor(R.color.purple_500))
                    answer2.setBackgroundColor(resources.getColor(R.color.purple_500))
                    answer3.setBackgroundColor(resources.getColor(R.color.purple_500))
                    answer4.setBackgroundColor(resources.getColor(R.color.purple_500))
                    answer1.text = it.quiz.answers[0].answerText
                    answer2.text = it.quiz.answers[1].answerText
                    answer3.text = it.quiz.answers[2].answerText
                    answer4.text = it.quiz.answers[3].answerText
                    question.text = it.quiz.questionText
                    answer1.isClickable = true
                    answer2.isClickable = true
                    answer3.isClickable = true
                    answer4.isClickable = true
                    viewModel.trueButton()
                    answer1.setOnClickListener {
                        answer1.setBackgroundColor(resources.getColor(R.color.red))
                        answer2.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                    }
                    answer2.setOnClickListener {
                        answer2.setBackgroundColor(resources.getColor(R.color.red))
                        answer1.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                    }
                    answer3.setOnClickListener {
                        answer3.setBackgroundColor(resources.getColor(R.color.red))
                        answer1.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                    }
                    answer4.setOnClickListener {
                        answer4.setBackgroundColor(resources.getColor(R.color.red))
                        answer1.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                    }
                    nextQuestion.visibility = View.GONE
                }
                is ScreenState.Error -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
                is ScreenState.FirstButton -> {
                    question.text = it.quiz.questionText
                    answer1.text = it.quiz.answers[0].answerText
                    answer2.text = it.quiz.answers[1].answerText
                    answer3.text = it.quiz.answers[2].answerText
                    answer4.text = it.quiz.answers[3].answerText
                    answer1.setOnClickListener {
                        answer1.setBackgroundColor(resources.getColor(R.color.green))
                        answer2.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                        viewModel.countTrue()
                        Log.e("asdasdasdas","djfksdjfsdj")
                    }
                }
                is ScreenState.SecondButton -> {
                    question.text = it.quiz.questionText
                    answer1.text = it.quiz.answers[0].answerText
                    answer2.text = it.quiz.answers[1].answerText
                    answer3.text = it.quiz.answers[2].answerText
                    answer4.text = it.quiz.answers[3].answerText
                    answer2.setOnClickListener {
                        answer2.setBackgroundColor(resources.getColor(R.color.green))
                        answer1.isClickable = false
                        answer3.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                        viewModel.countTrue()
                    }
                }
                is ScreenState.ThirdButton -> {
                    question.text = it.quiz.questionText
                    answer1.text = it.quiz.answers[0].answerText
                    answer2.text = it.quiz.answers[1].answerText
                    answer3.text = it.quiz.answers[2].answerText
                    answer4.text = it.quiz.answers[3].answerText
                    answer3.setOnClickListener {
                        answer3.setBackgroundColor(resources.getColor(R.color.green))
                        answer2.isClickable = false
                        answer1.isClickable = false
                        answer4.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                        viewModel.countTrue()
                    }
                }
                is ScreenState.FourthButton -> {
                    question.text = it.quiz.questionText
                    answer1.text = it.quiz.answers[0].answerText
                    answer2.text = it.quiz.answers[1].answerText
                    answer3.text = it.quiz.answers[2].answerText
                    answer4.text = it.quiz.answers[3].answerText
                    answer4.setOnClickListener {
                        answer4.setBackgroundColor(resources.getColor(R.color.green))
                        answer2.isClickable = false
                        answer3.isClickable = false
                        answer1.isClickable = false
                        nextQuestion.visibility = View.VISIBLE
                        viewModel.countTrue()
                    }
                }
                else -> {
                    showError(resources.getString(R.string.unknownError)).show()
                }
            }
        })
        return currentView
    }
    fun Fragment.showError(message: String): AlertDialog =
        AlertDialog.Builder(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton("OK", null)
            .create()

}