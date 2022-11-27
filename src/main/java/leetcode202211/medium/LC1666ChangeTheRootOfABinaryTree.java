package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1666ChangeTheRootOfABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }



    /**
     * 很容易想很複雜的 pointer manipulation
     * https://leetcode.com/problems/change-the-root-of-a-binary-tree/discuss/954468/Java.-DFS.-It's-all-about-pointer-manipulation.
     * 只有看過解答才知道其實蠻直觀的
     * 1. dfs 從 leaf 開始
     * 2. dfs
     * - 帶入 newParent
     * - dfs 當前 Node 先更新 newParent
     * - dfs 當前 Node 先儲存 oldParent, 下一層 DFS 要用
     * - dfs 當前 Node 檢查 newParent 來自哪裡 (left/right), left/right assign null
     * - dfs 當前 Node 是否是 本來 root, 是的話 dfs 結束, 因為已經反轉完, root 變成別人 child, root 其他 child 自動變成子孫
     * - dfs 當前 Node 檢查 left 是否有, 有的話 assign to right
     * - dfs 當前 Node.left = dfs(oldParent, node)
     * - 回傳 當前 node
     * */
    Node originalRoot = null;

    public Node flipBinaryTree(Node root, Node leaf) {
        originalRoot = root;
        return helper(leaf, null);
    }

    Node helper(Node n, Node newP) {
        Node oldP = n.parent;
        n.parent = newP;

        if (n.left == newP) n.left = null;
        if (n.right == newP) n.right = null;

        if(n == originalRoot) return n;

        if(n.left != null) n.right = n.left;
        n.left = helper(oldP, n);

        return n;
    }
}
