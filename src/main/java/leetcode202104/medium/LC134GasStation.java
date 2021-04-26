package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC134GasStation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC134GasStation();
//        LC.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        LC.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3});
    }

    /**
     * https://leetcode.com/problems/gas-station/discuss/42667/Straightforward-Java-Linear-Solution-with-O(1)-space-explanation-and-Math-proof
     * 這個解釋比較直白
     * 這題有個假設很重要
     * 1. 只有一個答案, 就是說只要有解答, 那一定是某一點可以走回來, total gas > total cost
     * 2. 所以從該點走, total gas >= cost, 其他點走, 過程中gas會不足
     * 所以說
     * 1. 第一個loop 先把所有 gas 加起來 如果小於 all cost 就一定走不完, return -1;
     * 2. 過了第一個loop, 代表一定有解, 開始從 start = 0 offset往前找
     * -> 一開始 記載一個 start = 0, 假設起點是 0, loop 從 i -> n -1 走下去邊累積目前 gas - cost
     * -> 如果中途發生 累計gas < 0 -> 代表該點 start 到目前i 都不會是起點,因為中斷 , start 改成 i+1
     * -> 重頭累計, 然後往下一個i loop繼續
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for (int i = 0; i < cost.length; i++) tank += gas[i] - cost[i];
        if (tank < 0) return -1;
        int start = 0;
        int accu = 0;
        for (int i = 0; i < cost.length; i++) {
            int curGain = gas[i] - cost[i];
            if (accu + curGain < 0) {
                start = i + 1;
                accu = 0;
            } else accu += curGain;
        }
        return start;
    }
}
