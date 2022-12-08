package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1245TreeDiameter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1245TreeDiameter();
        LC.treeDiameter(new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}});
    }

    /**
     * 這題有個很關鍵的算法
     * 就是隨便選任一點 A, BFS 走到最遠的點 B
     * 再由 B BFS 走到最遠點 C, BC 距離就是最長 Diameter
     * https://github.com/wisdompeak/LeetCode/tree/master/BFS/1245.Tree-Diameter
     * 蠻多地方有證明 不過可能這個中文我最慨懂
     * A 開始能到最遠 B。 整張最遠是 ST, 要證明 B 必然是 ST 其中一點
     * 假設 B 不是 ST 任一點, 就會產生矛盾
     * case 1, AB 與 ST 不相交, ST 就是最長的,
     * - A 到 S or T 路上會經過 X, (完全不會經過B)
     * - A -> B
     * - A -> X -> S
     * - A -> X -> T
     * - 觀察 B -> A -> X -> S
     * - 因為 BA是 A能走到最遠的B 所以 BA > AS(AX+XT) && BA > AT(AX+XT)
     * - BA + AX + XS > 2*AX + XS + XT > 2*AX+ST > ST -> 違背 ST 最長定義
     * case 2, AB 與 ST 相交於 X
     * - A -> X -> B
     * - A -> X -> S
     * - A -> X -> T
     * - 因為 BA是 A能走到最遠的B, 所以會是 AX+XB > AX + XS, 即 XB > XS
     * - 所以 B -> X -> T => BX + XT > XS + XT -> 違背 ST 最長定義
     * - 因會實際上是 BS 是最長的
     * https://leetcode.com/problems/tree-diameter/solutions/418820/java-using-2-bfs/
     * 幾個小細節
     * 1. 節點數 n = int[][] edges.length
     * 2. 建立 List<Integer>[] adjs = new ArrayList[n];
     * 3. bfs 回傳 new int[2] = {nodeNum, dist}
     */
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adjs = new ArrayList[n];
        for (int i = 0; i < n; i++) adjs[i] = new ArrayList<>();
        for (int[] e : edges) {
            adjs[e[0]].add(e[1]);
            adjs[e[1]].add(e[0]);
        }

        int[] start = bfs(adjs, 0);
        return bfs(adjs, start[0])[1];
    }

    int[] bfs(List<Integer>[] adjs, int s) {
        Queue<int[]> bfs = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        bfs.offer(new int[]{s, 0});
        int[] res = null;
        while (bfs.size() > 0) {
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                int[] cur = bfs.poll();
                seen.add(cur[0]);
                res = cur;
                for (int v : adjs[cur[0]]) {
                    if (!seen.contains(v)) {
                        bfs.offer(new int[]{v, cur[1] + 1});
                        seen.add(v);
                    }
                }
            }
        }
        return res;
    }
}
