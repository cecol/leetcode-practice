package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC143ReorderList extends BasicTemplate {
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

    // 沒過
    // 有想到對半 & reverse 後半
    // 後續交錯不好處理
    // head & rev != null
    // 先拿出下一個 t1 = head.next, t2 = h2.next
    // 交錯 - head.next = h2, h2.next = t1
    // 往前走 head = t1, h2 = t2
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode f = head, s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }
        ListNode h2 = rev(s.next);
        s.next = null;

        while (head != null && h2 != null) {
            ListNode t1 = head.next;
            ListNode t2 = h2.next;
            h2.next = t1;
            head.next = h2;
            head = t1;
            h2 = t2;
        }
    }

    ListNode rev(ListNode h) {
        if (h == null || h.next == null) return h;
        ListNode r = rev(h.next);
        h.next.next = h;
        h.next = null;
        return r;
    }

}
