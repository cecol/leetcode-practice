package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC742ClosestLeafInABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/closest-leaf-in-a-binary-tree/discuss/109960/Java-DFS-%2B-BFS-27ms
     * 這題值得來紀錄是一位它是一題 graph 題目 但是偽裝成 tree
     * 比較難的就是
     * 1. 識別他應該要用 graph 來理解
     * 2. 是 Graph 解法, 但是得 透過操作 Tree 來走
     * <p>
     * 用 Graph 來思考就是, k 點開始 BFS, 最先到達的 leave 就是
     * k 點開始 BFS
     * 1. 走 left/right child
     * 2. 走 parent
     * <p>
     * 但我們其實不用真的走每個 node -> parent, 我們只要 root -> k 點的 node -> parent
     * <p>
     * 所以
     * 1. 先DFS 找出 k 點 且紀錄過程中 node -> parent
     * 2. 從 k 點 BFS
     */
    public int findClosestLeaf(TreeNode rt, int k) {
        Map<TreeNode, TreeNode> toP = new HashMap<>();
        TreeNode kn = fk(rt, toP, k);
        Queue<TreeNode> bfs = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        bfs.offer(kn);
        visited.add(kn);
        while (bfs.size() > 0) {
            TreeNode v = bfs.poll();
            if (v.left == null && v.right == null) return v.val;
            if (v.left != null && visited.add(v.left)) bfs.offer(v.left);
            if (v.right != null && visited.add(v.right)) bfs.offer(v.right);
            if (toP.containsKey(v) && visited.add(toP.get(v))) bfs.offer(toP.get(v));
        }
        return -1;
    }

    TreeNode fk(TreeNode rt, Map<TreeNode, TreeNode> toP, int k) {
        if (rt == null) return null;
        if (rt.val == k) return rt;
        if (rt.left != null) {
            toP.put(rt.left, rt);
            TreeNode lf = fk(rt.left, toP, k);
            if (lf != null) return lf;
        }
        if (rt.right != null) {
            toP.put(rt.right, rt);
            TreeNode rf = fk(rt.right, toP, k);
            if (rf != null) return rf;
        }
        return null;
    }
}
