package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Node;
import util.TreeNode;

public class LC117PopulatingNextRightPointersInEachNodeII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC117PopulatingNextRightPointersInEachNodeII();
        var s = LC.connect(null);
    }

    /**
     * 原本想用116解法改變一下來解  但看起來完全行不通
     * 也不能真的每一層level order去走
     * 參考別人的想法: 
     * */
    public Node connect(Node root) {
        if (root == null) return null;
        dfs(root, null);
        return root;
    }

    private void dfs(Node n, Node next) {
        if (n == null) return;
        n.next = next;
        Node ln = null;
        if (n.right != null) ln = n.right;
        else if (n.next != null && n.next.left != null) ln = n.next.left;
        else if (n.next != null && n.next.right != null) ln = n.next.right;
        dfs(n.left, ln);
        Node rn = null;
        if (n.next != null && n.next.left != null) rn = n.next.left;
        else if (n.next != null && n.next.right != null) rn = n.next.right;
        dfs(n.right, rn);
    }
}
