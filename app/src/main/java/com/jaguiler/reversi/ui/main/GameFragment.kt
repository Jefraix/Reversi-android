package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var winner = false
    private var moves = 0

    private var gameSettings = intArrayOf(4,1)
    private var difficulty = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.showresultsButton.setOnClickListener {
            val actionEval =
                    GameFragmentDirections.actionGameFragmentToResultsFragment(winner, moves)
            Navigation.findNavController(it).navigate(actionEval)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val safeArgs = GameFragmentArgs.fromBundle(args)

            gameSettings[0] = safeArgs.settings[0] // Board Size
            gameSettings[1] = safeArgs.settings[1] // Player piece color
            difficulty = safeArgs.difficulty // Game difficulty
        }

        binding.currdiffTextView.text = difficulty.toString()
        binding.currboardsizeTextView.text = gameSettings[0].toString()
        binding.currplayercolorTextView.text = gameSettings[1].toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}