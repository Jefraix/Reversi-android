package com.jaguiler.reversi.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReversiViewModel : ViewModel() {
    private var _board_size = MutableLiveData<Int>()
    var board_size: LiveData<Int> = _board_size

    private var _player_color = MutableLiveData<Int>()
    var player_color: LiveData<Int> = _player_color

    private var _difficulty = MutableLiveData<Int>()
    var difficulty: LiveData<Int> = _difficulty

    private var _winner = MutableLiveData<Boolean>()
    var winner: LiveData<Boolean> = _winner

    private var _moves = MutableLiveData<Int>()
    var moves: LiveData<Int> = _moves

    private var _set = MutableLiveData<Int>()
    var set: LiveData<Int> = _set


    fun setBoardSize(bs: Int) {
        _board_size.value = bs
    }

    fun setPlayerColor(pc: Int) {
        _player_color.value = pc
    }

    fun setDifficulty(d: Int) {
        _difficulty.value = d
    }

    fun setSettingsBool(s: Int) {
        _set.value = s
    }

    fun setWinner(w: Boolean) {
        _winner.value = w
    }

    fun setMoves(m: Int) {
        _moves.value = m
    }

    fun getBoardSize() = board_size.value

    fun getPlayerColor() = player_color.value

    fun getDiff() = difficulty.value

    fun getTheWinner() = winner.value

    fun getNumMoves() = moves.value

    fun newSettings() = set.value
}