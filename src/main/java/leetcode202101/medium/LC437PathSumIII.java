package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
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
     * 每個點就是要紀錄從root到自己的加總過程 -> 從root到該點只有一條路徑 紀錄該路徑的two sum加總可能
     * 所以到該點可以透過找 preSum.getOrDefault(cur - target, 0);
     * 如果有的話 -> 一定是, 此路徑中, 中間某一點開始加總到目前的點剛好是target
     * 就是說 cur - target = x 的值代表是否存在過此值在過程中出現過
     * ->   ex 10 -> 5 -> 8 -> 7 -> 6
     * ->   走到6的cur 會經歷 10 -> 15 -> 23 -> 30 -> 36
     * ->   如果要的 target = 20 => 30 - 10
     * ->   所以就要檢查是否出現過10, 這個10就是由 30(cur) - 20(target) 得到的
     * ->   代表在累加過程中有有出現過加總是 10, 再配上後面加總是30, 代表中間有一段加總是20 就是target
     * 代表之前累加過程中就有算過x(因此之後才又達到cur, cur是一定有的, map又是存cur之前出現的加總過程, 有可能有x有可能沒有,
     * -> 但是如果有x就代表從x 到 cur 就是 target)
     *
     * 2022/11/5 原本答案會錯 因為給的 TreeNode.val 加總會大過 Integer
     * 原本的 Map<Integer, Integer> 要改成 Map<Long, Integer>
     */
    public int pathSum2(TreeNode root, int sum) {
        Map<Long, Integer> m = new HashMap<>();
        m.put(0L, 1);
        return checkByPreSum(m, root, 0L, sum);
    }

    private int checkByPreSum(Map<Long, Integer> preSumCount, TreeNode root, long curSum, int target) {
        if (root == null) return 0;
        curSum += root.val;
        int middleSumCount = preSumCount.getOrDefault(curSum - target, 0);
        preSumCount.put(curSum, preSumCount.getOrDefault(curSum, 0) + 1);
        middleSumCount += checkByPreSum(preSumCount, root.left, curSum, target) +
                checkByPreSum(preSumCount, root.right, curSum, target);
        preSumCount.put(curSum, preSumCount.get(curSum) - 1);
        return middleSumCount;
    }
}
