package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC206ReverseLinkedList extends BasicTemplate {
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

    // 花了一點時間回想, 有想起
    // 1. 要遞迴, 回傳最後一個點為新的起始
    // 2. h 的 next 的 next 要反轉
    // 3. h.next 要 null, 避免迴圈 list
    public ListNode reverseList(ListNode h) {
        if(h == null) return null;
        if(h.next == null) return h;
        ListNode rev = reverseList(h.next);
        h.next.next = h;
        h.next = null;
        return rev;
    }

}
