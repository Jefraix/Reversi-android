package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private var winner = false
    private var moves = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        binding.newgameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultsFragment_to_titleFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val safeArgs = ResultsFragmentArgs.fromBundle(args)

            winner = safeArgs.winner // Board Size
            moves = safeArgs.moves // Player piece color
        }

        binding.winnerTextView.text = winner.toString()
        binding.movesTextView.text = moves.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}