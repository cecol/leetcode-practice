package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC818RaceCar extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC818RaceCar();
    }


    /**
     * https://leetcode.com/problems/race-car/solutions/123834/java-c-python-dp-solution/
     * 可以注意到一路加速走的距離是 1 -> 2 -> 4 -> 8, 2的冪次累加
     * 比如 target = 7 最快就是 1+2+4=7 一路向前沒回頭就達成
     * 就是 target bits length = 3 -> 2^3 - 1, 所以最短指令就是 3 = 'AAA'
     *
     * 所以要最短指令達成
     * 1. 一路達到 就是 target 剛好是 2^n-1, 其中 n 是 target 的 bits 數目
     * - 就是 if (1 << n == t + 1) 就是在表達這個到 target 最短指令長度 == n
     * 2. 中間有回頭 代表 target 在區間 2^(n-1)-1 < target < 2^n-1
     * -      可以先過頭到達 2^n-1, 然後回頭, 所以指令長度就是 n+1 -> 'A..AR'
     * -      回頭就可以遞迴, 看剩下的還有多長指令, 回頭要走的就是 2^n - 1 - target
     * -      2^n 代表目前走超過 target 到 2^n - 1 位置, 然後要頹頭走到 target, 所以要走的距離就是兩者差距
     * -      所以是 (2^n -1) - target
     * -      比如說 target = 14, 我們就會先走到 15, 15 距離 14 只有 1, 所以要看 dp[1] 時候的最短指令
     * -      就是 2^4 - 1 - 14 = 1, 其中 4 就是 14 bits 數
     * -      這時候不用管正反向, 因為 n+1 -> 'A..AR' 這邊已經把反向 R 加來, 下一層遞迴 根本不用管
     * -  但也可以中途反向 不一定要走超過才反向 反向走一段再反向
     * -      所以先走 n-1 個指令 , 反向, 走 m 個指令, 再反向,  遞迴剩下距離走到 target
     * -      低消會是 (n-1) + 1反向 + 回頭 m 個指令, 1反向 = n + m + 1
     * -      剩下距離就是 target - 2^(n-1): 原本走到 2^(n-1), 剩下距離是 2^(n-1) to target
     * -                 但又回頭走 m 指令 所以又補上 2^m 距離
     * -                 總結遞迴的剩下距離是 target - 2^(n-1) + 2^m
     * -                 所以 m 能從 0 to (n - 1) - 1 都下去嘗試看看有沒有更短
     * */
    int[] dp = new int[10001];

    public int racecar(int t) {
        if (dp[t] > 0) return dp[t];
        int n = (int) (Math.log(t) / Math.log(2)) + 1;
        if (1 << n == t + 1) {
            dp[t] = n;
        } else {
            dp[t] = racecar((1 << n) - 1 - t) + n + 1;
            for (int m = 0; m < n - 1; ++m) {
                dp[t] = Math.min(dp[t],
                        racecar(t - (1 << (n - 1)) + (1 << m))
                                + n + m + 1);
            }
        }
        return dp[t];
    }
}
