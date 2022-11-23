package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC1123LowestCommonAncestorOfDeepestLeaves extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334583/Java-O(n)-Short-and-Simple-Recursion
     * 這題應該很直觀的就是看高度,
     * 左右子樹高度一樣就是當前 root
     * 若左右子樹高不一樣, 就是遞迴選高的
     * 配上一個 map 去紀錄看過高度就很快了
     * */
    Map<TreeNode, Integer> map = new HashMap<>();
    public TreeNode lcaDeepestLeaves(TreeNode rt) {
        if (rt == null || h(rt.left) == h(rt.right)) return rt;
        return h(rt.left) > h(rt.right) ? lcaDeepestLeaves(rt.left) : lcaDeepestLeaves(rt.right);
    }

    int h(TreeNode rt) {
        if (rt == null) return 0;
        if (!map.containsKey(rt)) {
            map.put(rt, Math.max(h(rt.left), h(rt.right)) + 1);
        }
        return map.get(rt);
    }
}
