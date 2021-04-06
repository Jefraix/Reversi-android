package com.jaguiler.reversi.ui.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentGameBinding
import com.jaguiler.reversi.model.NumPair
import com.jaguiler.reversi.model.Reversi
import com.jaguiler.reversi.model.ReversiViewModel

class GameFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var winner = "No Winner"
    private var moves = 0
    private var playerTurn = true;

    private val sharedViewModel: ReversiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        binding.showresultsButton.setOnClickListener {
            sharedViewModel.setWinner(winner)
            sharedViewModel.setMoves(moves)
            it.findNavController().navigate(R.id.action_gameFragment_to_resultsFragment)
        }
        sharedViewModel.setWinner(winner)
        sharedViewModel.setMoves(moves)
        binding.gameoverTextView.visibility = View.INVISIBLE

        if(sharedViewModel.getBoardColor() == 1)
            binding.boardGridLayout.setBackgroundResource(R.drawable.reversigameboard)
        else {
            binding.boardGridLayout.setBackgroundResource(R.drawable.reversigameboard_brown)
        }

        sharedViewModel.setupReversiGame()
        setupOnClickListeners()
        animateBoard()
        updateUI()

        return binding.root
    }

    private fun setupOnClickListeners() {
        binding.cell00.setOnClickListener(this)
        binding.cell01.setOnClickListener(this)
        binding.cell02.setOnClickListener(this)
        binding.cell03.setOnClickListener(this)
        binding.cell04.setOnClickListener(this)
        binding.cell05.setOnClickListener(this)
        binding.cell06.setOnClickListener(this)
        binding.cell07.setOnClickListener(this)
        binding.cell10.setOnClickListener(this)
        binding.cell11.setOnClickListener(this)
        binding.cell12.setOnClickListener(this)
        binding.cell13.setOnClickListener(this)
        binding.cell14.setOnClickListener(this)
        binding.cell15.setOnClickListener(this)
        binding.cell16.setOnClickListener(this)
        binding.cell17.setOnClickListener(this)
        binding.cell20.setOnClickListener(this)
        binding.cell21.setOnClickListener(this)
        binding.cell22.setOnClickListener(this)
        binding.cell23.setOnClickListener(this)
        binding.cell24.setOnClickListener(this)
        binding.cell25.setOnClickListener(this)
        binding.cell26.setOnClickListener(this)
        binding.cell27.setOnClickListener(this)
        binding.cell30.setOnClickListener(this)
        binding.cell31.setOnClickListener(this)
        binding.cell32.setOnClickListener(this)
        binding.cell33.setOnClickListener(this)
        binding.cell34.setOnClickListener(this)
        binding.cell35.setOnClickListener(this)
        binding.cell36.setOnClickListener(this)
        binding.cell37.setOnClickListener(this)
        binding.cell40.setOnClickListener(this)
        binding.cell41.setOnClickListener(this)
        binding.cell42.setOnClickListener(this)
        binding.cell43.setOnClickListener(this)
        binding.cell44.setOnClickListener(this)
        binding.cell45.setOnClickListener(this)
        binding.cell46.setOnClickListener(this)
        binding.cell47.setOnClickListener(this)
        binding.cell50.setOnClickListener(this)
        binding.cell51.setOnClickListener(this)
        binding.cell52.setOnClickListener(this)
        binding.cell53.setOnClickListener(this)
        binding.cell54.setOnClickListener(this)
        binding.cell55.setOnClickListener(this)
        binding.cell56.setOnClickListener(this)
        binding.cell57.setOnClickListener(this)
        binding.cell60.setOnClickListener(this)
        binding.cell61.setOnClickListener(this)
        binding.cell62.setOnClickListener(this)
        binding.cell63.setOnClickListener(this)
        binding.cell64.setOnClickListener(this)
        binding.cell65.setOnClickListener(this)
        binding.cell66.setOnClickListener(this)
        binding.cell67.setOnClickListener(this)
        binding.cell70.setOnClickListener(this)
        binding.cell71.setOnClickListener(this)
        binding.cell72.setOnClickListener(this)
        binding.cell73.setOnClickListener(this)
        binding.cell74.setOnClickListener(this)
        binding.cell75.setOnClickListener(this)
        binding.cell76.setOnClickListener(this)
        binding.cell77.setOnClickListener(this)
    }

    private fun animateBoard() {
        val animator = ValueAnimator.ofFloat(0f, 1f)

        animator.addUpdateListener {
            val value = it.animatedValue as Float
            binding.boardGridLayout.alpha = value
        }

        animator.interpolator = DecelerateInterpolator()
        animator.duration = SHOW_BOARD_ANIM_DUR
        animator.start()
    }

    private fun animateGameOver() {
        binding.gameoverTextView.visibility = View.VISIBLE
        val animator = ValueAnimator.ofFloat(0f, 1f)

        animator.addUpdateListener {
            val value = it.animatedValue as Float
            binding.gameoverTextView.alpha = value
        }

        animator.interpolator = DecelerateInterpolator()
        animator.duration = SHOW_OVER_ANIM_DUR
        animator.start()
    }

    private fun updateUI() {
        binding.cell00.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,0)))
        binding.cell01.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,1)))
        binding.cell02.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,2)))
        binding.cell03.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,3)))
        binding.cell04.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,4)))
        binding.cell05.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,5)))
        binding.cell06.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,6)))
        binding.cell07.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(0,7)))

        binding.cell10.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,0)))
        binding.cell11.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,1)))
        binding.cell12.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,2)))
        binding.cell13.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,3)))
        binding.cell14.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,4)))
        binding.cell15.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,5)))
        binding.cell16.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,6)))
        binding.cell17.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(1,7)))

        binding.cell20.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,0)))
        binding.cell21.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,1)))
        binding.cell22.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,2)))
        binding.cell23.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,3)))
        binding.cell24.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,4)))
        binding.cell25.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,5)))
        binding.cell26.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,6)))
        binding.cell27.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(2,7)))

        binding.cell30.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,0)))
        binding.cell31.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,1)))
        binding.cell32.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,2)))
        binding.cell33.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,3)))
        binding.cell34.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,4)))
        binding.cell35.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,5)))
        binding.cell36.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,6)))
        binding.cell37.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(3,7)))

        binding.cell40.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,0)))
        binding.cell41.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,1)))
        binding.cell42.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,2)))
        binding.cell43.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,3)))
        binding.cell44.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,4)))
        binding.cell45.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,5)))
        binding.cell46.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,6)))
        binding.cell47.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(4,7)))

        binding.cell50.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,0)))
        binding.cell51.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,1)))
        binding.cell52.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,2)))
        binding.cell53.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,3)))
        binding.cell54.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,4)))
        binding.cell55.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,5)))
        binding.cell56.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,6)))
        binding.cell57.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(5,7)))

        binding.cell60.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,0)))
        binding.cell61.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,1)))
        binding.cell62.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,2)))
        binding.cell63.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,3)))
        binding.cell64.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,4)))
        binding.cell65.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,5)))
        binding.cell66.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,6)))
        binding.cell67.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(6,7)))

        binding.cell70.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,0)))
        binding.cell71.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,1)))
        binding.cell72.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,2)))
        binding.cell73.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,3)))
        binding.cell74.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,4)))
        binding.cell75.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,5)))
        binding.cell76.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,6)))
        binding.cell77.setImageResource(getImResByChar(sharedViewModel.getPosSymbol(7,7)))

    }

    private fun getImResByChar(sym: Char): Int {
        return if(sharedViewModel.getPlayerColor() == 1) {
            when (sym) {
                'o' -> R.drawable.whitepiece
                'x' -> R.drawable.blackpiece
                else -> R.drawable.empty
            }
        } else {
            when (sym) {
                'o' -> R.drawable.blackpiece
                'x' -> R.drawable.whitepiece
                else -> R.drawable.empty
            }
        }
    }

    private fun getCoord(imButn: ImageButton) = when(imButn.id) {
            R.id.cell_00 -> NumPair(0,0)
            R.id.cell_01 -> NumPair(0,1)
            R.id.cell_02 -> NumPair(0,2)
            R.id.cell_03 -> NumPair(0,3)
            R.id.cell_04 -> NumPair(0,4)
            R.id.cell_05 -> NumPair(0,5)
            R.id.cell_06 -> NumPair(0,6)
            R.id.cell_07 -> NumPair(0,7)
            R.id.cell_10 -> NumPair(1,0)
            R.id.cell_11 -> NumPair(1,1)
            R.id.cell_12 -> NumPair(1,2)
            R.id.cell_13 -> NumPair(1,3)
            R.id.cell_14 -> NumPair(1,4)
            R.id.cell_15 -> NumPair(1,5)
            R.id.cell_16 -> NumPair(1,6)
            R.id.cell_17 -> NumPair(1,7)
            R.id.cell_20 -> NumPair(2,0)
            R.id.cell_21 -> NumPair(2,1)
            R.id.cell_22 -> NumPair(2,2)
            R.id.cell_23 -> NumPair(2,3)
            R.id.cell_24 -> NumPair(2,4)
            R.id.cell_25 -> NumPair(2,5)
            R.id.cell_26 -> NumPair(2,6)
            R.id.cell_27 -> NumPair(2,7)
            R.id.cell_30 -> NumPair(3,0)
            R.id.cell_31 -> NumPair(3,1)
            R.id.cell_32 -> NumPair(3,2)
            R.id.cell_33 -> NumPair(3,3)
            R.id.cell_34 -> NumPair(3,4)
            R.id.cell_35 -> NumPair(3,5)
            R.id.cell_36 -> NumPair(3,6)
            R.id.cell_37 -> NumPair(3,7)
            R.id.cell_40 -> NumPair(4,0)
            R.id.cell_41 -> NumPair(4,1)
            R.id.cell_42 -> NumPair(4,2)
            R.id.cell_43 -> NumPair(4,3)
            R.id.cell_44 -> NumPair(4,4)
            R.id.cell_45 -> NumPair(4,5)
            R.id.cell_46 -> NumPair(4,6)
            R.id.cell_47 -> NumPair(4,7)
            R.id.cell_50 -> NumPair(5,0)
            R.id.cell_51 -> NumPair(5,1)
            R.id.cell_52 -> NumPair(5,2)
            R.id.cell_53 -> NumPair(5,3)
            R.id.cell_54 -> NumPair(5,4)
            R.id.cell_55 -> NumPair(5,5)
            R.id.cell_56 -> NumPair(5,6)
            R.id.cell_57 -> NumPair(5,7)
            R.id.cell_60 -> NumPair(6,0)
            R.id.cell_61 -> NumPair(6,1)
            R.id.cell_62 -> NumPair(6,2)
            R.id.cell_63 -> NumPair(6,3)
            R.id.cell_64 -> NumPair(6,4)
            R.id.cell_65 -> NumPair(6,5)
            R.id.cell_66 -> NumPair(6,6)
            R.id.cell_67 -> NumPair(6,7)
            R.id.cell_70 -> NumPair(7,0)
            R.id.cell_71 -> NumPair(7,1)
            R.id.cell_72 -> NumPair(7,2)
            R.id.cell_73 -> NumPair(7,3)
            R.id.cell_74 -> NumPair(7,4)
            R.id.cell_75 -> NumPair(7,5)
            R.id.cell_76 -> NumPair(7,6)
            R.id.cell_77 -> NumPair(7,7)
            else -> NumPair(-1,-1)
        }

    override fun onClick(v: View?) {
        if(v?.id != R.id.showresults_button) {
            if (!playerTurn) return
            playerTurn = false

            val cell: ImageButton = v as ImageButton
            val game = sharedViewModel.getGame()
            val gameboard = game?.grid
            val pair = getCoord(cell)

            val validPlayer = game?.addToGrid(gameboard,pair.gety(), pair.getx(), 1)

            if(!validPlayer!!) {
                if (game.actions(gameboard, 1).size > 0) {
                    playerTurn = true
                    return
                } else if (game.actions(gameboard, 2).size > 0) {
                    sharedViewModel.getGame()?.adversaryTurn()
                    val handler = Handler()
                    val runnable = Runnable {
                        updateUI()
                        playerTurn = true
                    }
                    handler.postDelayed(runnable, 1000)
                } else {
                    playerTurn = false
                    animateGameOver()
                    sharedViewModel.setWinner(winner)
                    sharedViewModel.setMoves(moves)
                }
            } else {
                updateUI()
                moves++

                sharedViewModel.getGame()?.adversaryTurn()
                val handler = Handler()
                val runnable = Runnable {
                    updateUI()
                    playerTurn = true
                }
                handler.postDelayed(runnable, 1000)
            }

            if(game.gameEnd()) {
                animateGameOver()
                playerTurn = false
                if(!game.winner) {
                    winner = "YOU LOST"
                }
                sharedViewModel.setWinner(winner)
                sharedViewModel.setMoves(moves)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val SHOW_BOARD_ANIM_DUR = 1000L
        const val SHOW_OVER_ANIM_DUR = 1000L
    }
}