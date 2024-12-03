package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC815BusRoutes extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. routes[i] 是 i bus 可以經過哪些站點
    // 2. bfs 放入的是 {站點, 走到這個站點的 count}
    // 3. 記載 Map 站點可以 碰到哪些 bus
    // 4. 記載看過的站點 && 看過的 bus, bfs 期間要 continue
    // 5. bfs 中 站點看到哪些 bus-i, 在看 route[bus-i] 可以走到哪些站點 -> bfs next round
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> stop2bus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                if (!stop2bus.containsKey(stop)) stop2bus.put(stop, new HashSet<>());
                stop2bus.get(stop).add(i);
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
            for (int bus : stop2bus.getOrDefault(stop, new HashSet<>())) {
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
