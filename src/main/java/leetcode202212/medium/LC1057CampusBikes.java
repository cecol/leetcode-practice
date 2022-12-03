package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class LC1057CampusBikes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1057CampusBikes();
    }

    /**
     * 其實這題沒有很難 還算很直觀
     * 就是把所有 worker vs bike 的組合都算出來, sorting 邏輯
     * order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
     * 因為 1 <= n <= m <= 1000 => 頂多 N log N , N == 1000,000
     * 但是用 PriorityQueue 來做會 TLE,
     * 改用 sort 會過, 更快的是用 counting sort
     * https://leetcode.com/problems/campus-bikes/discuss/305603/Java-Fully-Explained/699309
     * 會過的 sort
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = bikes.length, n = workers.length;
        int[][] tables = new int[m * n][3];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tables[idx++] = new int[]{dist(workers[i], bikes[j]), i, j};
            }
        }
        Arrays.sort(tables, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            else if (x[1] != y[1]) return x[1] - y[1];
            else return x[2] - y[2];
        });
        int[] res = new int[n];
        Arrays.fill(res, -1);
        HashSet<Integer> used = new HashSet<>();
        idx = 0;
        while (used.size() < n) {
            int[] current = tables[idx++];
            if(res[current[1]] != -1 || used.contains(current[2])) continue;
            res[current[1]] = current[2];
            used.add(current[2]);
        }

        return res;
    }

    int dist(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }
}
