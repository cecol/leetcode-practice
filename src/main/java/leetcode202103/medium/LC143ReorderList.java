package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

public class LC143ReorderList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC143ReorderList();
    }

    /**
     * 2023/1/3 莫明其妙想出一個更簡單的遞迴方法
     * 1. 找出 tail, preTail
     * 2. ListNode h2 = head.next;
     * 3. preTail.next = null;
     * 4. head.next = tail;
     * 5. tail.next = rev(h2);
     * 6. return tail
     * */
    public void reorderList2(ListNode head) {
        rev(head);
    }

    ListNode rev(ListNode h) {
        if(h == null || h.next == null) return h;
        ListNode cur = h;
        while(cur.next.next != null) cur = cur.next;
        ListNode tail = cur.next;
        cur.next = null;
        ListNode h2 = h.next;
        h.next = tail;
        tail.next = rev(h2);
        return h;
    }

    /**
     * https://leetcode.com/problems/reorder-list/discuss/44992/Java-solution-with-3-steps
     * 就算解完, Reverse a linked list I & II, 這題大概可以猜出怎麼解, 但思路還是不夠清晰
     * 1. 先找到 middle node,
     * 2. 把後半 reverse掉
     * 3. 把這兩段交錯串再一起 -> 這邊沒有比較好的想法來寫 最後還是參考答案才明白
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secHead = reverse(slow.next);
        slow.next = null;

        while (head != null && secHead != null) {
            ListNode t1 = head.next;
            ListNode t2 = secHead.next;
            secHead.next = t1;
            head.next = secHead;
            head = t1;
            secHead = t2;
        }

    }

    private ListNode reverse(ListNode h) {
        if (h == null || h.next == null) return h;
        ListNode nh = reverse(h.next);
        h.next.next = h;
        h.next = null;
        return nh;
    }
}
