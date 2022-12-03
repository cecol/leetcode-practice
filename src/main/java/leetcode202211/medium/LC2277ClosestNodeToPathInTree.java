package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC2277ClosestNodeToPathInTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 沒想到這題是很直觀的
     * 1. 先找出路徑上的所有點 -> DFS
     * 2. 然後每個點下去 BFS
     * 竟然這樣不會 TLE, 應該也沒有更快的演算法
     * https://leetcode.com/problems/closest-node-to-path-in-tree/discuss/2204368/Java-DFS%2BBFS-with-comments
     *
     * 有些小細節要注意
     * 1. DFS 用 Set<Integer> 紀錄 path 就好, 不要用 List<Integer>, 然後下層有找到就是 沒找到再把自己移出 path
     * 2. BFS, 不是 Set<Integer> path 每個都下去跑一次, 會超慢, 是 destination node
     * bfs 下去看會先踩到 Set<Integer> path 任一點就是了
     * 一開始沒想到這招, 反過來找有效率多了
     *
     */
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            Set<Integer> path = new HashSet<>();
            dfs(graph, path, query[i][0], query[i][1]);
            res[i] = bfs(graph, path, query[i][2]);
        }
        return res;
    }

    int bfs(List<Integer>[] graph, Set<Integer> path, int d) {
        Queue<Integer> bfs = new LinkedList<>();
        bfs.offer(d);
        Set<Integer> seen = new HashSet<>();
        while (bfs.size() > 0) {
            Integer v = bfs.poll();
            seen.add(v);
            if(path.contains(v)) return v;
            for(Integer n: graph[v]) {
                if(!seen.contains(n)) {
                    bfs.offer(n);
                }
            }
        }
        return -1;
    }

    boolean dfs(List<Integer>[] graph, Set<Integer> path, int i, int j) {
        path.add(i);
        if (i == j) return true;
        for (Integer n : graph[i]) {
            if (!path.contains(n) && dfs(graph, path, n, j)) {
                return true;
            }
        }
        path.remove(i);
        return false;
    }
}
