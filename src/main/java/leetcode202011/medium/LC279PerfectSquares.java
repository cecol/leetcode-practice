package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC279PerfectSquares extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC279PerfectSquares();
        var s = LC.numSquares(12);
    }

    public int numSquares(int n) {
        int[] dpN = new int[n + 1];
        Arrays.fill(dpN, -1);
        dpN[0] = 0;
        for (int i = 1; i < dpN.length; i++) {
            int p = 1;
            while (p * p <= n) {
                int j = p * p;
                if (i >= j && dpN[i - j] != -1) {
                    if (dpN[i] == -1) dpN[i] = dpN[i - j] + 1;
                    else dpN[i] = Math.min(dpN[i], dpN[i - j] + 1);
                }
                p++;
            }
        }
        log.debug("dpN: {}", dpN);
        return dpN[n];
    }
}
