package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC36ValidSudoku extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        char[][] cc = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        var LC = new LC36ValidSudoku();
        LC.isValidSudoku(cc);
    }

    public boolean isValidSudoku(char[][] board) {
        Set<Character>[][] ss = new HashSet[2][9];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 9; j++)
                ss[i][j] = new HashSet<>();
        Set<Character>[][] ss2 = new HashSet[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                ss2[i][j] = new HashSet<>();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!ss[0][i].add(board[i][j])) return false;
                    if (!ss[1][j].add(board[i][j])) return false;
                    if (!ss2[i / 3][j / 3].add(board[i][j])) return false;
                }
            }
        return true;
    }
}
