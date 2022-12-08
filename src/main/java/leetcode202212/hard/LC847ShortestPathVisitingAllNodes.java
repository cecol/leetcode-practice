package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC847ShortestPathVisitingAllNodes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC847ShortestPathVisitingAllNodes();
        LC.shortestPathLength(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}});
    }

    /**
     * 原本以為這題有什麼特別算法, 結果是暴力 BFS 下去找 最短路徑
     * https://github.com/wisdompeak/LeetCode/tree/master/BFS/847.Shortest-Path-Visiting-All-Nodes
     * 因為 edge 有各種可能, 我們不可能紀錄 visited node 來確認是否用重複走, 而且必然有些 node/edge 得來回經過 才會走到其他 node
     * 畢竟一個 node 可能要 來回經過多次才會走到其他 node, 所以當前 node + 拜訪過的 node 才是要紀錄的 visited status
     * 才會知道該 node 再 bfs 是否只會產生相同結果
     *
     * 但不可能用 Set<Integer> 來紀錄每一步 BFS 過往拜訪的 node, 會 TLE
     * 所以要用 bitmap 來記載 哪些 node 看過了
     * 關鍵在於 1 <= n <= 12, int 32 bit 夠用!!
     * 所以假設 n = 5, 最終要的 status = 31, 就是 bit 0 to 4 都是 1
     *
     * 所以一開始 就要把所有 node 當作潛在的 最短路徑起頭, 都加入 bfs queue
     * - for i = 0 to n
     * - int status = (1 << (i)); - 起點先當作走過了, 所以 status 的 i offset == 1
     * - q.offer(new int[]{status, i});
     * bfs 裏面是 int[]{status, 當前走到的 node}, status 就是有哪些 bits == 1, 就是代表哪些 node 走過了
     * Set<String> visited = new HashSet<>(); 就是 visited.add(status + ":" + i);
     *
     * 所以每次 bfs 都下去 一層一層 level++ bfs, 只要看到 status = target, 就可以回傳 level
     *
     * bfs 檢查
     * int nextStatus = cur[0] | (1 << (nextNode));
     * if (visited.contains(nextStatus + ":" + nextNode)) continue; 就代表走過,
     * 代表從nextNode 再下去走 只會得到相同 status (該 nextNode 已經沒有 edge 可以再 bfs 下去了)
     * */
    public int shortestPathLength(int[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int target = 0, level = 0;
        for (int i = 0; i < graph.length; i++) {
            int status = (1 << (i));
            target = target | (1 << (i));
            visited.add(status + ":" + i);
            q.offer(new int[]{status, i});
        }

        while (q.size() > 0) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                if (cur[0] == target) return level;
                for (int i = 0; i < graph[cur[1]].length; i++) {
                    int nextNode = graph[cur[1]][i];
                    int nextStatus = cur[0] | (1 << (nextNode));
                    if (nextStatus == target) return level + 1;
                    if (visited.contains(nextStatus + ":" + nextNode)) continue;
                    visited.add(nextStatus + ":" + nextNode);
                    q.offer(new int[]{nextStatus, nextNode});
                }
            }
            level++;
        }
        return -1;
    }
}
