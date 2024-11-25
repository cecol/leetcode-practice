package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.PriorityQueue;
import java.util.Stack;

public class LC23MergeKSortedLists extends BasicTemplate {
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


    // 秒解 bj4
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(), cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((x, y) -> (x.val - y.val));
        for (ListNode n : lists) if (n != null) pq.add(n);
        while (!pq.isEmpty()) {
            ListNode next = pq.poll();
            cur.next = next;
            next = next.next;
            if(next != null) pq.add(next);
            cur = cur.next;
        }
        return dummy.next;
    }
}
