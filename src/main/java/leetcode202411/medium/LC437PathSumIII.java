package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC437PathSumIII extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;

        }
    }

    // 沒過
    // 1. ex 10 -> 5 -> 8 -> 7 -> 6, 就要記載一路上的加總: 10 -> 15 -> 23 -> 30 -> 36
    // 2. 走到 cur = 36, target = 20, 就找看看有沒有出現過 middleSumCount: cur - target: 30-20 = 10
    // 3. 拿到當前的 middleSumCount, 在往左右子樹下去找, 的結果回傳 加總就是結果
    // 4. 遞迴結束 m 的 cur key 要 -1, 因為要遞迴回去, 去走其他子樹, 當前的紀錄不能影響其他子樹
    // 5. 當前遞迴只能影響 自己子樹, 前一輩子樹不應該被影響到
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> m = new HashMap<>();
        m.put(0L, 1);
        return checkByPreSum(m, root, 0L, targetSum);
    }

    int checkByPreSum(Map<Long, Integer> m, TreeNode rt, long cur, int t) {
        if (rt == null) return 0;
        cur += rt.val;
        int middleSumCount = m.getOrDefault(cur - t, 0);
        m.put(cur, m.getOrDefault(cur, 0) + 1);
        middleSumCount += checkByPreSum(m, rt.left, cur,t)+checkByPreSum(m, rt.right, cur,t);
        m.put(cur, m.getOrDefault(cur, 0) - 1);
        return middleSumCount;
    }
}
