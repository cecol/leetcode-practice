package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

public class LC141LinkedListCycle extends BasicTemplate {
    public static void main(String[] args) {
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode h) {
        if (h == null) return false;
        ListNode s1 = h, s2 = h.next;
        while (s2 != null && s2.next != null && s1 != s2) {
            s1 = s1.next;
            s2 = s2.next.next;
        }
        if (s1 == s2) return true;
        return false;
    }

}
