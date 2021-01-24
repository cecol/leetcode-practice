package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC104MaximumDepthOfBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC104MaximumDepthOfBinaryTree();
        var s = LC.maxDepth(null);
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int h = 0;
        h = Math.max(h, maxDepth(root.left));
        h = Math.max(h, maxDepth(root.right));
        return h+1;
    }
}
