package com.jaguiler.reversi.model;

import java.util.HashSet;

/**
 * Represents a formal model of an adversarial game.
 * The A generic is used for the data structure that
 * stores the applicable actions of a state. The S
 * generic is the data structure that stores a state
 * of the game.
 *
 * @author Juan Aguilera
 */
public interface AdversarialGame<A,S> {

    /**
     * Sets up the initial state of the game.
     */
    public void initialState();

    /**
     * Returns whose turn it is in the current state.
     * @return The player
     */
    public int player();

    /**
     * Returns a set containing all the possible
     * actions in a given state.
     * @param state The state
     * @param p The player whose turn it is
     * @return A set of possible actions
     */
    public HashSet<A> actions(S state, int p);

    /**
     * Returns the resulting state if an action is applied
     * to the given state.
     * @param state The state
     * @param action The action
     * @param p Whose turn it is
     * @return A state after action is performed
     */
    public S result(S state, A action, int p);

    /**
     * Checks to see if the current state is a terminal state
     * @param state The state
     * @return Whether the state is terminal
     */
    public boolean terminalTest(S state);

    /**
     * 	Gives the utility of a given state
     * @param state The state
     * @param p Whose turn it is
     * @return Utility of the state
     */
    public int utility(S state, int p);

}

