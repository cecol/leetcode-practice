package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC894AllPossibleFullBinaryTrees extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC894AllPossibleFullBinaryTrees();
    }

    public List<TreeNode> allPossibleFBT(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        if (n == 1) return List.of(new TreeNode(0));
        if (n == 3) return List.of(new TreeNode(0, new TreeNode(0), new TreeNode(0)));
        List<TreeNode> res = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            List<TreeNode> l = allPossibleFBT(i - 1);
            List<TreeNode> r = allPossibleFBT(n - i);
            for (TreeNode ll : l)
                for (TreeNode rr : r) res.add(new TreeNode(0, ll, rr));
        }
        return res;
    }


}
