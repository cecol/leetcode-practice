package leetcode202105.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1411NumberOfWaysToPaintNx3Grid extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1411NumberOfWaysToPaintNx3Grid();
    }

    /**
     * 看得出是DP 但一直沒看出解題pattern
     * 有數學公式上的秒解, 我覺得要腦袋夠清晰才可以很快解出來
     * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574923/JavaC%2B%2BPython-DP-O(1)-Space
     * 很直白解釋， 但沒有很 generalize
     * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574943/Java-Detailed-Explanation-with-Graph-Demo-DP-Easy-Understand
     * 有明白解釋
     * 1. 每一個row 只有兩種組合, 3種顏色組合, 2種顏色組合
     * 2. 我們一個一個row增加下去, 每一次增加只參考前一個row
     * 3. 3色組合可以產生 2個3色+2個2色的, 2色組合可以產生 2個3色+3個2色的
     * 4. 3色組合S(n), 2色組合T(n)
     * 5. S(n+1) = 2*S(n) + 2*T(n)
     * 6. T(n+1) = 2*S(n) + 3*T(n)
     * 我覺得最關鍵就是我沒想出來, 每一層是前一層的 2/3色組合的概念下去想
     * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574912/JavaC%2B%2B-DFS-Memoization-with-Picture-Clean-code
     * 這個解法更直覺, 直接用 int[n+1][4][4][4] 來走過各種可能, 我一開始有試著想, 但沒有好結果
     * 1. dfs 下去解
     * dfs(n, a0, b0, c0) is the number of ways to color the first n rows of the grid
     * keeping in mind that the previous row (n + 1) has colors a0, b0 and c0
     */
    public int numOfWays(int n) {
        if (n == 1) return 12;
        int m = 1000000007;
        long n3 = 6;
        long n2 = 6;
        for (int i = 2; i <= n; i++) {
            long n33 = 2 * n2 + 2 * n3;
            long n22 = 2 * n3 + 3 * n2;
            n3 = n33 % m;
            n2 = n22 % m;
        }
        return (int)((n3 + n2) % m);
    }
}
