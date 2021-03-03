package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC463IslandPerimeter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC463IslandPerimeter();
    }

    /**
     * 根本沒想出這題會是什麼pattern
     * https://leetcode.com/problems/island-perimeter/discuss/94992/Java-9-line-solution-add-4-for-each-land-and-remove-2-for-each-internal-edge
     * 直到看到答案才知道就是基本的邊數計算, 沒想到這麼基本
     * 從左上角開始走
     * 1. 當i>0 代表有左邊的 adj 可能性,
     * 2. 當j>0 帶表有上邊的 adj 可能性
     * 每一個可能性都直接回頭 -2 就好
     */
    public int islandPerimeter(int[][] grid) {
        int r = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    r += 4;
                    if (i > 0 && grid[i - 1][j] == 1) r -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) r -= 2;
                }
            }
        }
        return r;
    }
}
