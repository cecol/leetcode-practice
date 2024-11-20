package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC21MergeTwoSortedLists extends BasicTemplate {
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

    public ListNode mergeTwoLists(ListNode l, ListNode k) {
        if (l == null) return k;
        if (k == null) return l;

        ListNode h = null, t = null;
        while (l != null && k != null) {
            ListNode n = null;
            if (l.val > k.val) {
                n = k;
                k = k.next;
            } else {
                n = l;
                l = l.next;
            }
            if (h == null) {
                h = n;
                t = n;
            } else {
                t.next = n;
                t = t.next;
            }
        }
        ListNode f = l == null ? k : l;
        while(f != null) {
            t.next = f;
            t=t.next;
            f=f.next;
        }

        return h;
    }

}
