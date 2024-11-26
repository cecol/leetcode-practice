package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC19RemoveNthNodeFromEndOfList extends BasicTemplate {
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

    // 直觀解,
    // 1. 要做 dummy, 不然很難處理 null
    // 2. 要做 s1, s2 , s2 先走 N 步, s1/s2 一起走到底, s1.next 就是要處理對象
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(), s1 = dummy, s2 = dummy;
        dummy.next = head;
        while (n > 0) {
            s2 = s2.next;
            n--;
        }
        while (s2.next != null) {
            s1 = s1.next;
            s2 = s2.next;
        }
        if(s1.next.next != null) s1.next = s1.next.next;
        else s1.next = null;
        return dummy.next;
    }
}
