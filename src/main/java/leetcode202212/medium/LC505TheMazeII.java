package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.immutable.List;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class LC505TheMazeII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC505TheMazeII();
    }


    /**
     * 在熟悉 LC490TheMaze 且 bfs + PriorityQueue 的題型後 很直觀解出來了
     * 關鍵在於
     * 1. 要理解球的四個方位走法, 往左走就一路走到底才停, 往上/下/右 都雷同, 走完在看是否 visited, 沒有才加入 bfs
     * 2. bfs 是由最短路徑一直堆疊下去, 所以 bfs Queue 是
     * PriorityQueue<int[]> bfs = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
     * 這樣就是一直挑出還是最短累積路徑一直往 上下左右擴增 看看
     * 只要哪一步  剛好等於 int[] destination 就可以回傳
     * PriorityQueue<int[]> 中 int[] = {x,y, 當前累計距離}, 所以會一直拿出 當前累計距離 的點來 bfs
     * */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        PriorityQueue<int[]> bfs = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        boolean[][] visited = new boolean[m][n];
        bfs.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        while (bfs.size() > 0) {
            int[] v = bfs.poll();
            int x = v[0], y = v[1], xx = x, yy = y;
            visited[x][y] = true;
            if (x == destination[0] && y == destination[1]) return v[2];
            while (xx > 0 && maze[xx - 1][y] == 0) xx--;
            if (!visited[xx][y]) bfs.offer(new int[]{xx, y, v[2] + (x - xx)});
            while (yy > 0 && maze[x][yy - 1] == 0) yy--;
            if (!visited[x][yy]) bfs.offer(new int[]{x, yy, v[2] + (y - yy)});
            xx = x;
            while (xx < m - 1 && maze[xx + 1][y] == 0) xx++;
            if (!visited[xx][y]) bfs.offer(new int[]{xx, y, v[2] + (xx - x)});
            yy = y;
            while (yy < n - 1 && maze[x][yy + 1] == 0) yy++;
            if (!visited[x][yy]) bfs.offer(new int[]{x, yy, v[2] + (yy - y)});
        }
        return -1;
    }
}
