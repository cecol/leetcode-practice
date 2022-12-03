package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;
import java.util.Stack;

public class LC536ConstructBinaryTreeFromString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-string/discuss/149479/Logical-Thinking-with-Java-Code-Beats-95.72/664142
     * 有想出基本思路, 但一些細節沒想清楚
     * 跟 LC224BasicCalculator 很類似  只是操作的是 TreeNode, 還有判斷是 Assign left/right
     * 1. 遇到數字就一直讀到完
     * 2. TreeNode cur 記載目前看到的 node, 可能是 parent, 可能是 new Node
     * 3. 遇到 ( 就是把 cur 放進 stack, cur = null
     * 4. 遇到 ) 就是去 stack 把 parent 讀出來, 把 cur assign to parent left/right child
     * -  cur = parent:
     * -  後續遇到 ( , parent 會再被放進去 for next child
     * -  或者遇到 ) , 該 parent 是別人的 child,
     * -  或者 parent = root, 所以最後直接回傳
     * - 所以關鍵是 - TreeNode cur 記載目前看到的 node, 可能是 parent, 可能是 new Node
     * */
    public TreeNode str2tree(String s) {
        Stack<TreeNode> sk = new Stack<>();
        TreeNode cur = null;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '-' || Character.isDigit(s.charAt(i))) {
                int sign = 1;
                if (s.charAt(i) == '-') {
                    sign = -1;
                    i++;
                }
                int sum = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(++i) - '0';
                }
                cur = new TreeNode(sum * sign);
            } else if (s.charAt(i) == '(') {
                sk.push(cur);
                cur = null;
            } else {
                TreeNode p = sk.pop();
                if (p.left == null) p.left = cur;
                else p.right = cur;
                cur = p;
            }
        }
        return cur;
    }
}
