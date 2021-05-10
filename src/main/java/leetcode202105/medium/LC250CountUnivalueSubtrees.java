package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC250CountUnivalueSubtrees extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC250CountUnivalueSubtrees();
    }

    /**
     * 很直觀的解了, 就是DFS 下去child, 用 global res紀錄當前的uni subtree count
     * 先看看左右子樹, 然後左右子樹回傳自己的val, 在跟當前的 root比對, 有的話就 res++
     * 如果沒有就回傳 1001, 左右子樹其一不是 uni, 當前root一定不是,
     * 只是巧妙用超過的區間的 value 1001 來表達非uni subtree的結果 來簡化判斷
     * */
    int res = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return res;
        int lb = dfsUni(root.left, root);
        int rb = dfsUni(root.right, root);
        if( lb == root.val && rb == root.val ) res++;
        return res;
    }

    private int dfsUni(TreeNode root, TreeNode p) {
        if(root == null) return p.val;
        int lb = dfsUni(root.left, root);
        int rb = dfsUni(root.right, root);
        if( lb == root.val && rb == root.val) {
            res++;
            return root.val;
        }
        return 1001;
    }
}
