package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC348DesignTicTacToe extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC348DesignTicTacToe();
    }

    int[] r = null;
    int[] c = null;
    int diag;
    int antiDiag;

    /**
     * Initialize your data structure here.
     */
    public void TicTacToe(int n) {
        r = new int[n];
        c = new int[n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        r[row] += toAdd;
        c[col] += toAdd;
        if (row == col) diag += toAdd;
        if (col == (c.length - row - 1)) antiDiag += toAdd;
        int size = r.length;
        if (Math.abs(r[row]) == size ||
                Math.abs(c[col]) == size ||
                Math.abs(diag) == size ||
                Math.abs(antiDiag) == size) return player;
        return 0;
    }
}
