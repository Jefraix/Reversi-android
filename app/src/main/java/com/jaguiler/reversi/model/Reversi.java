package com.jaguiler.reversi.model;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains all necessary methods to track the state
 * of the reversi game. It implements the AdversarialGame
 * interface, making use of the terminalState(), actions()
 *
 * @author Juan Aguilera
 */
public class Reversi implements AdversarialGame<NumPair, char[][]> {

    private char[][] Grid; // Stores the state of the board in the current turn
    private int max_depth;

    /**
     * Creates a reversi game object
     */
    public Reversi(int diff) {
        this.Grid = new char[8][8];
        this.initialState();

        switch(diff) {
            case 1:
                this.max_depth = 1;
                break;
            case 2:
                this.max_depth = 3;
                break;
            case 3:
                this.max_depth = 5;
                break;
        }
    }

    /* Sets the initial state of the game depending on
     * board size.
     * @see AdversarialGame#initialState()
     */
    @Override
    public void initialState() {
        for(int i = 0; i < this.Grid.length; i++) {
            Arrays.fill(this.Grid[i], ' ');
        }

        this.Grid[3][3] = 'o';
        this.Grid[3][4] = 'x';
        this.Grid[4][3] = 'x';
        this.Grid[4][4] = 'o';
    }

    /* Checks which player's turn it is.
     *
     * Not used.
     *
     * @see AdversarialGame#player()
     */
    @Override
    public int player() {
        return 1;
    }

    /* Returns a HashSet of numbered pairs representing
     * coordinates in the Grid. Each of the numbered p-
     * airs is a valid position in the current turn.
     * @param state The state
     * @param p The player whose turn it is
     * @return A set of possible actions
     * @see AdversarialGame#actions()
     */
    @Override
    public HashSet<NumPair> actions(char[][] s, int p) {
        HashSet<NumPair> action_set = new HashSet<NumPair>();

        for(int r = 0; r < s.length; r++) {
            for(int c = 0; c < s.length; c++) {
                if(s[r][c] == ' ' && checkGrid(s, c, r, p, 0)) { // if position is empty, and a move is legal
                    action_set.add(new NumPair(c, r)); // add to the action set
                }
            }
        }

        return action_set;
    }

    /* Returns a resulting Grid configuration depending on
     * the given action being taken on the given state of
     * the Grid.
     * @param state The state
     * @param action The action
     * @param p Whose turn it is
     * @return A state after action is performed
     * @see AdversarialGame#result()
     */
    @Override
    public char[][] result(char[][] s, NumPair a, int p) {
        char[][] resultState = new char[s.length][];

        // Make copy of given state
        for(int i = 0; i < s.length; i++) {
            resultState[i] = Arrays.copyOf(s[i], s.length);
        }

        addToGrid(resultState, a.getx(), a.gety(), p); // adds board piece to resultState

        return resultState;
    }

    /* Checks to see if the current state is a terminal state
     * @param state The state
     * @return Whether the state is terminal
     * @see AdversarialGame#terminalTest()
     */
    @Override
    public boolean terminalTest(char[][] s) {
        return (actions(s, 1).size() == 0)
                && (actions(s, 2).size() == 0); // Check if possible actions of both players are exhausted
    }

    /* Returns the utility of the given state
     * @param state The state
     * @param p Whose turn it is
     * @return Utility of the state
     * @see AdversarialGame#utility()
     */
    @Override
    public int utility(char[][] s, int p) {
        int x = 0;
        int o = 0;

        // Count instances of 'x' and 'o'
        for(int r = 0; r < s.length; r++) {
            for(int c = 0; c < s.length; c++) {
                if(s[r][c] == 'x') { x++;}
                else if(s[r][c] == 'o') { o++;}
            }
        }

        // Return utility based on player
        switch(p) {
            case 1:
                if(x > o) { return 1;}
                else if (x == o) { return 0;}
                else { return -1;}
            case 2:
                if(o > x) { return 1;}
                else if (o == x) { return 0;}
                else { return -1;}
        }

        System.out.println("This should not print");
        return 2;
    }

    /**
     * Plays the computers turn. Searches the state-space
     * of the game using two similar algorithms:
     *
     * Board size 4: The minimax algorithm is used to search
     * for a terminal state in the current state, and makes
     * a decision at the root depending on that.
     *
     * Board size 8: A heuristic alpha-beta minimax algorithm is
     * used to search up until a cut-off state. Since the terminal
     * states are far down the state-space tree in a board of size
     * 8, it is vital to use this algorithm to conserve both space
     * and time.
     */
    public boolean adversaryTurn() {
        /*
        if(this.Grid.length == 4) {
            //System.out.println("Adversary choosing move...");
            NumPair d = minimaxDecision(this.Grid);

            if(d.getx() == -1) { // If no applicable actions
                //System.out.println("Opponent decides to pass turn.");
            } else {
                addToGrid(this.Grid, d.getx(), d.gety(), 2);
            }

        } else if(this.Grid.length == 8) {
            //System.out.println("Adversary choosing move...");
            NumPair d = alphaBetaDecision(this.Grid);

            if(d.getx() == -1) { // If no applicable actions
                //System.out.println("Opponent decides to pass turn.");
            } else {
                addToGrid(this.Grid, d.getx(), d.gety(), 2);
            }
        }
        */
        //System.out.println("Adversary choosing move...");
        NumPair d = alphaBetaDecision(this.Grid);

        if(d.getx() == -1) { // If no applicable actions
            //System.out.println("Opponent decides to pass turn.");
            return false;
        } else {
            addToGrid(this.Grid, d.getx(), d.gety(), 2);
            return true;
        }
    }

    /**
     * Performs the Minimax algorithm on the given state.
     * The algorithm will chose the best decision the
     * computer player can make by computing the minimax
     * score of each respective node on the way to the
     * terminal node once its found. The algorithm will
     * guarantee the computer to make optimal choices under
     * the assumption the human player will play their best
     * game.
     *
     * Not desireable for this project.
     *
     * @param s The current state of the board
     * @return A decision in the form of a number pair
     */
    private NumPair minimaxDecision(char[][] s) {
        ArrayList<NumPair> action_list = new ArrayList<NumPair>(actions(s, 2)); // Generate a list of possible actions
        if(action_list.isEmpty()) { return new NumPair(-1,-1);} // If empty, return invalid position

        NumPair best_action = action_list.get(0);

        for(NumPair np : action_list) { // Check every possible actions
            if(minValue(result(s, np, 2)) > minValue(result(s, best_action, 2))) { // Choose the best
                best_action = np;
            }
        }

        return best_action;
    }

    /**
     * Chooses the max value given by the applicable actions
     * to state s by player 2.
     * @param s State of the game
     * @return Minimax value
     */
    private int maxValue(char[][] s) {
        if(terminalTest(s)) { return utility(s, 2);}

        int v = Integer.MIN_VALUE;

        for(NumPair np : actions(s,2)) {
            v = Math.max(v, minValue(result(s, np, 2)));
        }

        return v;
    }

    /**
     * Chooses the min value given by the applicable actions
     * to state s by player 1.
     * @param s State of the game
     * @return Minimax value
     */
    private int minValue(char[][] s) {
        if(terminalTest(s)) {return utility(s, 1);}

        int v = Integer.MAX_VALUE;

        for(NumPair np : actions(s,1)) {
            v = Math.min(v, maxValue(result(s, np, 1)));
        }

        return v;
    }

    /**
     * Alpha-Beta pruning version of minimax. The lower and
     * upper bounds alpha and beta allow the minimax algorithm
     * to prune certain parts of the state-space tree. This
     * implementation is vital for the fast execution of the
     * larger board version of the reversi game.
     *
     * @param s The current state of the game
     * @return The action to take at this state
     */
    private NumPair alphaBetaDecision(char[][] s) {
        ArrayList<NumPair> action_list = new ArrayList<NumPair>(actions(s, 2)); // Generate a list of possible actions
        if(action_list.isEmpty()) { return new NumPair(-1,-1);} // If empty, return invalid position
        NumPair best_action = action_list.get(0);
        int prevVal = minValueAB(result(s, best_action, 2), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        int currVal;

        for(NumPair np : action_list) {
            currVal = minValueAB(result(s, np, 2), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
            if(currVal > prevVal) { // Choose the best
                best_action = np;
                prevVal = currVal;
            }
        }

        return best_action;
    }

    /**
     * Chooses the max value given by the applicable actions
     * to state s by player 2. Keeps an upper and lower
     * bound that is compared to parent node upper and lower
     * bounds to potentially prune part of the search tree.
     *
     * @param s State of the game
     * @param a Lower bound (Best for max)
     * @param b Upper bound (Best for min)
     * @param d Current depth
     * @return Minimax value
     */
    private int maxValueAB(char[][] s, int a, int b, int d) {
        if(cutOffTest(s, d)) { return heuristic(s, 2);}

        int v = Integer.MIN_VALUE;

        for(NumPair np : actions(s,2)) {
            v = Math.max(v, minValueAB(result(s, np, 2), a, b, d+1));
            if(v >= b) { return v;}
            a = Math.max(a, v);
        }

        return v;
    }

    /**
     * Chooses the min value given by the applicable actions
     * to state s by player 1. Keeps an upper and lower
     * bound that is compared to parent node upper and lower
     * bounds to potentially prune part of the search tree.
     *
     * @param s State of the game
     * @param a Lower bound (Best for max)
     * @param b Upper bound (Best for min)
     * @param d Current depth
     * @return Minimax value
     */
    private int minValueAB(char[][] s, int a, int b, int d) {
        if(cutOffTest(s, d)) { return heuristic(s, 1);}

        int v = Integer.MAX_VALUE;

        for(NumPair np : actions(s,1)) {
            v = Math.min(v, maxValueAB(result(s, np, 1), a, b, d+1));
            if(v <= a) { return v;}
            b = Math.min(b, v);
        }

        return v;
    }

    /**
     * Tests if the given state is a terminal state or if
     * the depth limit has been reached for the given state.
     *
     * @param s The state to check
     * @param d The current depth
     * @return Whether s is a candidate for cutoff or a terminal
     */
    private boolean cutOffTest(char[][] s, int d) {
        if(terminalTest(s)) {return true;}
        else if(d == this.max_depth) { return true;}
        else {return false;}
    }

    /**
     * Heuristic function for the reversi/othello game.
     * Calculates the utility of a given state depending
     * on the player. It simply calculates the difference
     * in number of the board pieces. If there are more
     * of player p's pieces, the utility is positive. Ot-
     * herwise, it will be negative. The more the difference
     * in pieces, the higher the magnitude of the utility.
     *
     * @param s The given state to calculate utility for
     * @param p The player whose utility will be calculated
     * @return The utility value
     */
    private int heuristic(char[][] s, int p) {
        int x = 0;
        int o = 0;

        for(int r = 0; r < s.length; r++) {
            for(int c = 0; c < s.length; c++) {
                if(s[r][c] == 'x') { x++;}
                else if(s[r][c] == 'o') { o++;}
            }
        }

        switch(p) {
            case 1:
                return x - o;
            case 2:
                return o - x;
        }

        System.out.println("This should not print, bro lol");
        return 70;
    }

    /**
     * Adds a symbol to the current grid at coordinates
     * c,r. The symbol put depends on the value of p.
     *
     * @param c Column of the grid
     * @param r Row of the grid
     * @param p Player
     * @return Whether adding the symbol was successful
     */
    public boolean addToGrid(char[][] s, int c, int r, int p) {
        if(s[r][c] == ' ') {
            switch(p) {
                case 1:
                    if(checkGrid(s, c, r, p, 1)) { s[r][c] = 'x';}
                    else{ return false;}
                    break;
                case 2:
                    if(checkGrid(s, c, r, p, 1)) { s[r][c] = 'o';}
                    else { return false;}
                    break;
                default:
                    //System.out.println("Something went wrong while trying to add to grid");
            }
            return true;
        } else {
            //System.out.println("Selected space is not empty.");
            return false;
        }
    }

    /**
     * Checks the given grid to see if given column (letter) and row
     * (number) result in a possible position in the given grid.
     * This method has two different uses: It always checks to see
     * if the move is possible, but it will also modify the given
     * char array if m = 1. This helps update the grid after.
     *
     * @param c The column
     * @param r The row
     * @param p Player at play in this state
     * @param m Mode of the method
     * @return Whether the given parameters result in a legal move
     */
    private boolean checkGrid(char[][] s, int c, int r, int  p, int m) {
        // Change plr and opp depending on p value (1 = human, 2 = ai)
        char plr = (p == 1) ? 'x' : 'o';
        char opp = (p == 1) ? 'o' : 'x';
        int len = s.length;
        boolean valid = false;

        // For loops calculate positions to check around the given position (r, c)
        for(int r_rel = -1; r_rel < 2; r_rel++) {// From top to bottom
            for(int c_rel = -1; c_rel < 2; c_rel++) { // From left to right

                if((r+r_rel < 0 || r+r_rel >= len)
                        || (c+c_rel < 0 || c+c_rel >= len)) { // Check if curr pos out of bounds
                    if(r_rel == 0) {c_rel++;} // Skips position (r, c)
                    continue;
                }

                char curr = s[r+r_rel][c+c_rel]; // Get char at pos

                if(curr == opp) { // If an opponent
                    int x = c + c_rel;
                    int y = r + r_rel;

                    do { // Look in that direction until no more opp pieces
                        x += c_rel;
                        y += r_rel;
                        if((x < 0 || y < 0) || (x >= len || y >= len)) { // out of bounds
                            break;
                        } else {
                            curr = s[y][x];
                        }
                    } while(curr == opp);

                    if(curr == plr) { // if at resulting position we have found a plr piece
                        valid = true; // this is a valid move
                        if(m == 1) { // Go back to origin
                            x -= c_rel;
                            y -= r_rel;
                            do {     // update opp pieces to plr pieces
                                s[y][x] = plr;
                                x -=c_rel;
                                y -= r_rel;
                                curr = s[y][x];
                            } while(curr != ' ');
                        }
                    }
                }

                if(r_rel == 0) {c_rel++;} // Skips position (r, c)
            }
        }

        if(!valid && m == 1) {
            //System.out.println("Not a valid move.");
        };

        return valid;
    }

    /**
     * Checks if game is in terminal state.
     *
     * @return Whether game is in terminal state
     */
    public boolean gameEnd() {
        return terminalTest(this.Grid);
    }

    /**
     * Returns the current grid's configuration.
     *
     * @return The reversi's object grid.
     */
    public char[][] getGrid() {
        return this.Grid;
    }

    /**
     * Sets the max depth to search in the state-space.
     *
     * @param new_Depth The new depth to set
     */
    public void setMaxDepth(int new_Depth) {
        this.max_depth = new_Depth;
    }

    public boolean getWinner() {
        int x = 0;
        int o = 0;

        for(int r = 0; r < this.Grid.length; r++) {
            for(int c = 0; c < this.Grid.length; c++) {
                if(this.Grid[r][c] == 'x') { x++;}
                else if(this.Grid[r][c] == 'o') { o++;}
            }
        }
        if (x > o) return true;
        else return false;
    }
}
