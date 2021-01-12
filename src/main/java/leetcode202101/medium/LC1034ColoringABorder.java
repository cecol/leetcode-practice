package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC1034ColoringABorder extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1034ColoringABorder();
//        var s = LC.colorBorder(new int[][]{{1, 2, 2}, {2, 3, 2}}, 0, 1, 3);
//        var s2 = LC.colorBorder(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2);
        var s3 = LC.colorBorder(new int[][]{{2, 1, 2, 2}, {2, 2, 1, 2}, {1, 1, 2, 1}}, 2, 2, 2);
    }

    /**
     * 花了超久才看懂這題的border概念
     * 給一個grid, 裡面每一格的數字代表不同顏色(標記), 給一個座標(r0,c0)
     * 由這個座標延伸出去的(四個面向)只要跟自己顏色相同, 就是同一component
     * 目的是標記出這一組component的邊界, 所謂的邊界概念是指這component任一格只要其ㄧ邊是
     * 1. 面對其他顏色 or
     * 2. grid的邊界
     * 所以這樣著色下去, 只有component中自己的上下左右都是跟自己同色 -> 不能改顏色 -> 就是說
     * 1. 不在邊界
     * 2. 四周都是跟自己不同色
     * --> 以上情形都滿足就不用改色
     * --> 其次都要改
     * 不能直接dfs下去改色, 這樣走到的那一格很難判定他是邊界或者是非邊界(因為旁邊原本同色後來被改成邊界)
     * 解法是先dfs下去把component都改成負數, 在dfs尾巴判定
     * 1. i > 0 && j > 0 && i < grid.length - 1 && j < grid[0].length -> 不在邊界
     * 2. 4周的格子絕對值是原本顏色 -> 用絕對值是因為dfs改成負數
     * -->符合上述就改回原色
     * 最後在繞一次grid, 任何 < 0就是邊界, 可以開始改色
     */
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if (grid == null || grid.length == 0) return grid;
        int oldC = grid[r0][c0];
        dfs(grid, r0, c0, oldC);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) grid[i][j] = color;
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int oldC) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] != oldC) return;
        grid[i][j] = -oldC;
        dfs(grid, i + 1, j, oldC);
        dfs(grid, i - 1, j, oldC);
        dfs(grid, i, j - 1, oldC);
        dfs(grid, i, j + 1, oldC);
        if (i > 0 && j > 0 && i < grid.length - 1 && j < grid[0].length - 1
                && oldC == Math.abs(grid[i + 1][j])
                && oldC == Math.abs(grid[i - 1][j])
                && oldC == Math.abs(grid[i][j + 1])
                && oldC == Math.abs(grid[i][j - 1]))
            grid[i][j] = oldC;
    }
}
