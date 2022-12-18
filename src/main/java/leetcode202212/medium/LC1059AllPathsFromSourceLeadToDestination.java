package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC1059AllPathsFromSourceLeadToDestination extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1059AllPathsFromSourceLeadToDestination();
    }

    /**
     * 找不到答案從哪找的了, 但是原文也沒什麼解釋
     * 其實這題有些陷阱 我們先看清楚題意, 我看很久都沒看懂 直到看到答案才理解
     * 1. 給定 source, 確保 source 所有 out degree 都能走到指定 destination
     * 2. 走到任何點 該點沒有 outDegree, 該點也是 destination, 但可能不是 題目指定的 destination -> return false
     * 3. source 到指定 destination is a finite number.
     * - The number of possible paths from source to destination is a finite number.
     * - 這是說中間沒 cycle
     *
     * 陷阱是指說
     * https://leetcode.com/problems/all-paths-from-source-lead-to-destination/solutions/466820/simple-dfs-with-explanation/
     * 像用 boolean[] visited 來看, 會 TLE
     *
     * 所以得用 int[] visited 來記載
     * c++ 這個有解釋 https://leetcode.com/problems/all-paths-from-source-lead-to-destination/solutions/2334472/c-solution-with-explanation/
     * 1. visited[i] == 0 初始值 未被訪過
     * 2. visited[i] == 1 正在拜訪中
     * 3. visited[i] == 2 已確定這條可以走到指定 destination
     *
     * 所以很直觀的就是 dfs 帶入
     * 1. List<Integer>[] graph 地圖長相
     * 2. visited 拜訪中狀態
     * 3. u - 正在拜訪的點
     * 4. d - 指定的 destination
     *
     * - if (visited[u] != 0) return visited[u] == 2;
     * -     正在拜訪的 u 已經拜訪過, 如果 visited[u] == 1, 就代表正在拜訪又拜訪回來 -> cycle -> return false
     * -     正在拜訪的 u 已經拜訪過, 如果 visited[u] == 2, 就代表可以走到 指定 destination, 直接回傳 true
     * - visited[u] = 1; - u 先設定成: 正在拜訪中
     * - if (u != d && graph[u].size() == 0) return false;
     * -     正在拜訪的 u 沒有 outDegree -> u 也是 destination, 但要驗證是否是 指定的 destination
     * -     所以若是 u == destination 會通過這個判斷去下一個, 下一個直接跳過 設定成 visited[u] = 2
     * - for (int v : graph[u]) if (!dfs(graph, visited, v, d)) return false;
     * -     u 繼續往下走看看, (這時候 visited[u] 還是 1; 還是拜訪中, 如果 dfs 又走回 u, 就會 return false)
     * -     看看所有 outDegree 是否都是走到 指定的 destination
     * - visited[u] = 2; 拜訪完且都是走到 指定的 destination
     * - return true
     * */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) graph[e[0]].add(e[1]);
        int[] visited = new int[n];
        return dfs(graph, visited, source, destination);
    }

    boolean dfs(List<Integer>[] graph, int[] visited, int u, int d) {
        if (visited[u] != 0) return visited[u] == 2;
        visited[u] = 1;
        if (u != d && graph[u].size() == 0) return false;
        for (int v : graph[u]) if (!dfs(graph, visited, v, d)) return false;
        visited[u] = 2;
        return true;
    }
}
