package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC129SumRootToLeafNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC129SumRootToLeafNumbers();
        var s = LC.sumNumbers(null);
    }

    int s = 0;

    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        sn(root, new StringBuilder());
        return s;
    }

    private void sn(TreeNode n, StringBuilder cur) {
        if (n == null) return;
        cur.append(n.val);
        if (n.left == null && n.right == null) {
            s += Integer.parseInt(cur.toString());
            cur.deleteCharAt(cur.length() - 1);
            return;
        }
        sn(n.left, cur);
        sn(n.right, cur);
        cur.deleteCharAt(cur.length() - 1);
    }
}
