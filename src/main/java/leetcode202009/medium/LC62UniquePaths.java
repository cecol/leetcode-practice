package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC62UniquePaths extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC62UniquePaths();
        var r = LC.uniquePaths(51, 9);
        System.out.println(r);
    }

    public int uniquePaths(int m, int n) {
        int[] t = new int[]{0};
        pathPerm(m - 1, n - 1, t, 0);
        return t[0];
    }

    public void pathPerm(int m, int n, int[] t, int d) {
        if (m == 0 && n == 0) {
            t[0]++;
        } else {
            if (m > 0) pathPerm(m - 1, n, t, d + 1);
            if (n > 0) pathPerm(m, n - 1, t, d + 1);
        }
    }
}
