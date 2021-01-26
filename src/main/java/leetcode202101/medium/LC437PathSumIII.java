package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC437PathSumIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC437PathSumIII();
        var s = LC.pathSum(null, 0);
    }

    /**
     * https://leetcode.com/problems/path-sum-iii/discuss/91889/Simple-Java-DFS
     * 遞迴解法 但是很慢
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode root, int sum) {
        if (root == null) return 0;
        return (root.val == sum ? 1 : 0) +
                pathSumFrom(root.left, sum - root.val) +
                pathSumFrom(root.right, sum - root.val);
    }

    /**
     * https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
     * 用HashMap版本 -> 紀錄已加總過的pre sum數值次數
     * 很像Two sum
     * 每個點就是要紀錄從root到自己的加總過程 -> 從root到該點至有一條路徑 紀錄該路徑的two sum加總可能
     * 所以到該點可以透過找 preSum.getOrDefault(target - cur, 0);
     * 如果有的話 -> 一定是, 此路徑中, 中間某一點開始加總到目前的點剛好是target
     * 就是說 target - cur = x 的值代表是否存在過此值在過程中出現過  -> target = x + cur -> 如果有x,
     * 代表之前累加過程中就有算過x(因此之後才又達到cur, cur是一定有的, map又是存cur之前出現的加總過程, 有可能有x有可能沒有,
     * -> 但是如果有x就代表從x 到 cur 就是 target)
     *
     */
    public int pathSum2(TreeNode root, int sum) {
        Map<Integer, Integer> m = new HashMap<>();
        return checkAllPossibility(m, root, 0, sum);
    }

    private int checkAllPossibility(Map<Integer, Integer> preSum, TreeNode root, int cur, int target) {
        if (root == null) return 0;
        cur += root.val;
        int res = preSum.getOrDefault(target - cur, 0);
        preSum.put(cur, preSum.getOrDefault(cur, 0) + 1);
        res += checkAllPossibility(preSum, root.left, cur, target) +
                checkAllPossibility(preSum, root.right, cur, target);
        preSum.put(cur, preSum.get(cur) + 1);
        return res;
    }
}
