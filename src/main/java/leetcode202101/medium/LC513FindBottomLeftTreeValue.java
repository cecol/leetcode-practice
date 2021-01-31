package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC513FindBottomLeftTreeValue extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC513FindBottomLeftTreeValue();
        var s = LC.findBottomLeftValue(null);
    }

    /**
     * 這題大概知道怎解, 但覺得自己的想法比較不是正確做法
     * 所以後來去找其他人的答案
     * https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98779/Right-to-Left-BFS-(Python-%2B-Java)
     * 這解答有意思的是用queue 來BFS走下去, 剛好就是一層一層下去
     * 然後都是先放right再放左 確保最後拿到的一定是left tree(如果沒有left tree, 一定是right去接) 這個蠻關鍵的
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            root = q.poll();
            if (root.right != null) q.add(root.right);
            if (root.left != null) q.add(root.left);
        }
        return root.val;
    }
}
