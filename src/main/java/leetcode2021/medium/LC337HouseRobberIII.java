package leetcode2021.medium;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LC337HouseRobberIII extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC337HouseRobberIII();
        var s = LC.rob(null);
    }

    /**
     * https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
     * 給一個起點:root
     * 偷root -> 不能偷 root.left & root.right
     * 不偷root -> 可以偷 root.left & root.right
     * 所以可以遞迴分解成
     * int noRobRoot = rob(r.left) + rob(r.right); -> 不偷root, 改偷兒子 -> 遞回下去
     * int robRoot = root.val + rob(r.left.left) + rob(r.left.right) + rob(r.right.left) + rob(r.right.right)
     * -> 偷root, 兒子不能偷, 但可以偷孫子
     * 但是偷孫子層的(rob(r.left.left) + rob(r.left.right) + rob(r.right.left) + rob(r.right.right))
     * 其實在算'不偷root改偷兒子' case 時候時也重複算過, 所以需要多一個Map來記錄已算過的case
     * -> DP結構就是 Map<TreeNode, Integer> -> key: 已算過的sub-problem: 就是偷子樹最佳case的該子樹reference
     * -> value: 已算過的sub-problem: 就是偷子樹最佳case的結果
     */
    public int rob(TreeNode root) {
        return subRob(root, new HashMap<>());
    }

    private int subRob(TreeNode r, Map<TreeNode, Integer> m) {
        if (r == null) return 0;
        if (m.containsKey(r)) return m.get(r);
        int noRobRoot = subRob(r.left, m) + subRob(r.right, m);

        int robRoot = 0;
        if (r.left != null) robRoot += subRob(r.left.left, m) + subRob(r.left.right, m);
        if (r.right != null) robRoot += subRob(r.right.left, m) + subRob(r.right.right, m);
        robRoot += r.val;
        int res = Math.max(noRobRoot, robRoot);
        m.put(r, res);
        return res;
    }

    /**
     *  最佳解法, 上面的解法其實途中放棄了某些有用資訊
     *  subRob 只回傳從該node開始偷的最佳結果, 但不知道是該node有偷or未偷而產生的最佳結果
     *  ex: int noRobRoot = subRob(r.left, m) + subRob(r.right, m);
     *  只知道沒偷root是拿左兒子右兒子遞迴下去的結果, 但其中包含了其實可能左兒子右兒子有偷or沒偷的可能性
     *  如果我們都能把每一個sub-problem的該root有偷沒偷的結果都存下來 那麼就可以拿來參考
     *  目前這一層該如何判斷有偷沒偷
     *  所以每一個子問題題都要回int[2] -> int[0] == 沒偷, int[1] == 有偷
     *  然後當前的root就可以
     *  偷當前的root -> 那一定是從子樹問題的沒偷子樹來算出來的
     *  不偷當前的root -> 那就得從, 不偷子樹&偷子樹中找出max
     */
    private int[] robSubOpt(TreeNode r) {
        if (r == null) return new int[2];

        int[] left = robSubOpt(r.left);
        int[] right = robSubOpt(r.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = r.val + left[0] + right[0];
        return res;
    }
}
