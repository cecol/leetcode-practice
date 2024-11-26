package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC328OddEvenLinkedList extends BasicTemplate {
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

    // 沒想到
    // 1. odd/even pointer 雙指針 一直往後代換就好
    // 2. 終止條件是 even, 因為 EVEN 會先到達 NUll
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenH = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                even = even.next;
                odd = odd.next;
            }
            odd.next = evenH;
        }
        return head;
    }
}
