package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

public class LC975OddEvenJump extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC975OddEvenJump();
    }

    /**
     * https://leetcode.com/problems/odd-even-jump/discuss/217974/Java-solution-DP-%2B-TreeMap
     * 這題是 TreeMap 配上 DP, 這種是否能跳到最後的問題看起來都是從尾巴 DP 解回去
     *
     * 題意是, 你要跳 1,2,3,4,5,6,7 步數時候
     * 但跳的是 odd, 1,3,5,7 時候 只能往後跳到比自己大的值中最小值
     * 但跳的是 even, 2,3,4,6 時候 只能往後跳到比自己小的值中最大值
     * 如果有多個值符合 Ex: odd 步數, 往後 offset 有比自己大的值中最小值 有3個, 選 offset 最小的
     * 計數從哪一個 offset 開始可以跳到最後?
     *
     * TreeMap Key記載從尾巴 n-1 開始 loop 當前看到的 arr[i], value 是 offset
     * DP[n][2]
     * DP[i][0] 記載 i 可以 odd 跳到 n-1
     * DP[i][1] 記載 i 可以 even 跳到 n-1
     *
     * 所以到當前 i 時候, TreeMap 已記載 offset i 之後的 offset j 所有值 TreeMap{arr[j] -> j}
     * 所以找 TreeMap.ceilingKey(arr[i]) 來找前面是否有 offset j 值 arr[j] >= arr[i]
     * 如果有 dp[i][0] = dp[TreeMap.get(arr[j])][1];
     * 因為有 j, 且 j 也被檢查過是否能跳到 n-1, 所以如果 i 到 j 可以透過 odd 跳
     * 那麼 i 到 n-1 就是 j 到 n-1 的 even 跳 (i -odd-> j -even-> n-1 )
     *
     * 反之 dp[i][1] = dp[TreeMap.get(arr[j])][0]; 也是同理
     *
     * 最後因為是看出發點能否到達 n-1, 出發第一步是 odd, 所以算完後當前 dp[i][0] == true 就可以計數
     */
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        boolean[][] dp = new boolean[n][2];
        dp[n - 1] = new boolean[]{true, true};
        tm.put(arr[n - 1], n - 1);
        int res = 1;
        for (int i = n - 2; i >= 0; i--) {
            Integer nextGreater = tm.ceilingKey(arr[i]);
            if (nextGreater != null) {
                dp[i][0] = dp[tm.get(nextGreater)][1];
            }

            Integer nextSmaller = tm.floorKey(arr[i]);
            if (nextSmaller != null) {
                dp[i][1] = dp[tm.get(nextSmaller)][0];
            }
            tm.put(arr[i], i);

            res += dp[i][0] ? 1 : 0;
        }
        return res;
    }
}
