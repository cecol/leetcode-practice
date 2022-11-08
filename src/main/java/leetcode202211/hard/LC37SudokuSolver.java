package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC37SudokuSolver extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC37SudokuSolver();
    }

    /**
     * 原本想說要用Set之類記載目前每個欄位剩下哪些 Candidates, 然後從最少Candidates 開始解
     * 結果正解是暴力法遞迴下去解
     * 1. 找到空欄位, 1-9 看哪個合法就去填看看
     * 2. 填了之後下去遞迴
     *       如果下去遞迴回傳 false, 代表上一次填的數字不可用, 得改回 board[i][j] = '.';
     *       如果下去遞迴回傳 true, 後面也都填完了, 就可以直接回傳 true
     */
    public void solveSudoku(char[][] board) {
        solved(board);
    }

    boolean solved(char[][] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solved(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        return true;
    }

    boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row][i] != '.' && board[row][i] == c) return false;
            int r33 = 3 * (row / 3) + i / 3, c33 = 3 * (col / 3) + i % 3;
            if (board[r33][c33] != '.' && board[r33][c33] == c) return false;
        }
        return true;
    }
}
