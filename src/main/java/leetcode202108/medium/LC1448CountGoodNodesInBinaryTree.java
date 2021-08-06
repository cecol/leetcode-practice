package leetcode202108.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC1448CountGoodNodesInBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1448CountGoodNodesInBinaryTree();
    }

    /**
     *  蠻直觀的pre order把當前走到路徑的 max value帶下去, 每個點自己檢查就知道是否最大了
     * */
    int c = 1;
    public int goodNodes(TreeNode root) {
        checkNode(root.val, root.left);
        checkNode(root.val, root.right);
        return c;
    }

    private void checkNode(int max, TreeNode cur) {
        if(cur == null) return;
        if(cur.val >= max) c++;
        checkNode(Math.max(cur.val, max), cur.left);
        checkNode(Math.max(cur.val, max), cur.right);
    }
}
