package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.TitleFragmentBinding
import com.jaguiler.reversi.model.ReversiViewModel

class TitleFragment : Fragment() {

    private var _binding: TitleFragmentBinding? = null
    private val binding get() = _binding!!

    //private var gameSettings = intArrayOf(4,1)

    private val sharedViewModel: ReversiViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = TitleFragmentBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            /*
            val actionEval =
                    TitleFragmentDirections.actionTitleFragmentToConfigFragment(gameSettings)
            Navigation.findNavController(it).navigate(actionEval)
             */
            if(sharedViewModel.newSettings() == null) {
                sharedViewModel.setBoardSize(4)
                sharedViewModel.setPlayerColor(1)
                sharedViewModel.setDifficulty(1)
            }
            it.findNavController().navigate(R.id.action_titleFragment_to_configFragment)
        }
        binding.settingsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_settingsFragment)
        }

        return binding.root
    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val safeArgs = TitleFragmentArgs.fromBundle(args)

            gameSettings[0] = safeArgs.boardsize // Board Size
            gameSettings[1] = safeArgs.playercolor // Player piece color

        }
    }
    */

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}