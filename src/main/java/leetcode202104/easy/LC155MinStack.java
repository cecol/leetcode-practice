package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC155MinStack extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC155MinStack();
    }

    /**
     * https://leetcode.com/problems/min-stack/discuss/49010/Clean-6ms-Java-solution
     * 我真沒想到可以這樣解有效且簡單
     * 1. linked list 實作 stack, 只要保有head 就好
     * 2. linked list Node 裡面記載到他為止看到的 min -> 這個真的很關鍵
     */
    public void MinStack() {

    }

    class Node {
        int val;
        int min;
        Node next;

        public Node(int v) {
            val = v;
            min = v;
        }

        public Node(int v, int m, Node n) {
            val = v;
            min = m;
            next = n;
        }
    }

    Node head;

    public void push(int x) {
        if (head == null) head = new Node(x);
        else head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
