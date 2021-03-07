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

        ListNode preMiddle = slow;
        ListNode middle = slow.next;
        ListNode reversedSecondHalf = reverse(middle);
        preMiddle.next = null;

        while (head != null && reversedSecondHalf != null) {
            ListNode t1 = head.next;
            ListNode t2 = reversedSecondHalf.next;
            reversedSecondHalf.next = head.next;
            head.next = reversedSecondHalf;
            head = t1;
            reversedSecondHalf = t2;
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
