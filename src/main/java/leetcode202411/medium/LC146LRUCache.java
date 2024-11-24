package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC146LRUCache extends BasicTemplate {
    public static void main(String[] args) {
    }

    class Node {
        Node next = null, pre = null;
        int v;
        int k;

        Node(int key, int value) {
            k = key;
            v = value;
        }
    }

    Map<Integer, Node> kv = new HashMap<>();
    Node head = null, tail = null;
    int cap = 0, count = 0;

    // 沒過, 有想起事 linked list
    // 但沒想好是 要獨立做好 delete(Node n) & add(Node n) 才方便
    public void LRUCache(int capacity) {
        cap = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    void delete(Node n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }

    void add(Node n) {
        n.next = head.next;
        n.next.pre = n;
        n.pre = head;
        head.next = n;
    }

    public int get(int key) {
        if (kv.get(key) != null) {
            Node n = kv.get(key);
            delete(n);
            add(n);
            return n.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (kv.get(key) != null) {
            Node n = kv.get(key);
            n.v = value;
            delete(n);
            add(n);
        } else {
            Node n = new Node(key, value);
            kv.put(key, n);
            add(n);
            if (count < cap) count++;
            else {
                kv.remove(tail.pre.k);
                delete(tail.pre);
            }
        }
    }

}
