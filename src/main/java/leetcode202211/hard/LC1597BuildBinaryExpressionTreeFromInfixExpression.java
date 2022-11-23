package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC1597BuildBinaryExpressionTreeFromInfixExpression extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1597BuildBinaryExpressionTreeFromInfixExpression();
    }

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/discuss/1089208/Java-1ms-faster-than-99.09-very-easy-to-understand-recursive-approach
     * 相較用 stack 來解, deque 加上 dfs 更加直觀
     * 1. 把 String s 拆成 Deque<Character>
     * 2. dfs, 準備 Deque<Node> operands, Deque<Node> operators 存當下的遇到得 數字 或者 運算元
     * - 遇到數字就是放入 operands
     * - 遇到 '(' 遞迴 dfs
     * - 遇到 ')' 遞迴 dfs 結束, 結清當前的 () 內所有結果
     * - 遇到 [+ - * /] 如果是 當前是 [* /] 且之前是 [+ -] 就無法結清得繼續累積, 不然都可以結清
     *
     * eliminate 就是結清目前的 Deque<Node> operands, Deque<Node> operators 直到剩下一個 operand
     * */

    public Node expTree(String s) {
        Deque<Character> dq = new LinkedList<>();
        for (char c : s.toCharArray()) dq.offer(c);
        return helper(dq);
    }

    Node helper(Deque<Character> dq) {
        Deque<Node> operands = new LinkedList<>();
        Deque<Node> operators = new LinkedList<>();
        while (dq.size() > 0) {
            char c = dq.pollFirst();
            if (Character.isDigit(c)) operands.offerLast(new Node(c));
            else if (c == '(') operands.offerLast(helper(dq));
            else if (c == ')') {
                eliminate(operands, operators);
                return operands.pollLast();
            } else {
                if (operators.size() > 0 &&
                        !((operators.peekLast().val == '+' || operators.peekLast().val == '-') &&
                                (c == '*' || c == '/')))
                    eliminate(operands, operators);
                operators.offer(new Node(c));
            }
        }
        eliminate(operands, operators);
        return operands.pollLast();
    }

    void eliminate(Deque<Node> operands, Deque<Node> operators) {
        while (operands.size() != 1) {
            Node right = operands.pollLast();
            Node left = operands.pollLast();
            Node operator = operators.pollLast();
            operator.left = left;
            operator.right = right;
            operands.offerLast(operator);
        }
    }
}
