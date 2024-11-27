package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC148SortList extends BasicTemplate {
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

    // 沒過, 沒想到這題就是考 merge sort
    // 1. list 分左伴右半, 各自 遞迴下去 sortList
    // 2. merge 結果
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode m = getMiddle(head);
        ListNode l1 = head, l2 = m.next;
        m.next = null;
        l1 = sortList(l1);
        l2 = sortList(l2);
        return merge(l1, l2);
    }

    ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }

    ListNode getMiddle(ListNode n) {
        if (n == null) return n;
        ListNode s = n, f = n;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
}
