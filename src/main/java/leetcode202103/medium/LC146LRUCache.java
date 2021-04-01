package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC146LRUCache extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC146LRUCache();
    }

    class Node {
        int k;
        int v;
        Node pre;
        Node next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    /**
     * 原本一直以為用 PriorityQueue來解, 但完全想錯
     * 自建 double linked list 最快
     * https://leetcode.com/problems/lru-cache/discuss/45922/JAVA-Easy-Version-To-Understand!!!!
     */
    int cap = 0, count = 0;
    Map<Integer, Node> m = new HashMap<>();
    Node head, tail;

    public void LRUCache(int capacity) {
        this.cap = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        count = 0;
    }

    private void deleteNode(Node n) {
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }

    private void addToHead(Node n) {
        n.next = head.next;
        n.next.pre = n;
        n.pre = head;
        head.next = n;
    }

    public int get(int key) {
        if (m.get(key) != null) {
            Node n = m.get(key);
            deleteNode(n);
            addToHead(n);
            return n.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (m.get(key) != null) {
            Node n = m.get(key);
            n.v = value;
            deleteNode(n);
            addToHead(n);
        } else {
            Node n = new Node(key, value);
            m.put(key,n);
            addToHead(n);
            if(count < cap) count++;
            else {
                m.remove(tail.pre.k);
                deleteNode(tail.pre);
            }
        }
    }
}
