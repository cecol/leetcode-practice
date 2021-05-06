package leetcode202105.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1074NumberOfSubmatricesThatSumToTarget extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1074NumberOfSubmatricesThatSumToTarget();
    }

    /**
     * 這題我有想過是 prefix sum, 但有不少 corner case 我沒想通
     * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/discuss/303750/JavaC%2B%2BPython-Find-the-Subarray-with-Target-Sum
     * 我知道很類似 https://leetcode.com/problems/subarray-sum-equals-k/description/ 這題
     * 但我沒有想法怎麼把此題改成跟他一樣
     * 1. 先把每一個 row 算 prefixSum
     * 2. 然後對每一個 column pair 算 prefix sum
     * 3. 計算 rows 的prefix sum (配上當前的 i, j column pair)
     * 這樣就達成 prefix Sum 概念
     * 1. 我沒想到要切成這個樣子
     * 2. 每次 column pair 應當 clean counter map
     *
     * mock解到這題讓我理解有幾個地方準備不足
     * 1. prefixSum 太久沒複習 花了時間去回想 以致這題花更多時間去思考 後續思考也更混亂
     * 2. 後續思考時候沒有想清楚 各種 sub matrix 應當要分開計算 prefix sum count, 所以一直用同一個map, 所以道指很多錯誤重複計算
     * -> 但也沒想出要怎麼解決, 看到答案才理解就是每個 column 形成的 sub matrix 都得重新計算 count
     * -> 我一直以為 matrix[i][j] 就包含了裡面所有的 prefix Sum, 雖然後來有想到 錯誤重複計算 counter
     * -> 但也沒猜想出應當 每個 column pair的計算方式
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int res = 0, m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) for (int j = 1; j < n; j++) matrix[i][j] += matrix[i][j - 1];
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    res += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
