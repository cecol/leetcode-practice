package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC1028RecoverATreeFromPreorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1028RecoverATreeFromPreorderTraversal();
    }

    /**
     * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/discuss/276002/Java-simple-inline-iterative-solution-with-HashMap
     * 沒想到這題是建立 child 然後看看 child level 來找回 parent
     * 並把 child assign 回 parent
     * 因為有個 hashmap 記載每層的 node
     * 可是有個問題是 同層若有多個 node, 怎知道哪個是當前 child parent?
     *
     * 因為是 preorder, 所以 left 走完才走 right
     * 所以當走到 right , 會覆蓋掉同層 left in hashmap, 但沒關係, 因為 left 已走完
     * 自然要 分配的 child 都是屬於 right
     * */
    public TreeNode recoverFromPreorder(String traversal) {
        Map<Integer, TreeNode> levels = new HashMap<>();
        int i = 0;
        while (i < traversal.length()) {
            int curLevel = 0, curNum = 0;
            while (i < traversal.length() && traversal.charAt(i) == '-') {
                curLevel++;
                i++;
            }
            while (i < traversal.length() && traversal.charAt(i) >= '0' && traversal.charAt(i) <= '9') {
                curNum = curNum * 10 + traversal.charAt(i) - '0';
                i++;
            }
            TreeNode rt = new TreeNode(curNum);
            levels.put(curLevel, rt);
            if(curLevel > 0) {
                TreeNode parent = levels.get(curLevel - 1);
                if(parent.left == null) parent.left = rt;
                else parent.right = rt;
            }
        }
        return levels.get(0);
    }
}
