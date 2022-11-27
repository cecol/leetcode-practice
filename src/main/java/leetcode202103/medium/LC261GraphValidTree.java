package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC261GraphValidTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC261GraphValidTree();
    }

    public boolean validTree(int n, int[][] edges) {
        int[] p = new int[n];
        int c = 0;
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] e : edges) {
            if (!u(p, e[0], e[1])) return false;
            c++;
        }
        return c == n - 1;
    }

    private int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    private boolean u(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi == pj) return false;
        p[pj] = pi;
        return true;
    }
}
