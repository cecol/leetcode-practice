package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1039MinimumScoreTriangulationOfPolygon extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1039MinimumScoreTriangulationOfPolygon();
        var s = LC.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5});
        var s2 = LC.minScoreTriangulation(new int[]{3, 7, 4, 5});
    }

    /**
     * https://leetcode.com/problems/minimum-score-triangulation-of-polygon/discuss/363482/Intuitive-Java-Solution-With-Explanation
     * First observe that we only stop drawing inner edges, when we cannot form any more triangels.
     * Obviously every such triangle has 1 edge from the given polygon or 2 edges.
     * That means, the stop condition is all edges from the polygon are covered.
     * 每一個三角形必然用到多邊形的一邊or2邊, 所有的三角形會剛好用完多邊形的每一邊各一次
     *
     * Also observe that it doesn't matter which triangle you are trying first.
     * Ultimately, if it is part of optimal configuration, it will end up in the final solution
     * (i.e. doesn't matter which subproblem formed that triangle, this will be more clear with the below pic).
     * 多邊形的某一邊其形成的多種三角形中, 定會有一個是最佳化的case的其中一種三角形,(畢竟該邊也是要劃入三角形的一部分),
     * 所以直接以 [0, n-1] 為開始尋找最佳化的三角形的邊
     * 列舉任其他任何點[1 to n-2]能與[0, n-1]的case, 來找出會佳化結果, 就是答案了
     *
     */
    public int minScoreTriangulation(int[] A) {
        if (A == null || A.length == 0) return 0;
        int[][] dp = new int[A.length][A.length];
        int res = mst(A, dp, 0, A.length - 1);
        log.debug("res: {}", res);
        return res;
    }

    public int mst(int[] a, int[][] dp, int begin, int end) {
        if ((end - begin + 1) < 3) return 0; // cannot form a triangle
        if(dp[begin][end] != 0) return dp[begin][end];
        int res = Integer.MAX_VALUE;
        for (int i = begin + 1; i < end; i++) { //開始列舉所有其他點與 [being, end] 形成之三角形 case
            int l = mst(a, dp, begin, i); // 左三角
            int r = mst(a, dp, i, end); // 又三角
            res = Math.min(res, l + r + (a[begin] * a[end] * a[i]));
        }
        dp[begin][end] = res;
        return res;
    }
}
