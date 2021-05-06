package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LC958CheckCompletenessOfABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC958CheckCompletenessOfABinaryTree();
    }

    /**
     * 蠻直觀的就把每個 TreeNode left val set 成 parent.val * 2, right val set 成 parent.val * 2 + 1,
     * 然後level order traversal 去檢查是否符合預期的 sequence number
     * Runtime: 0 ms, faster than 100.00%
     * Memory Usage: 38.6 MB, less than 19.19%
     * */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<>();
        root.val = 1;
        bfs.add(root);
        int seq = 1;
        while(bfs.size() > 0) {
            int size = bfs.size();
            for(int i = 0;i<size;i++) {
                TreeNode p = bfs.poll();
                if(p.val != seq++) return false;
                if(p.left != null) {
                    p.left.val = p.val *2;
                    bfs.add(p.left);
                }
                if(p.right != null) {
                    p.right.val = p.val *2 + 1;
                    bfs.add(p.right);
                }
            }
        }
        return true;
    }
}
