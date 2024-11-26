package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC36ValidSudoku extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全忘記這招善用 Set 來達成檢查存在否
    // Set<Character>[0][0-9] Set 表達每列 i 看到的 數字有否重複
    // Set<Character>[1][0-9] Set 表達每列 j 看到的 數字有否重複
    // Set<Character>[0-3][0-3] Set 表達每9宮格 i/3, j/3 看到的 數字有否重複
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[][] s1 = new HashSet[2][9];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 9; j++)
                s1[i][j] = new HashSet<>();
        Set<Character>[][] s2 = new HashSet[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                s2[i][j] = new HashSet<>();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!s1[0][i].add(board[i][j])) return false;
                    if (!s1[1][j].add(board[i][j])) return false;
                    if (!s2[i / 3][j / 3].add(board[i][j])) return false;
                }
            }

        return true;
    }
}
