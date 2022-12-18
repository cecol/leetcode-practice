package leetcode202212.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC883ProjectionAreaOf3DShapes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC883ProjectionAreaOf3DShapes();
    }

    /**
     * contest rating 1425, 很直觀的一題
     *
     * */
    public int projectionArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        int[] maxZ = new int[n];
        for (int i = 0; i < m; i++) {
            int maxY = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) res++;
                maxY = Math.max(maxY, grid[i][j]);
                maxZ[j] = Math.max(maxZ[j], grid[i][j]);
            }
            res += maxY;
        }
        for (int z : maxZ) res += z;
        return res;
    }
}
