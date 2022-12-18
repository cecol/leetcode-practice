package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class WeeklyContest323 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest323();
//        LC.longestSquareStreak(new int[]{5, 12, 3, 10, 4, 11, 4, 16, 11}); // expect 2
//        LC.longestSquareStreak(new int[]{4, 3, 6, 16, 8, 2}); // expect 3
//        LC.longestSquareStreak(new int[]{2, 4, 8, 16}); // expect 3
        LC.maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2});
    }

    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int res = 0;
        for (int i = 0; i < m; i++) Arrays.sort(grid[i]);
        for (int i = n - 1; i >= 0; i--) {
            int mx = 0;
            for (int j = 0; j < m; j++) {
                mx = Math.max(mx, grid[j][i]);
            }
            res += mx;
        }
        return res;
    }

    public int longestSquareStreak(int[] nums) {
        int res = -1;
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
            map.put(nums[i], i);
        }
        int[] dp = new int[n];
        dp[0] = -1;
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
            int sqrt = (int) Math.sqrt(nums[i]);
            if (sqrt * sqrt == nums[i] && set.contains(sqrt)) {
                if (dp[map.get(sqrt)] == -1) dp[i] = 2;
                else dp[i] = dp[map.get(sqrt)] + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    class Allocator {

        TreeMap<Integer, Integer> ts = new TreeMap<>();
        int size;
        HashMap<Integer, List<Integer>> ids = new HashMap<>();

        public Allocator(int n) {
            size = n;
        }

        public int allocate(int size, int mID) {
            if (ts.isEmpty()) {
                if (size > this.size) return -1;
                ts.put(0, size);
                if (!ids.containsKey(mID)) ids.put(mID, new ArrayList<>());
                ids.get(mID).add(0);
                return 0;
            }
            for (int k : ts.keySet()) {
                int nextStart = ts.get(k) + k;
                Integer nextEnd = ts.ceilingKey(nextStart);
                if (nextEnd == null) nextEnd = this.size;
                if (nextEnd - nextStart >= size) {
                    if (!ids.containsKey(mID)) ids.put(mID, new ArrayList<>());
                    ids.get(mID).add(nextStart);
                    ts.put(nextStart, size);
                    return nextStart;
                }
            }
            return -1;
        }

        public int free(int mID) {
            int c = 0;
            if (ids.containsKey(mID)) {
                for (int k : ids.get(mID)) {
                    c += ts.get(k);
                    ts.remove(k);
                }
                ids.remove(mID);
            }
            return c;
        }
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
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
