package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC490TheMaze extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC490TheMaze();
    }


    /**
     * https://leetcode.com/problems/the-maze/solutions/97062/8-ms-straightforward-java-dfs-beats/
     * 這題看起來很簡單  但因為他不是一格一格走  是連續走就卡住了  但其實搞清楚關鍵可以很簡單
     * 1. 是 DFS 且 帶一個 boolean[][] visited 下去
     * - 每次 DFS 進去的 x,y 若等於 dest, 就是 true
     * - 或者 visited[x][y] -> false
     * 2. 找下一個是 int xx = x, while(xx - 1 >= 0 && maze[xx-1][y] == 0) xx--, 然後用 xx, y 下去 dfs
     * 以上作用在 4個方向 就可以了
     * */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        return dfs(maze, new boolean[m][n], start[0], start[1], destination);
    }

    boolean dfs(int[][] maze, boolean[][] visited, int x, int y, int[] dest) {
        int m = maze.length, n = maze[0].length;
        if (x == dest[0] && y == dest[1]) return true;
        if (visited[x][y]) return false;
        visited[x][y] = true;

        int xx = x;
        while (xx - 1 >= 0 && maze[xx - 1][y] == 0) xx--;
        if (dfs(maze, visited, xx, y, dest)) return true;

        int yy = y;
        while (yy - 1 >= 0 && maze[x][yy - 1] == 0) yy--;
        if (dfs(maze, visited, x, yy, dest)) return true;

        xx = x;
        while (xx + 1 < m && maze[xx + 1][y] == 0) xx++;
        if (dfs(maze, visited, xx, y, dest)) return true;

        yy = y;
        while (yy + 1 < n && maze[x][yy + 1] == 0) yy++;
        if (dfs(maze, visited, x, yy, dest)) return true;

        return false;
    }
}
