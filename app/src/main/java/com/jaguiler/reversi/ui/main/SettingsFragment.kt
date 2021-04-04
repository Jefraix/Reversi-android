package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var localSettings = intArrayOf(1,1)
    private var boardsize = -1
    private var playercolor = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.applysettingsButton.setOnClickListener {
            val actionEval =
                SettingsFragmentDirections.actionSettingsFragmentToTitleFragment(localSettings)
            Navigation.findNavController(it).navigate(actionEval)
        }

        binding.boardsizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            boardsize = when(checkedId) {
                R.id.fourby_radioButton -> 4
                R.id.eightby_radioButon -> 8
                else -> -1
            }
            localSettings[0] = boardsize
        }

        binding.playercolorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            playercolor = when(checkedId) {
                R.id.white_radioButton -> 1
                R.id.black_radioButton -> 2
                else -> -1
            }
            localSettings[1] = playercolor
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}