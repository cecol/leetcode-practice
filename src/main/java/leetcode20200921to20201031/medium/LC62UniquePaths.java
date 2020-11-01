package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC62UniquePaths extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC62UniquePaths();
        var r = LC.uniquePaths(3, 2);
        System.out.println(r);
    }

    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m == 1 || n == 1) return 1;
        int[][] t = new int[m][n];
        for (int i = 0; i < t.length; i++) t[i][0] = 1;
        Arrays.fill(t[0], 1);
        for (int i = 1; i < t.length; i++) {
            for (int j = 1; j < t[i].length; j++) {
                t[i][j] = t[i - 1][j] + t[i][j - 1];
            }
        }
        return t[m - 1][n - 1];
    }


}
