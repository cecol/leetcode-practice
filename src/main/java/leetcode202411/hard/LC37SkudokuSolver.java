package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;

public class LC37SkudokuSolver extends BasicTemplate {
    public static void main(String[] args) {
    }

    public void solveSudoku(char[][] board) {
        solved(board);
    }

    // 沒過
    // 暴力 board[i][j] 下去 c from 1 -> 9 set 看看
    // 1.  board[i][j] = c
    // 2.  isV(board)
    // 2-1.  boolean solved(board)
    // 2-2. board[i][j] = '.'
    // 2-3. 如果 c From 1 -> 9 all false, return false, 代表上層失敗
    boolean solved(char[][] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isV(board, i, j, c)) {
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

    boolean isV(char[][] board, int r, int c, char v) {
        for (int i = 0; i < 9; i++) {
            if(board[i][c] != '.' && board[i][c] == v) return false;
            if(board[r][i] != '.' && board[r][i] == v) return false;
            int r33 = 3 * (r/3) + i/3, c33 = 3 * (c/3)+ i %3;
            if(board[r33][c33] != '.' && board[r33][c33] == v) return false;
        }
        return true;
    }
}
