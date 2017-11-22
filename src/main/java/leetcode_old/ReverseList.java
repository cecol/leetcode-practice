package leetcode_old;

public class ReverseList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rear = head;
        ListNode preRear = head;
        while (rear.next != null) rear = rear.next;
        while (preRear.next != rear) preRear = preRear.next;
        preRear.next = null;
        ListNode reversed = reverseList(head);
        rear.next = reversed;
        return rear;
    }
}
