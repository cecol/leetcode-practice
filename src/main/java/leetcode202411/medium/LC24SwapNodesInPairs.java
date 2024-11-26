package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC24SwapNodesInPairs extends BasicTemplate {
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

    // 很直觀 倆倆 遞迴下去跑
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode sub = swapPairs(head.next.next);
        ListNode nh = head.next;
        nh.next = head;
        head.next = sub;
        return nh;
    }
}
