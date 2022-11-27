package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC545BoundaryOfBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 原本以為用 bfs, level order traversal 就可以了, 但我沒想法去解 如果其中一個 level 只有一個點
     * 那他是 left 邊界 還是 right 邊界?
     * <p>
     * https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
     * 我有想到是用 preorder, 但有三種 case 要特別照顧, 而且要配合 post-order
     * 1. 先加 root
     * 2. 先走 left boundary -> 這是 preorder
     * - 因為知道是 root.left 遞迴下來, 有的話一定都是 left boundary
     * - preorder 先加自己, 然後有 left child 就只走 left child
     * - 沒有 left child 才走 right child
     * <p>
     * 3. 先走 left.leaves
     * 4. 在走 right leaves
     * 只要是 leave -> left == null && right == null, 一定都會是邊界(我前面想錯, 以為是最低 level 才是)
     * 其實是所有 leave 都算, 就算不是最低 level, 也仍是 leave boundary
     * <p>
     * 5. 走 left boundary -> 這是 post order -> 因為是 reverse 加回去
     * * - 因為知道是 root.right 遞迴下來, 有的話一定都是 right boundary
     * * - preorder 先加自己, 然後有 right child 就只走 right child
     * * - 沒有 right child 才走 left child
     */
    List<Integer> res = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode rt) {
        if (rt == null) return res;
        res.add(rt.val);
        leftBound(rt.left);
        leaves(rt.left);
        leaves(rt.right);
        rightBound(rt.right);
        return res;
    }

    void leftBound(TreeNode rt) {
        if (rt == null || (rt.left == null && rt.right == null)) return;
        res.add(rt.val);
        if (rt.left != null) leftBound(rt.left);
        else leftBound(rt.right);
    }

    void leaves(TreeNode rt) {
        if (rt == null) return;
        if (rt.left == null && rt.right == null) res.add(rt.val);
        leaves(rt.left);
        leaves(rt.right);
    }

    void rightBound(TreeNode rt) {
        if (rt == null || (rt.left == null && rt.right == null)) return;
        if (rt.right == null) rightBound(rt.left);
        else rightBound(rt.right);
        res.add(rt.val);
    }
}
