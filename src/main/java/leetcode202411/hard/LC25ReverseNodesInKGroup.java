package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;
import org.w3c.dom.NodeList;

import java.util.PriorityQueue;

public class LC25ReverseNodesInKGroup extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 沒過
    // 其實兩層遞迴就可以解了
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode check = head;
        for (int i = 0; i < k - 1; i++) {
            check = check.next;
            if (check == null) return head;
        }
        ListNode next = check.next;
        check.next = null;
        ListNode revd = rev(head);
        head.next = reverseKGroup(next, k);
        return revd;
    }

    ListNode rev(ListNode h) {
        if(h == null || h.next == null) return h;
        ListNode end = rev(h.next);
        h.next.next = h;
        h.next = null;
        return end;
    }
}
