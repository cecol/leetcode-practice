package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC733FloodFill extends BasicTemplate {
    public static void main(String[] args) {
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0) return null;
        boolean[][] v = new boolean[image.length][image[0].length];
        ff(image, sr, sc, color, image[sr][sc], v);
        return image;
    }

    void ff(int[][] g, int i, int j, int c, int s, boolean[][] v) {
        int n = g.length, m = g[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || v[i][j] || g[i][j] != s) return;
        v[i][j] = true;
        g[i][j] = c;
        ff(g, i + 1, j, c, s, v);
        ff(g, i - 1, j, c, s, v);
        ff(g, i, j + 1, c, s, v);
        ff(g, i, j - 1, c, s, v);
    }
}
