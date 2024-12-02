package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC61RotateList extends BasicTemplate {
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

    // 直觀可以解開,
    // 1. 先算 SIZE
    // 2. 先走 K 步, 找到下一個 Head, 就可以截斷
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }
        k = k % size;
        tail = head;
        ListNode cut = head;
        while (k > 0) {
            tail = tail.next;
            k--;
        }
        while (tail.next != null) {
            tail = tail.next;
            cut = cut.next;
        }
        tail.next = head;
        head = cut.next;
        cut.next = null;
        return head;
    }
}
