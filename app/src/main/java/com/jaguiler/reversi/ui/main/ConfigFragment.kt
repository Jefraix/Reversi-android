package com.jaguiler.reversi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {

    private var _binding: FragmentConfigBinding? = null
    private val binding get() = _binding!!

    private val difficultyList = listOf(
            Difficulty("Easy", 1),
            Difficulty("Normal", 2),
            Difficulty("Hard", 3)
    )
    private var difficultySetting = -1 // 1 = easy, 2 = normal, 3 = hard

    private var gameSettings = intArrayOf(1,1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigBinding.inflate(inflater, container, false)
        binding.difficultyRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.difficultyRecyclerView.apply {
            adapter = DifficultyAdapter(difficultyList)
        }

        arguments?.let { args ->
            val safeArgs = ConfigFragmentArgs.fromBundle(args)
            gameSettings[0] = safeArgs.gameSettings[0]
            gameSettings[1] = safeArgs.gameSettings[0]
        }
    }

    /**
     * Data class to hold difficulty values for recycler view
     */
    data class Difficulty(val text: String, val index: Int)

    /**
     * View Holder
     */
    private inner class DifficultyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var difficulty: Difficulty
        private val diffTextView: TextView = itemView.findViewById(R.id.term_textView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            difficultySetting = difficulty.index
        }

        fun bind(diff: Difficulty) {
            this.difficulty = diff
            diffTextView.text = diff.toString()
        }

    }

    /**
     * Adapter
     */
    private inner class DifficultyAdapter(private val difficulties: List<Difficulty>) : RecyclerView.Adapter<DifficultyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DifficultyViewHolder {
            val view = layoutInflater.inflate(R.layout.difficulty_recycler_item, parent, false)
            return DifficultyViewHolder(view)
        }

        override fun onBindViewHolder(holder: DifficultyViewHolder, position: Int) {
            holder.bind(difficulties[position])
        }

        override fun getItemCount() = difficulties.size


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}