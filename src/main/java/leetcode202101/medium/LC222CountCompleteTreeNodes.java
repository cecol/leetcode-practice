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

    /**
     * https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
     * 最佳解的 O(logN)^2
     * 蠻特別的 2022/11 再次來複習完全沒看懂
     * 1. 算樹高 height(TreeNode root) 只往左子樹走,
     * 2. 先算 root 樹高, 再算 root.right 樹高
     * 如果 root 樹高 - 1 == root.right 樹高, 保證 root.left 是完全樹, (殘缺在 root.right)
     * 所以是 node 數 = Math.pow(2, root.left 樹高) - 1 + countNodes(root.right)
     * 因為 root.right 再算數高也只是往 root.right.left 一直往下, 所以對於右右子孫情況未知 得遞迴下去算
     * 這邊有個算法是 Math.pow(2, root.left) == (1 << root.left 樹高) -> 回頭來看完全忘記
     * <p>
     * 3. 如果 root 樹高 - 1 != root.right 樹高 (殘缺在 root.left)
     * 代表 root.left 是非完全樹, 但 root.right 是完全樹
     * (root.left 更高一層, root.right 少一層, 所以root.right 該層是 complete tree)
     * 所以 node 樹 = countNodes(root.left) + Math.pow(2, root.right 樹高)  -1
     */
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes(TreeNode rt) {
        int rtH = height(rt);
        if (rtH == -1) return 0;
        int rtRightH = height(rt.right);
        if (rtH - 1 == rtRightH) return (1 << rtH) + countNodes(rt.right);
        return countNodes(rt.left) + (1 << (rtH - 1));
    }

    /**
     * 還有更直觀解法
     * https://leetcode.com/problems/count-complete-tree-nodes/discuss/61948/Accepted-Easy-Understand-Java-Solution
     * (1 << leftDepth) - 1 解釋也在討論有提到
     * https://leetcode.com/problems/count-complete-tree-nodes/discuss/61948/Accepted-Easy-Understand-Java-Solution/63464
     *
     */
    public int countNodes2(TreeNode rt) {
        int lh = leftH(rt);
        int rh = rightH(rt);

        if (lh == rh) return (1 << lh) - 1;
        return 1 + countNodes2(rt.left) + countNodes2(rt.right);
    }


    int leftH(TreeNode rt) {
        int c = 0;
        while (rt != null) {
            rt = rt.left;
            c++;
        }
        return c;
    }

    int rightH(TreeNode rt) {
        int c = 0;
        while (rt != null) {
            rt = rt.right;
            c++;
        }
        return c;
    }
}
