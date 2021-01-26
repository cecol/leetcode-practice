package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC222CountCompleteTreeNodes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC222CountCompleteTreeNodes();
        var s = LC.countNodes(null);
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
     * 最佳解的 O(logN)^2
     * 蠻特別的
     * 1. 先算出樹高 -> 一定是最左子樹 -> height
     * 2. 如果root右子樹樹高也 == height - 1 (這邊要一層一層往下遞迴)
     * -> 1. 完全完整樹 node數目 == 2^height - 1
     * -> 2. 這顆是缺乏的右樹 -> 應該也是最後算到的樹
     * 這邊就是透過遞迴一層一層去右樹找, 每層遞迴都算該層的node樹(用高度配上2的次方)
     * 然後會找到最後一顆右子樹剛好是缺乏的
     */
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes2(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }
}
