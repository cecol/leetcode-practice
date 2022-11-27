package leetcode202211.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BiWeeklyContest92 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new BiWeeklyContest92();
        LC.countPalindromes("0000000");

    }

    public int numberOfCuts(int n) {
        int[] cuts = new int[101];
        cuts[1] = 0;
        cuts[2] = 1;

        for (int i = 3; i <= 100; i++) {
            if (cuts[i] != 0) continue;
            if (i % 2 == 1) {
                cuts[i] = i;
                if (i * 2 <= 100) cuts[i * 2] = i;
            } else cuts[i] = cuts[i / 2] * 2;
        }
        return cuts[n];
    }

    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[] rowOne = new int[m];
        int[] colOne = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) rowOne[i] += grid[i][j];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) colOne[i] += grid[j][i];
        }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = rowOne[i] + colOne[j] - (m - rowOne[i]) - (n - colOne[j]);

        return grid;
    }


    public int bestClosingTime(String s) {
        int n = s.length();
        int[] yc = new int[n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            c += s.charAt(i) == 'Y' ? 1 : 0;
            yc[i] = c;
        }
        int p = n;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int pi = ((s.charAt(i) == 'Y' ? 1 : 0) + (i - (i == 0 ? 0 : yc[i - 1]))) + (yc[n - 1] - yc[i]);
            if (pi < p) {
                idx = i;
                p = pi;
            }
        }
        int pn = n - yc[n - 1];
        if (pn < p) idx = n;
        return idx;
    }

    public int countPalindromes(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i + 4 < n; i++) {
            for (int j = i + 4; j < n; j++) {
                if (s.charAt(i) != s.charAt(j)) continue;
                for (int k = i + 1; k < j - 2; k++) {
                    for (int l = j - 1; l > k + 1; l--) {
                        log.debug("{} {} {} {}", i, k, l, j);
                        if (s.charAt(k) == s.charAt(l)) res++;
                    }
                }
            }
        }
        return res;
    }
}
