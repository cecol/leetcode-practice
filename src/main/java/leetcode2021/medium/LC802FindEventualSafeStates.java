package leetcode2021.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC802FindEventualSafeStates extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC802FindEventualSafeStates();
        var s = LC.eventualSafeNodes(new int[][]{});
    }

    /**
     * https://leetcode.com/problems/find-eventual-safe-states/discuss/119871/Straightforward-Java-solution-easy-to-understand!
     * 每一個節點都給標記: 0 -> 尚未拜訪, 1 -> 安全(terminal or got terminal), 2 -> 不安全(cycle or go to cycle)
     * 建立一個每個節點的標記紀錄 int[] mark
     * 每個節點下去走DFS, DFS會回傳 true -> terminal, false -> cycle
     * DFS中就拿出該節點可以走向其他節點 -> 再去走DFS,
     * DFS遞迴的終止點: mark !=0 -> 直接回傳是safe -> 1, unsafe -> 2
     * */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;

        int n = graph.length;
        // be used to mark that node is: 0 un-visited, 1 safe(terminal or bound to terminal), 2 unsafe(cycle or bound to cycle)
        int[] mark = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, mark)) res.add(i);
        }
        return res;
    }

    private boolean dfs(int[][] graph, int i, int[] mark) {
        if (mark[i] != 0) return mark[i] == 1;
        mark[i] = 2;
        for (int next : graph[i]) if (!dfs(graph, next, mark)) return false;
        mark[i] = 1;
        return true;
    }
}
