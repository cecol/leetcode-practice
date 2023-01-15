package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC558LogicalOROfTwoBinaryGridsRepresentedAsQuadTrees extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    /**
     * https://leetcode.com/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/discuss/157532/Java-concise-code-beat-100/610646
     * 這題在考熟不熟悉 quad tree 操作, 知道 quad 操作就會很容易
     * <p>
     * 1. 不用檢查 Node 是否 null 才下去 intersect, 用 isLeaf 就足夠
     * 2. 任一者是 isLeaf 就可以直接決定後續結果
     * 3. 若都不是 isLeaf, 就得先遞迴下去
     * 4. 遞迴玩得檢查是否 4個兒子都是 isLeaf 且值都一樣, 是的話得 reset parent & child
     * - 因為 q1.topLeft = intersect(q1.topLeft, q2.topLeft);
     * - 可能本來 q1 is leaf, topLeft = null
     * - 但我們直接覆蓋 q1.topLeft = intersect(q1.topLeft, q2.topLeft), 讓他變成 parent,
     * - 所以最後還要回頭檢查是否還是 4 個遞迴的 childs 還是保持 leaf
     * 5. intersect 不用建新 Node, 只要拿 q1 當merge 結果就好
     */
    public Node intersect(Node q1, Node q2) {
        if (q1.isLeaf) return q1.val ? q1 : q2;
        if (q2.isLeaf) return q2.val ? q2 : q1;

        q1.topLeft = intersect(q1.topLeft, q2.topLeft);
        q1.topRight = intersect(q1.topRight, q2.topRight);
        q1.bottomLeft = intersect(q1.bottomLeft, q2.bottomLeft);
        q1.bottomRight = intersect(q1.bottomRight, q2.bottomRight);

        if (q1.topLeft.isLeaf && q1.topRight.isLeaf && q1.bottomLeft.isLeaf && q1.bottomRight.isLeaf &&
                q1.topLeft.val == q1.topRight.val &&
                q1.topRight.val == q1.bottomLeft.val &&
                q1.bottomLeft.val == q1.bottomRight.val
        ) {
            q1.isLeaf = true;
            q1.val = q1.bottomLeft.val;

            q1.topLeft = null;
            q1.topRight = null;
            q1.bottomLeft = null;
            q1.bottomRight = null;
        }

        return q1;
    }
}
