package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC675CutOffTreesForGolfEvent extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC675CutOffTreesForGolfEvent();
    }

    /**
     * https://leetcode.com/problems/cut-off-trees-for-golf-event/solutions/107404/java-solution-priorityqueue-bfs/
     * 被題目迷惑好久... 蘇然是直觀的 BFS, 但沒看懂題目要問的,
     * 1. return the minimum steps you need to walk to cut off all the trees.
     * 2. If you are standing in a cell with a tree, you can choose whether to cut it off.
     * 3. must cut off the trees in order from shortest to tallest.
     * <p>
     * 2, 3 很容易讓人疑惑, 配上 Example, 會以為是連續邊走邊 cut,
     * 其實關鍵是在 [you can choose whether to cut it off] 就是說當前你可以不砍, 可以砍
     * 任意你走, 但是一但砍了 樹高 h1, 下次就要砍樹高 h2
     * 所以砍樹是有順序 由小到大, 一開始砍最高的, 後面就不用砍了,
     * 所以就是要先從 [0, 0] 走到最矮樹, 最矮樹 再走到 次矮樹... 每個過程的 BFS
     * <p>
     * 所以
     * 1. 先把所有樹 sort by height
     * 2. 從 [0, 0] 開始依序對下一棵樹 兩兩間 bfs
     *
     * 這是很典型的 BFS, 但通常 BFS 題目一次 BFS 就有答案, 這是多次 BFS 加總的答案
     * 也算不常見了
     */
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> tree = new ArrayList<>();
        int m = forest.size(), n = forest.get(0).size();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) tree.add(new int[]{i, j, forest.get(i).get(j)});
            }
        Collections.sort(tree, (x, y) -> x[2] - y[2]);
        int res = 0;
        int[] pos = new int[]{0, 0};
        for (int i = 0; i < tree.size(); i++) {
            int[] t = tree.get(i);
            int dist = bfs(forest, pos[0], pos[1], t[0], t[1]);
            if (dist == -1) return -1;
            res += dist;
            pos[0] = t[0];
            pos[1] = t[1];
        }
        return res;
    }

    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    int bfs(List<List<Integer>> forest, int i, int j, int x, int y) {
        int m = forest.size(), n = forest.get(0).size();
        Queue<int[]> bfs = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        bfs.offer(new int[]{i, j});
        int res = 0;
        while (bfs.size() > 0) {
            int s = bfs.size();
            for (int k = 0; k < s; k++) {
                int[] v = bfs.poll();
                if (v[0] == x && v[1] == y) return res;
                for (int[] dir : dirs) {
                    int mx = v[0] + dir[0];
                    int my = v[1] + dir[1];
                    if (mx < 0 || my < 0 || mx >= m || my >= n || visited[mx][my]||forest.get(mx).get(my) == 0) continue;
                    bfs.offer(new int[]{mx, my});
                    visited[mx][my] = true;
                }
            }
            res++;
        }
        return -1;
    }
}
