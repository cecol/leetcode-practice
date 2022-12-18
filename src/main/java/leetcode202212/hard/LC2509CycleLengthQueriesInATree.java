package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC2509CycleLengthQueriesInATree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2509CycleLengthQueriesInATree();
    }

    /**
     * 2022/12/18 contest 324
     * 想不到第四題這麼簡單... 我花第三題花太久導致第四題完全沒時間看
     * 結果回頭看是 秒解 ...
     * 很直觀的一題, 完全沒難度...
     * */
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int len = queries.length;
        int[] res = new int[len];
        int idx = 0;
        for (int[] q : queries) {
            int r1 = q[0];
            int r2 = q[1];
            int r1l = 0;
            int r2l = 0;
            while (r1 != r2) {
                if (r1 > r2) {
                    r1 /= 2;
                    r1l++;
                } else {
                    r2 /= 2;
                    r2l++;
                }
            }
            res[idx++] = r1l + r2l + 1;
        }
        return res;
    }
}
