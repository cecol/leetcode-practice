package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC864ShortestPathToGetAllKeys extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC864ShortestPathToGetAllKeys();
        LC.shortestPathAllKeys(new String[]{"@.a..","###.#","b.A.B"});
    }

    /**
     * https://leetcode.com/problems/shortest-path-to-get-all-keys/solutions/146827/java-bfs-21ms/
     * 雖然是看過 LC847ShortestPathVisitingAllNodes
     * 但兩邊的 multiple status 紀錄方式不太一樣
     * 相同概念 - 每個點都要來回走過幾次才會達到要的最終目的！！
     *
     * 所以要根據  最終目的是什麼 來決定要 去重的 state 長怎樣
     * LC847ShortestPathVisitingAllNodes 是所有 node 都 visited
     * 所以是所有 node 的 bitmap
     * 所以 status = 當前 node + visited node bitmap
     *
     * LC864ShortestPathToGetAllKeys 是所有 key 都 visited
     * 所以是所有 key 的 bitmap
     * 所以 當前 x,y + visited keys bitmap
     *
     * 所以要先掃完 grid 找到
     * 1. key 數  算出最終狀態 keys
     * 2. 起點
     * 3. 轉成 char[][] grids 之後好處理
     *
     * bfs 時候
     * 用 boolean[][][] visited = new boolean[m][n][targetKeys + 1]; 當作 visited
     * Queue<int[]> bfs 中 int[] = {x, y, visitedKeys}
     * bfs 檢查
     * 1. 超界
     * 2. nextKeyStatus & if (visited[x][y][nextKeyStatus]) continue;
     * 3. 遇到門鎖且 當前 key Status 可以解 if (c >= 'A' && c <= 'F' && !unlock(cur[2], c)) continue;
     * 過了上面3關 才可以繼續走
     *
     * 最後回傳
     * */
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        char[][] grids = new char[m][n];
        int x = -1, y = -1, keyCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                grids[i][j] = c;
                if (c == '@') {
                    x = i;
                    y = j;
                } else if (c >= 'a' && c <= 'f') {
                    keyCount++;
                }
                grids[x][y] = c;
            }
        }
        int targetKeys = 0;
        for (int i = 0; i < keyCount; i++) targetKeys = addKeys(targetKeys, 'a' + i);
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{x, y, 0});
        boolean[][][] visited = new boolean[m][n][targetKeys + 1];
        visited[x][y][0] = true;
        int res = 0;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (bfs.size() > 0) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                int[] cur = bfs.poll();
                if (cur[2] == targetKeys) return res;
                for (int[] dir : dirs) {
                    x = cur[0] + dir[0];
                    y = cur[1] + dir[1];
                    if (x < 0 || y < 0 || x >= m || y >= n || grids[x][y] == '#') continue;
                    char c = grids[x][y];
                    int nextKeyStatus = addKeys(cur[2], c);
                    if (visited[x][y][nextKeyStatus]) continue;
                    if (c >= 'A' && c <= 'F' && !unlock(cur[2], c)) continue;
                    visited[x][y][nextKeyStatus] = true;
                    bfs.offer(new int[]{x, y, nextKeyStatus});
                }
            }
            res++;
        }
        return -1;
    }

    int addKeys(int keys, int c) {
        if (c >= 'a' && c <= 'f') {
            int index = c - 'a';
            return keys | (1 << index);
        }
        return keys;
    }

    boolean unlock(int keys, int c) {
        int index = c - 'A';
        return (keys & (1 << index)) > 0;
    }
}
