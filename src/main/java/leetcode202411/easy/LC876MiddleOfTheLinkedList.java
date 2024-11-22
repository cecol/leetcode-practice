package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC876MiddleOfTheLinkedList extends BasicTemplate {
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

    // 很直觀地想起就是 快慢指針問題
    public ListNode middleNode(ListNode h) {
        if(h == null) return null;
        if(h.next == null) return h;
        ListNode s1 = h, s2 = h;
        while(s2 != null && s2.next != null) {
            s1 = s1.next;
            s2 = s2.next.next;
        }
        return s1;
    }

}
