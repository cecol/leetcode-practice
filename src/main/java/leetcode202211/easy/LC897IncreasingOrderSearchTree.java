package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.TreeSet;

public class LC897IncreasingOrderSearchTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC897IncreasingOrderSearchTree();
    }

    public TreeNode increasingBST(TreeNode rt) {
        if(rt == null) return null;
        if(rt.left == null && rt.right == null) return rt;
        TreeNode ll = increasingBST(rt.left);
        TreeNode rr = increasingBST(rt.right);
        rt.right = rr;
        if(ll != null) {
            TreeNode tmp = ll;
            while(tmp.right != null) tmp = tmp.right;
            tmp.right = rt;
            rt.left = null;
            return ll;
        }
        return rt;
    }
}
