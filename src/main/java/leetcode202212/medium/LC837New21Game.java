package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC837New21Game extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC837New21Game();
    }

    /**
     * 看了看 好像有理解 但下次再遇到可能還是不熟
     * 超不直觀,
     * 基本上是 21 點遊戲, 可以看成 n=21,k=17,maxPts = 1 to 13(撲克牌只有 1 to 13數字)
     * 就是說莊家通常抽到 17 就停了以免爆炸, 所以莊家要一直抽到 17, 但還沒有超過 21 點的機率
     * <p>
     * 看個範例
     * maxPts = 3, 只有 1,2,3 可以抽, k = 5, 求 p(5) 機率
     * - If you had a 4 and you get a 1: prob(4) * (1/3) -> 本來就有4的機率 * 再抽一張 1: (1/3) 機率
     * - If you had a 3 and you get a 2: prob(3) * (1/3) -> 本來就有3的機率 * 再抽一張 2: (1/3) 機率
     * - If you had a 2 and you get a 3: prob(2) * (1/3) -> 本來就有2的機率 * 再抽一張 3: (1/3) 機率
     * (如果你有 1, 不可能下一張牌抽到會拿你加到5點, 所以機率是 0)
     * 整體機率是 prob(5) = p(4)/3 + p(3)/3 + p(2)/3
     * <p>
     * general func:
     * p(K) = p(K-1)/maxPts + p(K-2)/maxPts + p(K-3)/maxPts + ... p(K-W)/maxPts
     * 更簡化成
     * Wsum = p(K-1) + .. + p(K-W)
     * p(K) = Wsum / maxPts
     * <p>
     * 所以在 k 之前 Wsum 一直累加, dp[i] = 當前累加 Wsum / maxPts
     * - if (i < k) Wsum += dp[i];
     * - 但是 k 之後就不會再抽牌了 !!, 所以 k 之後 Wsum 不會在累加
     * <p>
     * - 然後 Wsum 在一定距離後也要把前面減回來
     * - if (i - maxPts >= 0) Wsum -= dp[i - maxPts];
     * - 目前 i = 5, maxPts = 3, p(5) 只會有 p(2)+3,p(3)+2,p(4)+1 組成, 前面的 p(1), p(2) Wsum要扣回來
     * - 上面例子也是解釋為什麼要維持 Wsum window, 因為 Wsum 是從 1 累計到 k, 如果開始超界, 前面的機率降變0, 所以得扣掉
     * <p>
     * 總結 p(K) = Wsum / maxPts
     * 但 Wsum 只有在 k 前累加, k 後不抽牌
     * 然後 i 超過 maxPts, Wsum 要把前面扣回來
     *
     * 所以可以分成兩段
     * 1. i = 1 to k, 累加 Wsum,
     * - 但如果 i >= maxPts, Wsum 要減去前面
     * 2. i = k to n
     * - 收集 機率
     * - 但如果 i >= maxPts, Wsum 要減去前面 -> 越後面機率越小
     *
     * 不過為什麼 i = k to n 才加總結果機率? 不是 i = 0 to n 加總?
     * 我在想因為是抽到 k 才停, 才開始有機率出來, 不然都在抽的過程, 根本沒機率可言
     */
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n > k + maxPts) return 1d;
        double dp[] = new double[n + 1], Wsum = 1d, res = 0d;
        dp[0] = 1;

        for (int i = 1; i < k; i++) {
            dp[i] = Wsum / maxPts;
            Wsum += dp[i];
            if (i >= maxPts) Wsum -= dp[i - maxPts];
        }

        for (int i = k; i <= n; i++) {
            dp[i] = Wsum / maxPts;
            res += dp[i];
            if (i - maxPts >= 0) Wsum -= dp[i - maxPts];
        }
        return res;
    }
}
