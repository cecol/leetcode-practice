package leetcode202009.hard;

import leetcode202009.BasicTemplate;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC23MergeKSortedLists extends BasicTemplate {

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

  public static void main(String[] args) {
    var LC = new LC23MergeKSortedLists();
    var r = LC.mergeKLists(null);
  }

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
      @Override
      public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
      }
    });
    for (ListNode n : lists) if (n != null) q.offer(n);
    if (q.isEmpty()) return null;
    ListNode r = new ListNode(0);
    ListNode t = r;
    while (!q.isEmpty()) {
      t.next = q.poll();
      t = t.next;
      if(t.next != null) q.offer(t.next);
    }
    return r.next;
  }
}
