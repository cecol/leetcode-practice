package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2503MaximumNumberOfPointsFromGridQueries extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2503MaximumNumberOfPointsFromGridQueries();
    }

    /**
     * https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/solutions/2899361/java-bfs-with-comments-heap-and-treemap/
     * 我有觀察出基本思路 有做出暴力解, 但是TLE
     * 後來有做出 BFS, 配上 boolean[][] visited 還是 TLE
     * 我覺得我的我的時間複雜度還是 max(kLogK, m*n) 應該跟正解差不多
     *
     * 但可能中間過程中太多多餘算法 來做成我要的解法
     *
     * 直到看到答案才恍然大悟  用個 PriorityQueue 就可以很乾淨的解開了
     * 基本上不是要看 queries 找區間
     * 而是直接把 grid[0][0] 入 PriorityQueue 做 Bfs,
     * PriorityQueue 內放 int[]{x, y, grid[x][y]}
     * 初始query 就是 grid[0][0] + 1, 把 PriorityQueue 中 在這以下都找出來 加總 point 放到 TreeMap
     * 然後再找下一個 Query 區間 - int query = bfs.peek()[2] + 1;
     * 不斷算下去把 TreeMap 所有 grid 數字區間 points 都算出來
     *
     * 最後就是走過 queries 去查詢 TreeMap 拿出 floorKey/Value 就是答案
     *
     * 想不到思路可以簡化成這麼簡單明白... 我真的沒辦法解出來是合理的(2022/12/11)
     * */
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        TreeMap<Integer, Integer> pointsMap = new TreeMap<>();

        PriorityQueue<int[]> bfs = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        bfs.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;
        int points = 0;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (bfs.size() > 0) {
            int query = bfs.peek()[2] + 1;
            while (bfs.size() > 0 && bfs.peek()[2] < query) {
                int[] v = bfs.poll();
                points++;
                for (int[] dir : dirs) {
                    int x = v[0] + dir[0];
                    int y = v[1] + dir[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y]) continue;
                    bfs.offer(new int[]{x, y, grid[x][y]});
                    visited[x][y] = true;
                }
            }
            pointsMap.put(query, points);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer key = pointsMap.floorKey(queries[i]);
            if(key != null) res[i] = pointsMap.get(key);
        }
        return res;
    }


    public int[] maxPointsTLE(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int[] sq = Arrays.copyOf(queries, queries.length);
        Arrays.sort(sq);
        int[] sc = new int[queries.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            map.put(sq[i], i);
        }
        boolean[][] visited = new boolean[m][n];
        List<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        for (int i = 0; i < queries.length; i++) {
            if (i > 0 && sq[i] == sq[i - 1]) sc[i] = sc[i - 1];
            else {
                List<int[]> nextQueue = new LinkedList<>();
                sc[i] += (i == 0 ? 0 : sc[i - 1]);
                for (int[] v : queue) {
                    if (sq[i] > grid[v[0]][v[1]]) sc[i] += dfs(grid, visited, sq[i], v[0], v[1], nextQueue);
                    else nextQueue.add(v);
                }
                queue = nextQueue;
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] <= grid[0][0]) continue;
            res[i] = sc[map.get(queries[i])];
        }
        return res;
    }

    int dfs(int[][] grid, boolean[][] visited, int v, int i, int j, List<int[]> queue) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) return 0;
        if (grid[i][j] >= v) {
            queue.add(new int[]{i, j});
            return 0;
        }
        int c = 1;
        visited[i][j] = true;
        c += dfs(grid, visited, v, i + 1, j, queue);
        c += dfs(grid, visited, v, i - 1, j, queue);
        c += dfs(grid, visited, v, i, j + 1, queue);
        c += dfs(grid, visited, v, i, j - 1, queue);
        return c;
    }
}
