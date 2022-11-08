package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC815BusRoutes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC815BusRoutes();
    }

    /**
     * 一看就知道是BFS, 可是我的問題是如何把 int[][] route 做成 BFS解法?
     * 一開始想錯 以為是每台公車 自己建立 Set 裡面擠載走過的站點, 然後每台公車下去 BFS
     * https://leetcode.com/problems/bus-routes/discuss/122771/C%2B%2BJavaPython-BFS-Solution
     * 1. 其實是要記載每個站點有哪些公車有到, 用站點下去BFS, 其實這樣也才合理, 因為是從站點開始
     * 所以準備一個 Map<Integer, Set<Integer>> key: 站點, value: 這個站點有哪些公車i 經過過
     * 2. 然後BFS 不只記載站點, 也記載目前站點走過幾台公車
     * 3. 還要記載哪些站點走過了, 還有哪些公車搭過了
     * 4. BFS 過程很迂迴
     *    1. 拿出當前站點
     *    2. 用當前站點(stop2Bus)哪到哪些公車走過 (檢查公車走過沒)
     *    3. 用那些公車(routes)在找出下一個站點 (檢查站點走過沒)
     *    4. 加入站點到 BFS
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> stop2Bus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                if (!stop2Bus.containsKey(stop)) stop2Bus.put(stop, new HashSet<>());
                stop2Bus.get(stop).add(i);
            }
        }
        Queue<int[]> bfs = new LinkedList<>();
        bfs.offer(new int[]{source, 0});
        Set<Integer> seenStop = new HashSet<>();
        boolean[] seenBus = new boolean[n];
        while (!bfs.isEmpty()) {
            int[] p = bfs.poll();
            int stop = p[0], steps = p[1];
            if (stop == target) return steps;
            for (int bus : stop2Bus.get(stop)) {
                if (seenBus[bus]) continue;
                for (int nextStop : routes[bus]) {
                    if (seenStop.contains(nextStop)) continue;
                    seenStop.add(nextStop);
                    bfs.offer(new int[]{nextStop, steps + 1});
                }
                seenBus[bus] = true;
            }
        }
        return -1;
    }
}
