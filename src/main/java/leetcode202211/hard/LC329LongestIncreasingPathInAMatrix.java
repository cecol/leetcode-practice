package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC329LongestIncreasingPathInAMatrix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC329LongestIncreasingPathInAMatrix();
        int len = LC.longestIncreasingPath(new int[][]{{1, 2}});
        log.debug("{}", len);
    }

    /**
     * 直觀來看DFS可以解, 但是會TLE
     * 需要多加上一個 DP[m][n] 來記載這一個當前走過後她能走到的最大長度
     * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
     * 1. 每個點下去走 DFS
     * 2. skip 出界 或者 比前一格小的
     * 3. 找出當前這格4個方位走完的 max
     * 4. 因為只能往大的走, 所以無法走走過的, 所以不用多一個 boolean[][]來記載走過的位置
     * 5. 最關鍵 用一個 int[][] cache 記載當前走過的最大位置, 所以後面如果走到這個, 直接拿之前走過的紀錄
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, cache, -1);
                max = Math.max(max, len);
            }
        return max;
    }

    int dfs(int[][] matrix, int i, int j, int[][] cache, int mx) {
        int m = matrix.length, n = matrix[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) return 0;
        if (matrix[i][j] <= mx) return 0;
        if (cache[i][j] != 0) return cache[i][j];
        int m1 = dfs(matrix, i - 1, j, cache, matrix[i][j]);
        int m2 = dfs(matrix, i + 1, j, cache, matrix[i][j]);
        int m3 = dfs(matrix, i, j - 1, cache, matrix[i][j]);
        int m4 = dfs(matrix, i, j + 1, cache, matrix[i][j]);
        cache[i][j] = Math.max(Math.max(m1, m2), Math.max(m3, m4)) + 1;
        return cache[i][j];
    }
}
