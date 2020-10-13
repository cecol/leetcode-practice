package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC64MinimumPathSum extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC64MinimumPathSum();
        var r = LC.minPathSum(null);
    }

    public int minPathSum(int[][] grid) {
        if (grid == null) return 0;
        if (grid.length == 0) return 0;
        if (grid.length == 1 && grid[0] == null) return 0;
        if (grid.length == 1 && grid[0].length == 0) return 0;
        if (grid.length == 1 && grid[0].length == 1) return grid[0][0];
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < dp.length; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < dp[0].length; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];
        for (int i = 1; i < dp.length; i++)
            for (int j = 1; j < dp[0].length; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        return dp[dp.length - 1][dp[0].length - 1];
    }
}