package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentSettingsBinding
import com.jaguiler.reversi.model.ReversiViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var boardcolor = 1
    private var playercolor = 1

    private val sharedViewModel: ReversiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.applysettingsButton.setOnClickListener {
            sharedViewModel.setBoardColor(boardcolor)
            sharedViewModel.setPlayerColor(playercolor)
            sharedViewModel.setSettingsBool(1)
            it.findNavController().navigate(R.id.action_settingsFragment_to_titleFragment)
        }

        binding.boardcolorRadioGroup.check(binding.greenRadioButton.id)
        binding.boardcolorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            boardcolor = when(checkedId) {
                R.id.green_radioButton -> 1
                R.id.brown_radioButon -> 2
                else -> -1
            }
        }

        binding.playercolorRadioGroup.check(binding.blackRadioButton.id)
        binding.playercolorRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            playercolor = when(checkedId) {
                R.id.white_radioButton -> 2
                R.id.black_radioButton -> 1
                else -> -1
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}