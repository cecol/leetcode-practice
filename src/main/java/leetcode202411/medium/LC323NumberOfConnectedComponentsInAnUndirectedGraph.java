package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC323NumberOfConnectedComponentsInAnUndirectedGraph extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解 union find 前面解過 就完全很直白
    public int countComponents(int n, int[][] edges) {
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int[] e : edges) {
            u(p, e[0], e[1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) if (p[i] == i) res++;
        return res;
    }

    int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return p[i];
    }

    void u(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi == pj) return;
        p[pj] = pi;
    }
}
