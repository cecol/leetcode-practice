package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC708InsertIntoASortedCircularLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC708InsertIntoASortedCircularLinkedList();
    }

    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    /**
     * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/solutions/863294/java-recursive-commented-easy-to-understand/
     * 有想到用 遞迴 可是 因為是非遞減 list, 會有相同數字
     * edge case 蠻多細節要處理
     * (cur.val <= iNode.val && iNode.val <= cur.next.val) -> 正常 case: insertVal 在兩點之間
     * (cur.val > cur.next.val && iNode.val <= cur.next.val) -> insertVal is smallest
     * (cur.val > cur.next.val && iNode.val >= cur.val) -> insertVal is largest
     *
     * 考慮下 [3,3,3], insertVal = 0
     * 其實會走到 cur.next == head, 走了一圈 上面 3個 都不符合
     * */
    public Node insert(Node h, int insertVal) {
        Node iNode = new Node(insertVal);
        iNode.next = iNode;
        if (h == null) return iNode;

        insert(h, h, iNode);
        return h;
    }

    void insert(Node cur, Node head, Node iNode) {
        if ((cur.val <= iNode.val && iNode.val <= cur.next.val) ||
                (cur.val > cur.next.val && iNode.val <= cur.next.val) ||
                (cur.val > cur.next.val && iNode.val >= cur.val) ||
                cur.next == head) {
            iNode.next = cur.next;
            cur.next = iNode;
            return;
        }
        insert(cur.next, head, iNode);
    }
}
