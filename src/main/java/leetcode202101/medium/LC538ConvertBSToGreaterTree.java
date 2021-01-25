package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC538ConvertBSToGreaterTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC538ConvertBSToGreaterTree();
        var s = LC.convertBST(null);
    }

    /**
     * https://leetcode.com/problems/convert-bst-to-greater-tree/discuss/100506/Java-Recursive-O(n)-time
     * 因為BST -> 中序就是ordered -> 所以用中序來走過樹 -> 但是這個中序是由右邊開始走過來
     * 並且過程中累加一個全域變數 -> 所以右邊走完 就可以更新當前node, 並再走左邊,
     * */
    int s =0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        sumBST(root);
        return root;
    }

    public void sumBST(TreeNode root) {
        if (root == null) return;
        sumBST(root.right);
        root.val += s;
        s=root.val;
        sumBST(root.left);
    }
}
