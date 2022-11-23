package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC2246LongestPathWithDifferentAdjacentCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/discuss/1955433/JavaC%2B%2BPython-DFS-on-Tree
     * 這其實跟 LC687LongestUnivaluePath 很像
     * 只是這邊的樹有2個以上的 childs , 所以得每個都下去 dfs 遞迴然後找出最長的兩個
     * 這邊就得用 PriorityQueue 把最長兩個找出來
     * */
    int res = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) tree[parent[i]].add(i);
        dfs(tree, 0, s);
        return res;
    }

    int dfs(List<Integer>[] tree, int node, String s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> -x));
        for (int ch : tree[node]) {
            int cur = dfs(tree, ch, s);
            if (s.charAt(ch) != s.charAt(node)) pq.offer(cur);
        }
        int big1 = pq.size() > 0 ? pq.poll() : 0;
        int big2 = pq.size() > 0 ? pq.poll() : 0;
        res = Math.max(res, big1 + big2 + 1);
        return big1 + 1;
    }
}
