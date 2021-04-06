package com.jaguiler.reversi.model;

/**
 * A simple class that contains two values.
 * Used by the Reversi class in order to
 * store coordinates of the game board.
 *
 * @author Juan Aguilera
 */
public class NumPair {
    private final int x;
    private final int y;

    public NumPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    @Override
    public int hashCode() {
        return this.x + this.y;
    }
}

