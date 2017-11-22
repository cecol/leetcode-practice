package leetcode_old;

public class PartitionList {

    public static void main(String[] a) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        n1.next = n2;
        partition(n1, 0);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode rootFront = null, rootTail = null, front = null, tail = null;
        while (head != null) {
            ListNode n = new ListNode(head.val);
            if (head.val < x) {
                if (rootFront == null) rootFront = front = n;
                else {
                    front.next = n;
                    front = front.next;
                }
            } else {
                if (tail == null) rootTail = tail = n;
                else {
                    tail.next = n;
                    tail = tail.next;
                }
            }
            head = head.next;
        }
        if (front == null) rootFront = front = rootTail;
        else front.next = rootTail;
        return rootFront;
    }
}
