package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC547NumberOfProvinces extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC547NumberOfProvinces();
        var s = LC.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
    }


    /**
     * https://leetcode.com/problems/friend-circles/discuss/101336/Java-solution-Union-Find
     * 2021/3/31 發現改題了！！
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) union(p, i, j);
            }
        }
        for (int i = 0; i < n; i++) if (p[i] == i) res++;
        return res;
    }

    private int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    private void union(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi == pj) return;
        p[pj] = pi;
    }
}
