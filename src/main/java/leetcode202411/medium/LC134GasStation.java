package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC134GasStation extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過 策略全忘
    // 1. 先看 SUM of gas > cost, 沒有就是 -1
    // 2. i = 0 to gas .length
    // 3. 記載 acc & curGas, 如果 curGas + acc < 0 代表起始站要換了 start = i+1, acc 重置
    // 4. 不然就 ACC 先沿用
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int s = 0;
        for (int i = 0; i < gas.length; i++) s = s + gas[i] - cost[i];
        if (s < 0) return -1;
        int start = 0;
        int acc = 0;
        for (int i = 0; i < gas.length; i++) {
            int curG = gas[i] - cost[i];
            if (acc + curG < 0) {
                start = i + 1;
                acc = 0;
            } else acc += curG;
        }
        return start;
    }
}
