package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC432AllOoneDataStructure extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC432AllOoneDataStructure();
    }

    /**
     * https://leetcode.com/problems/all-oone-data-structure/discuss/403466/Accepted-Java-solution-using-HashMap-and-Doubly-Linked-List
     * 1. 維持一個 Node Linkedlist, 每個 Node, 代表 inc/dec 的值 以及 有哪些 keys 是這個數值
     * 2. head.next 最小, tail.pre 最大
     * 3. Map<String, Node> kn 記載每個 key 是在哪個 node
     * 4. removeK, 當某個 key inc/dec key 會往下個或者前一個 node 移動,
     * - 如果 node.keys.size == 0, 就是該node 要移出 list -> deleteNode()
     * 5. inc - 如果有 key in Map<String, Node> kn, 就是這個 key node 往後面 Node move
     * - 有 Node, 就移過去, 沒有就 new Node, 然後 insertN
     * - 如果是 new key, 就是從 1 開始, 看是 head.next 是 1 or insertN
     * 6. dec - 如果沒 key, 就 return,
     * - 如果有 key in Map<String, Node> kn, 就是這個 key node 往前面 Node move
     * - 如果 node.val == 1 直接刪除就好
     * - 如果 node.pre.val == node.val - 1 往前面 Node move
     * - 最後就是 insertN new Node
     * - 然後 removeK
     */
    public void AllOne() {
        head.next = tail;
        tail.pre = head;
    }

    class Node {
        int val;
        Set<String> keys = new HashSet<>();
        Node pre, next;

        Node(int v) {
            val = v;
        }

        Node(String key, int v) {
            val = v;
            keys.add(key);
        }
    }

    Map<String, Node> kn = new HashMap<>();
    Node head = new Node(-1), tail = new Node(-1);

    void deleteNode(Node n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
        n.pre = null;
        n.next = null;
    }

    void removeK(Node n, String k) {
        n.keys.remove(k);
        if (n.keys.size() == 0) deleteNode(n);
    }

    void insertN(Node n, Node newN) {
        newN.next = n.next;
        newN.pre = n;
        n.next.pre = newN;
        n.next = newN;
    }

    public void inc(String k) {
        if (kn.containsKey(k)) {
            Node n = kn.get(k);
            if (n.val + 1 == n.next.val) {
                n.next.keys.add(k);
                kn.put(k, n.next);
            } else {
                Node newN = new Node(k, n.val + 1);
                insertN(n, newN);
                kn.put(k, newN);
            }
            removeK(n, k);
        } else {
            if (head.next.val == 1) {
                head.next.keys.add(k);
                kn.put(k, head.next);
            } else {
                Node newN = new Node(k, 1);
                insertN(head, newN);
                kn.put(k, newN);
            }
        }
    }

    public void dec(String k) {
        if (!kn.containsKey(k)) return;
        Node n = kn.get(k);
        if (n.val == 1) kn.remove(k);
        else if (n.val - 1 == n.pre.val) {
            n.pre.keys.add(k);
            kn.put(k, n.pre);
        } else {
            Node newN = new Node(k, n.val - 1);
            kn.put(k, newN);
            insertN(n.pre, newN);
        }
        removeK(n, k);
    }

    public String getMaxKey() {
        return tail.pre.keys.size() == 0 ? "" : tail.pre.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next.keys.size() == 0 ? "" : head.next.keys.iterator().next();
    }
}
