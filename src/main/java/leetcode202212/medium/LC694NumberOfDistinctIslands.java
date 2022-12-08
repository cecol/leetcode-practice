package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC694NumberOfDistinctIslands extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC694NumberOfDistinctIslands();
    }

    /**
     * https://leetcode.com/problems/number-of-distinct-islands/solutions/108474/java-c-clean-code/
     * 這題題意難在你要怎麼知道哪些 islands 長得一樣?
     * 1. 基本上一定是 i = 0 to m, j = 0 to n 去掃過 grid, 有 grid[i][j] == 1 就下去 dfs, 所以可以走完所有 islands
     * 2. 但走完要記載 islands,  這邊用 StringBuilder sb 去記載走過島嶼的 direction
     * - 因為都是上到下, 左到右去掃, 所以長得一樣的 islands, dfs direction 會長的一模一樣
     * - 所以第一點 O == original, dfs裏面就是 x+1: 'r', x-1: 'l', y+1: 'u', y-1: 'd'
     * - dfs回來也要 append('b') 代表遞迴回來的 終止 地點
     * - dfs 掃回來的 String 就放在 Set<String> dist 就是答案
     *
     * */
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<String> dist = new HashSet<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "O");
                    dist.add(sb.toString());
                }
        return dist.size();
    }

    void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m | j >= n || grid[i][j] != 1) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i + 1, j, sb, "r");
        dfs(grid, i - 1, j, sb, "l");
        dfs(grid, i, j + 1, sb, "u");
        dfs(grid, i, j - 1, sb, "d");
        sb.append("b");
    }
}
