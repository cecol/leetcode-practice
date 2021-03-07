package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC1022SumOfRootToLeafBinaryNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1022SumOfRootToLeafBinaryNumbers();
    }

    int res = 0;

    public int sumRootToLeaf(TreeNode root) {
        sum(root, 0);
        return res;
    }

    public void sum(TreeNode r, int cur) {
        if (r == null) return;
        cur = 2 * cur + r.val;
        if (r.left == null && r.right == null) {
            res += cur;
            return;
        }
        sum(r.left, cur);
        sum(r.right, cur);
    }
}
