package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    private var _binding: TitleFragmentBinding? = null
    private val binding get() = _binding!!

    private var gameSettings = intArrayOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = TitleFragmentBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_configFragment)
        }
        binding.settingsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_settingsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            val safeArgs = TitleFragmentArgs.fromBundle(args)

            gameSettings[0] = safeArgs.settings[0] // Board Size
            gameSettings[1] = safeArgs.settings[1] // Player piece color

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}