package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC779KthSymbolInGrammar extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC779KthSymbolInGrammar();
    }

    /**
     * - 1 0 - 1
     * - 2 01 - 2
     * - 3 0110 - 4
     * - 4 01101001 - 8
     * - 其實看 3 [0110] 跟 2[01] 的關係
     * 其實這很像 complete binary tree, parent id = (left/right child id)/2
     * <p>
     * 3 的 1/2 位置 是 2 的 1 生成
     * 3 的 3/4 位置 是 2 的 2 生成
     * <p>
     * 所以 求 kthGrammar(3, 1 or 2) 就是求 kthGrammar(2, 1)
     * 所以 求 kthGrammar(3, 3 or 4) 就是求 kthGrammar(2, 2)
     * <p>
     * 所以就是求 kthGrammar(3-1, (k+1)/2)
     * <p>
     * 所以看 kthGrammar(n, k) 層 一定是 kthGrammar(n-1, (k+1)/2) 層決定
     * 如果 n-1 層是回傳 0, n 層 kth 位置就是
     * 0 生成 01, k%2 == 0 就是在 [0]1
     * 0 生成 01, k%2 == 1 就是在 0[1]
     * <p>
     * 如果 n-1 層是回傳 1, n 層 kth 位置就是
     * 1 生成 10, k%2 == 0 就是在 0[1]
     * 1 生成 10, k%2 == 1 就是在 [0]1
     */
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        if (n == 2) return k == 1 ? 0 : 1;
        int v = kthGrammar(n - 1, (k + 1) / 2);
        if (v == 0) {
            return k % 2 == 0 ? 1 : 0;
        } else {
            return k % 2 == 0 ? 0 : 1;
        }
    }
}
