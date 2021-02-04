package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC988SmallestStringStartingFromLeaf extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC988SmallestStringStartingFromLeaf();
        var s = LC.smallestFromLeaf(null);
    }

    String s = null;

    public String smallestFromLeaf(TreeNode root) {
        smallestFromLeaf(root, "");
        return s;
    }

    public void smallestFromLeaf(TreeNode n, String cur) {
        if (n == null) return;
        String ff = (char) ('a' + n.val) + cur;
        if (n.right == null && n.left == null) {
            if (s == null) s = ff;
            else if (s.compareTo(ff) > 0) s = ff;
            return;
        }
        smallestFromLeaf(n.left, ff);
        smallestFromLeaf(n.right, ff);
    }
}
