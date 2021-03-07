package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC73SetMatrixZeroes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC73SetMatrixZeroes();
    }

    /**
     * 這題一開始看錯題目, 一直沒想懂怎可能 time complexity 不是 O(m*n)
     * 後來才看到是要求 space complexity 優於 O(m*n)
     * 其實就是在問如何 用最少space去記憶要set成 0 的 row & col
     * 基本上用個 Set 是可以達成
     * 不過如果是 constant space我是沒想出來
     * https://leetcode.com/problems/set-matrix-zeroes/discuss/26008/My-AC-java-O(1)-solution-(easy-to-read)
     * 1. 看起來就是先走過所有的 m*n
     * -> 如果是 first row, first column -> 個別標記起來
     * -> if matrix[i][j] == 0 -> 把 matrix[0][j] = 0; matrix[i][0] = 0;
     * -> 變相用first row, first column = 0 來記憶等等要全set 0的 matrix[i][j]
     * 2. 再走過所有的 (1 to m)*(1 to n) -> 檢查 matrix[i][0] == 0 || matrix[0][j] == 0 來看該格要否變成 0
     * 3. 如果有 first row, first column 原本是 0 -> 把  first row, first column set 成 0
     */
    public void setZeroes(int[][] matrix) {
        boolean fr = false, fc = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) fr = true;
                    if (j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) Arrays.fill(matrix[0], 0);
        if (fc) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
