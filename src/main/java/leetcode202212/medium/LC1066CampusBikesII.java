package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1066CampusBikesII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1066CampusBikesII();
    }

    /**
     * 不知道這題跟 Bipatite 有什麼關係
     * https://leetcode.com/problems/campus-bikes-ii/solutions/303422/python-priority-queue/
     * 看解答還算時蠻直觀的 Dijsktra(bfs + priority queue) + multi state
     * <p>
     * Node 記載
     * 1. 當前 worker i 編號
     * 2. mask - bitmap 有哪些 車 被分配了, offset i = 1, 代表 bikes[i] 已經被分配了
     * 3. 到當前 worker i 配上已分配的 mask bitmap, 已經花了多少 cost
     * -> bfs + priority queue 比較對象
     * <p>
     * 接下來就是很直觀的
     * 每一次 bfs, 都是每台腳踏車下去看, 如果在 mask is off, 代表可以分配, 就分配給剛前 worker i
     * 初始 worker = 0, 配上 mask = 0, 代表就是把所有 未分配腳踏車嘗試 分配給 worker 0, 然後看看後續
     * 接下來 把所有 未分配腳踏車嘗試 分配給 worker 1, 然後看看後續 ...
     * <p>
     * 因為是 min heap, 所以所有組合中 min cost 會優先被選出來
     * 配上  Set<String> seen,  String key = "$" + cur.worker + "$" + cur.mask; 去重
     * 只要一路 bfs 看到 if (cur.worker == workers.length) return cur.cost; 就代表目前分配完 worker,
     * 加上 min heap 特性, 可以確認是 min cost, 可以回傳
     */
    public int assignBikes(int[][] workers, int[][] bikes) {
        Queue<Node> bfs = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        Set<String> seen = new HashSet<>();
        bfs.offer(new Node(0, 0, 0));
        while (bfs.size() > 0) {
            Node cur = bfs.poll();
            String key = "$" + cur.worker + "$" + cur.mask;
            if (seen.contains(key)) continue;
            seen.add(key);
            if (cur.worker == workers.length) return cur.cost;
            for (int j = 0; j < bikes.length; j++) {
                if ((cur.mask & (1 << j)) == 0) {
                    bfs.offer(new Node(cur.worker + 1,
                            cur.mask | (1 << j),
                            cur.cost + dist(bikes[j], workers[cur.worker])));
                }
            }
        }
        return -1;
    }

    class Node {
        int worker;
        int mask;
        int cost;

        Node(int w, int m, int cost) {
            this.worker = w;
            this.mask = m;
            this.cost = cost;
        }
    }

    int dist(int[] b, int[] w) {
        return Math.abs(b[0] - w[0]) + Math.abs(b[1] - w[1]);
    }
}
