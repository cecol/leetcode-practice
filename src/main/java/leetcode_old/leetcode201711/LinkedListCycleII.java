package leetcode_old.leetcode201711;

import java.util.HashSet;

public class LinkedListCycleII {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head))
                return head;
            else
                set.add(head);
            head = head.next;
        }
        return null;
    }
}
