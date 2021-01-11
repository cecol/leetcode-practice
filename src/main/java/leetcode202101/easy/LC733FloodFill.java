package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC733FloodFill extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC733FloodFill();
        var s = LC.floodFill(new int[][]{{0, 0, 0}, {0, 1, 1}}, 1, 1, 1);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] v = new boolean[image.length][image[0].length];
        dfs(v, image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    private void dfs(boolean[][] v, int[][] i, int s, int e, int n, int o) {
        v[s][e] = true;
        i[s][e] = n;
        if (s - 1 >= 0 && i[s - 1][e] == o && !v[s - 1][e]) dfs(v, i, s - 1, e, n, o);
        if (s + 1 < i.length && i[s + 1][e] == o && !v[s + 1][e]) dfs(v, i, s + 1, e, n, o);
        if (e - 1 >= 0 && i[s][e - 1] == o && !v[s][e - 1]) dfs(v, i, s, e - 1, n, o);
        if (e + 1 < i[0].length && i[s][e + 1] == o && !v[s][e + 1]) dfs(v, i, s, e + 1, n, o);
    }
}
