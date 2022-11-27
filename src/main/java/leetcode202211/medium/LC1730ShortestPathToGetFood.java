package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC1730ShortestPathToGetFood extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> bfs = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '*') {
                    bfs.offer(new int[]{i, j});
                    break;
                }

        int res = 0;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (bfs.size() > 0) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                int[] v = bfs.poll();
                for (int[] dir : dirs) {
                    int mx = v[0] + dir[0];
                    int my = v[1] + dir[1];
                    if (mx < 0 || my < 0 || mx >= m || my >= n || grid[mx][my] == 'X') continue;
                    if(grid[mx][my] == '#') return res;
                    grid[mx][my] = 'X';
                    bfs.offer(new int[]{mx, my});
                }
            }
            res++;
        }

        return -1;
    }
}
