package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Set;
import java.util.TreeSet;

public class LC323NumberOfConnectedComponentsInAnUndirectedGraph extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/516491/Java-Union-Find-DFS-BFS-Solutions-Complexity-Explain-Clean-code
     * 直觀可以用 union find 解
     * 但其實 BFS 也是可以
     * */
    public int countComponents(int n, int[][] edges) {
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] e : edges) {
            u(p, e[0], e[1]);
        }
        Set<Integer> ss = new TreeSet<>();
        for (int i : p) ss.add(f(p,i));
        return ss.size();
    }

    int f(int[] p, int i) {
        while (i != p[i]) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    void u(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi != pj) p[pj] = pi;
    }
}
