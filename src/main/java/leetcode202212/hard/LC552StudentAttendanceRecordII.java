package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC552StudentAttendanceRecordII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC552StudentAttendanceRecordII();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Dynamic_Programming/552.Student-Attendance-Record-II/552.Student-Attendance-Record-II.cpp
     * 看似有很多狀態, 但其實都分開記載就沒這麼複雜
     * dp[n] 是由 dp[n-1] 再加一個字元組成
     * 但有很多子 dp_ij
     * i = 有幾個 A
     * j = 有幾個 L 在結尾
     * dp11 = 裏面有一個 A, 結尾剛好是 L
     * 然後 dp00[0] = 1 為初始條件
     * 為什麼沒有 dp01[0], dp02[0] .. 其他為 1?
     * 因為當下都沒有字元 怎會有 01?
     * 01是 ending == 'L', 可是根本都還沒有任何開始 根本不會有任何紀錄！！
     * 02是 ending == 'LL', 可是根本都還沒有任何開始 根本不會有任何紀錄！！
     * 11是 'A' + ending == 'L', 可是根本都還沒有任何開始 根本不會有任何紀錄！！
     * 所以只有 dp00[0] = 1 才是合理該開頭
     *
     * 接著就是各狀態遞迴
     * 1. dp00[i] = (dp00[i - 1] + dp01[i - 1] + dp02[i - 1]) % mod;
     * 沒有A, 沒有 L ending
     * 自然是 所沒有A 配上一個 P ending
     * 2. dp01[i] = dp00[i - 1];
     * 一個 L ending, 自然是 沒有 L ending 配上一個 L ending
     * 3. 同上
     * 4. dp10[i] = (dp00[i - 1] + dp01[i - 1] + dp02[i - 1] + dp10[i - 1] + dp11[i - 1] + dp12[i - 1]) % mod;
     * - 1個A 自然是所有沒有 A case 配上 A ending
     * - 1個A 自然是所有有 A case  配上 P ending
     * 5. dp11[i] = dp10[i - 1];
     * - 一個 A 又要 L ending, 自然只有 一個A 且 0 L ending 再加上一個 'A'
     * 6. 同上
     */
    public int checkRecord(int n) {
        long[] dp00 = new long[n + 1];
        long[] dp01 = new long[n + 1];
        long[] dp02 = new long[n + 1];
        long[] dp10 = new long[n + 1];
        long[] dp11 = new long[n + 1];
        long[] dp12 = new long[n + 1];
        dp00[0] = 1;
        long mod = (long) 1e9 + 7;
        for (int i = 1; i <= n; i++) {
            dp00[i] = (dp00[i - 1] + dp01[i - 1] + dp02[i - 1]) % mod;
            dp01[i] = dp00[i - 1];
            dp02[i] = dp01[i - 1];
            dp10[i] = (dp00[i - 1] + dp01[i - 1] + dp02[i - 1] + dp10[i - 1] + dp11[i - 1] + dp12[i - 1]) % mod;
            dp11[i] = dp10[i - 1];
            dp12[i] = dp11[i - 1];
        }
        return (int) ((dp00[n] + dp01[n] + dp02[n] + dp10[n] + dp11[n] + dp12[n]) % mod);
    }
}
