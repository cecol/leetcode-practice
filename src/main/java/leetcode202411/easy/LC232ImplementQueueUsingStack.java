package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC232ImplementQueueUsingStack extends BasicTemplate {
    public static void main(String[] args) {
    }

    class MyQueue {

        Stack<Integer> sk1 = new Stack<>();
        Stack<Integer> sk2 = new Stack<>();
        public MyQueue() {

        }

        public void push(int x) {
            sk1.push(x);
        }

        public int pop() {
            while(!sk1.empty()) sk2.push(sk1.pop());
            int res = sk2.pop();
            while(!sk2.empty()) sk1.push(sk2.pop());
            return res;
        }

        public int peek() {
            while(!sk1.empty()) sk2.push(sk1.pop());
            int res = sk2.peek();
            while(!sk2.empty()) sk1.push(sk2.pop());
            return res;
        }

        public boolean empty() {
            return sk1.empty();
        }
    }

}
