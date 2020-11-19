package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC935KnightDialer extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC935KnightDialer();
        var s1 = LC.knightDialer2(2);
        var s2 = LC.knightDialer2(3);
        var s3 = LC.knightDialer2(4);
        var s4 = LC.knightDialer2(3131);
    }

    public int knightDialer(int n) {
        if (n == 0) return 0;
        if (n == 1) return 10;
        int mod = (int) Math.pow(10, 9) + 7;
        int s = 0;
        int[][] directions = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        int[][] allInit = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}, {3, 1}};
        for (int[] init : allInit) {
            int[][][] kd = new int[n][4][3];
            kd[0][init[0]][init[1]] = 1;
            for (int i = 1; i < n; i++) {
                for (int mx = 0; mx < 4; mx++)
                    for (int my = 0; my < 3; my++) {
                        if (mx == 3 && my == 0) continue;
                        if (mx == 3 && my == 2) continue;
                        for (int[] dir : directions) {
                            int preX = mx - dir[0];
                            int preY = my - dir[1];
                            if (preX >= 0 && preY >= 0 && preX < 4 && preY < 3) {
                                kd[i][mx][my] += kd[i - 1][preX][preY];
                                if (kd[i][mx][my] > mod) kd[i][mx][my] %= mod;
                            }
                        }
                    }
            }
            for (int[] i2 : kd[n - 1])
                for (int i1 : i2) {
                    s += i1;
                    if (s > mod) s %= mod;
                }
        }
        log.debug("s: {}", s);
        return s;
    }

    /**
     * https://www.youtube.com/watch?v=HTnIFivp0aw&ab_channel=HuaHua
     * special solution https://zhanghuimeng.github.io/post/leetcode-935-knight-dialer/
     */
    public int knightDialer2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 10;
        int mod = (int) Math.pow(10, 9) + 7;
        int s = 0;
        int[][] preM = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int[][] kd = new int[n][10];
        Arrays.fill(kd[0], 1);
        for (int i = 1; i < n; i++) {
            for (int move = 0; move < preM.length; move++) {
                for (int p : preM[move]) {
                    kd[i][move] += kd[i - 1][p];
                    kd[i][move] %= mod;
                }
            }
        }
        for (int ms : kd[n - 1]) {
            s += ms;
            s %= mod;
        }
        log.debug("s: {}", s);
        return s;
    }
}
