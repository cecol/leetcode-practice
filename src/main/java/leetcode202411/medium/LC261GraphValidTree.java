package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC261GraphValidTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 這是考 union find
    // 1. 一開始每個人都是自己的爸爸
    // 2. 走遍 edges, 按理看到爸爸都不會一樣, 關聯到 root 爸爸
    // 3. 如果找到一半有誰 爸爸一樣, 代表就是發生已走過 -> 迴圈, 不是 tree
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

    int f(int[] p, int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    boolean u(int[] p, int i, int j) {
        int pi = f(p, i);
        int pj = f(p, j);
        if (pi == pj) return false;
        p[pj] = pi;
        return true;
    }

}
