package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC1932MergeBSTsToCreateSingleBST extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1932MergeBSTsToCreateSingleBST();
    }

    /**
     * https://leetcode.com/problems/merge-bsts-to-create-single-bst/discuss/1486253/Java-or-Easy-to-Understand
     * 這題可以解得很直觀, 但一開始很難看得出來
     * 一開始不清楚的點是
     * 1. 是否有任意組合, 都得嘗試之後才會找到可以的組合?
     * 2. 是否有任意組合, 且只有一定組合次序才可以完成? 所以得可總組合次序都得嘗試才能確定是否 merge 所有 BST?
     * 剛看題目會以為要做DFS, 把所有可能組合都跑過才會知道, 只有所有組合跑完找到最終組合出 BST, 才可以確認最終答案
     *
     * 但關鍵是 因為他是 strictly less & strictly greater BST
     * 所以他其實只有一種組合, 因為所有樹 所有點 都是唯一,
     * 1. 有重複的點, 一定組不出來,
     * 2. 能組合的 root 一定是別人的 leaves, 最終組出來只有一個 root, 所以只有一個 root 不是任何其他 樹 的 leave
     * 3. 承上 如果有兩個 root, 不再其他人的 leaves, 那就無法組成最後單一 BST
     * 4. 把找到的唯一最終 root 下去組合, 如果最後有組完, 上下屆檢查都對 就是 true
     *
     * Set<Integer> leaves = new TreeSet<>();
     * 只是用來找出最終 root, 從他開始
     *
     * Map<Integer, TreeNode> roots = new HashMap<>();
     * 用來記載還有多少 root 還沒被merge到
     *
     * traverse(TreeNode rt, Map<Integer, TreeNode> roots, int min, int max)
     * 持續遞迴 往子樹 leaves 找 root 下去組合
     * 如果過程中遇到超出界線, 就是 false
     * - if (rt.left == null && rt.right == null) {
     * 就是指目前遇到 leave, 找出可以組的 root, 並組合
     */
    public TreeNode canMerge(List<TreeNode> trees) {
        Set<Integer> leaves = new TreeSet<>();
        Map<Integer, TreeNode> roots = new HashMap<>();
        for (TreeNode tree : trees) {
            roots.put(tree.val, tree);
            if (tree.left != null) leaves.add(tree.left.val);
            if (tree.right != null) leaves.add(tree.right.val);
        }

        TreeNode res = null;
        for (TreeNode tree : trees) {
            if (!leaves.contains(tree.val)) {
                res = tree;
                break;
            }
        }
        if (res == null) return null;

        return traverse(res, roots, Integer.MIN_VALUE, Integer.MAX_VALUE) && roots.size() == 1 ? res : null;
    }

    boolean traverse(TreeNode rt, Map<Integer, TreeNode> roots, int min, int max) {
        if (rt == null) return true;
        if (rt.val <= min || rt.val >= max) return false;

        if (rt.left == null && rt.right == null) {
            if (roots.containsKey(rt.val) && rt != roots.get(rt.val)) {
                TreeNode next = roots.get(rt.val);
                rt.left = next.left;
                rt.right = next.right;
                roots.remove(rt.val);
            }
        }
        return traverse(rt.left, roots, min, rt.val) && traverse(rt.right, roots, rt.val, max);

    }
}
