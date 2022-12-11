package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC430FlattenAMultilevelDoublyLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC430FlattenAMultilevelDoublyLinkedList();
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    /**
     * 覺得 LinkedList 類題都很好用 遞迴 來解
     * 先把子問題下去解完, 就剩下 head 來看看怎麼加入已經 處理好的子問題
     * 1. 先 遞迴 把 head.next 拿下去 flatten, 因為 head.next 的 flatten 跟 head 無關
     * 2. 遞迴回來後 看看 head.child 存不存在 有的話就得繼續 遞迴 head.child
     * - 然後把 childFlat 跟 nextFlat 接上去
     * */
    public Node flatten(Node h) {
        if (h == null) return h;
        Node nextFlat = flatten(h.next);
        if (h.child != null) {
            Node childFlat = flatten(h.child);
            h.child = null;
            h.next = childFlat;
            childFlat.prev = h;
            if(nextFlat == null) return h;
            Node cur = childFlat;
            while (cur.next != null) cur = cur.next;
            cur.next = nextFlat;
            nextFlat.prev = cur;
        }
        return h;
    }
}
