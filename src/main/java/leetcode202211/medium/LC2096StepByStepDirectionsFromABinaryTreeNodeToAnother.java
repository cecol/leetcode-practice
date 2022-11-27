package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC2096StepByStepDirectionsFromABinaryTreeNodeToAnother extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2096StepByStepDirectionsFromABinaryTreeNodeToAnother();
    }

    /**
     * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/discuss/1612105/3-Steps
     * 我有想出基本解法 但都是一直 MLE memory limit exceed
     * 原本以為
     * 1. 找出 lca
     * 2. 找出 root -> lca, root -> startValue, root -> destValue
     * 3. 然後把 root -> lca path 刪除就好
     * 但是都會 MEL
     * <p>
     * 看起來只要做
     * 1. 找出 root -> startValue, root -> destValue path
     * Ex: "LLRRL" and "LRR".
     * 2. 刪除共同前綴 -> "L", and now start direction is "LRRL", and destination - "RR"
     * 3. 把 start direction 都轉成 U 並加上 destination
     * <p>
     * 這個解法不會 MLE 應該是 bottom up 把 path 建立起來, 所以 StringBuilder 要用 insert at 0,
     * 如果用 append 會更快 但是最後處理時候得 reverse 才好處理
     *
     */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        find(root, s, startValue);
        find(root, d, destValue);
        int i = 0, iMax = Math.min(s.length(), d.length());
        while (i < iMax && s.charAt(i) == d.charAt(i)) i++;
        return "U".repeat(s.length() - i) + d.toString().substring(i);
    }

    boolean find(TreeNode rt, StringBuilder s, int v) {
        if (rt.val == v) return true;
        if (rt.left != null && find(rt.left, s, v)) {
            s.insert(0, "L");
        } else if (rt.right != null && find(rt.right, s, v)) {
            s.insert(0, "R");
        }
        return s.length() > 0;
    }
}
