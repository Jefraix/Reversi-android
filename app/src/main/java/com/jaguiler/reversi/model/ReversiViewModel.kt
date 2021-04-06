package com.jaguiler.reversi.model

import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaguiler.reversi.R
import com.jaguiler.reversi.databinding.FragmentGameBinding

class ReversiViewModel() : ViewModel() {
    private lateinit var reversiGame: Reversi
    private val _liveReversiGame = MutableLiveData<Reversi>()
    var liveReversiGame: LiveData<Reversi> = _liveReversiGame

    private var _boardcolor = MutableLiveData<Int>()
    private var boardcolor: LiveData<Int> = _boardcolor

    private var _playercolor = MutableLiveData<Int>()
    private var playercolor: LiveData<Int> = _playercolor

    private var _difficulty = MutableLiveData<Int>()
    private var difficulty: LiveData<Int> = _difficulty

    private var _winner = MutableLiveData<String>()
    private var winner: LiveData<String> = _winner

    private var _moves = MutableLiveData<Int>()
    private var moves: LiveData<Int> = _moves

    private var _set = MutableLiveData<Int>()
    var set: LiveData<Int> = _set

    fun setupReversiGame() {
        reversiGame = Reversi(difficulty.value!!)
        _liveReversiGame.value = reversiGame
    }

    fun setBoardColor(bs: Int) {
        _boardcolor.value = bs
    }

    fun setPlayerColor(pc: Int) {
        _playercolor.value = pc
    }

    fun setDifficulty(d: Int) {
        _difficulty.value = d
    }

    fun setSettingsBool(s: Int) {
        _set.value = s
    }

    fun setWinner(w: String) {
        _winner.value = w
    }

    fun setMoves(m: Int) {
        _moves.value = m
    }

    fun getGame() = liveReversiGame.value

    fun getPosSymbol(x: Int, y: Int) = reversiGame.grid[x][y]

    fun getBoardColor() = boardcolor.value

    fun getPlayerColor() = playercolor.value

    fun getDiff() = difficulty.value

    fun getTheWinner() = winner.value

    fun getNumMoves() = moves.value

    fun newSettings() = set.value

}