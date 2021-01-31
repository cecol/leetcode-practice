package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC606ConstructStringFromBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC606ConstructStringFromBinaryTree();
        var s = LC.tree2str(null);
    }

    /**
     * 應該解的出來  但看錯題目定義, 解得很亂, 原本的思路也不完善
     * 直接參考標準方式
     * https://leetcode.com/problems/construct-string-from-binary-tree/discuss/103992/Java-Solution-Tree-Traversal
     * 題目真正意思是, 如果是空樹且是左空右有 -> 左空要有 ()
     * 如果是空樹且是右空左有 -> 右空不用 ()
     * 然後因為只有root不用()包起來
     * 我以為遞迴得另外褻個函式處理, 但當前的函式就可以了, 因為是當前去包下一層, 不是自己就先包自己
     * 只有parent會包child -> 所以root層不會被包到
     * */
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String result = t.val + "";
        String l = tree2str(t.left);
        String r = tree2str(t.right);
        if (l.equals("") && r.equals("")) return result;
        if (l.equals("")) return result + "()" + "(" + r + ")";
        if (r.equals("")) return result + "(" + l + ")";
        return result + "(" + l + ")" + "(" + r + ")";
    }

}
