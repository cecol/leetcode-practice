package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC234PalindromeLinkedList extends BasicTemplate {
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

    // 沒過 思路是對的, 但反轉那一段有 CORNET CASE 沒處理好
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        if(f.next != null) f = f.next;
        ListNode pre = s.next, cur = pre.next;
        pre.next = null;
        while(cur != null) {
            ListNode np = cur.next;
            cur.next = pre;
            pre = cur;
            cur = np;
        }
        while (f != null) {
            if (head.val != f.val) return false;
            head = head.next;
            f = f.next;
        }
        return true;
    }
}
