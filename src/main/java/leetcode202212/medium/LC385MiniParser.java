package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.immutable.List;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC385MiniParser extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC385MiniParser();
    }

    class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return true;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 1;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

    /**
     * 花超長時間, 原本以為可以用 Queue 遞迴來解, 但跟計算機題目還是有差別 LC772BasicCalculatorIII
     * 因為 NestedInteger 表現行為不太一樣, 有空的也有 裏面是 0
     * 總之後來還是用 Stack解
     * https://leetcode.com/problems/mini-parser/discuss/86066/An-Java-Iterative-Solution/91186
     * 1. 很直觀看第一個是否是 [, 如果不是就是直接是一個 NestedInteger 數字回傳就好
     * 2. 如果是 [ => 結果一定是 NestedInteger is list, 可以直接 res = new NestedInteger()
     * 3. for i = 1 to s.length -> 開始往後看
     * - 我們不用 int num 累計目前看到的數值, 直接記載一個 start = i + 1
     * - 等到看到 ']' or ',' 代表結算, 代表得  Integer val = Integer.valueOf(s.substring(start, i));
     * - 遇到 ']' 就得 pop, 當前這個 list NestedInteger 已經沒用了
     * - 不用擔心他不見
     * - 因為前面遇到 '[' 時候就
     * - NestedInteger ni = new NestedInteger();
     * - stack.peek().add(ni);
     * - 把他加到更前面的 list NestedInteger
     * - 並且讓他成為當前最新的 list NestedInteger in stack
     */
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') return new NestedInteger(Integer.valueOf(s));
        NestedInteger res = new NestedInteger();
        Stack<NestedInteger> stack = new Stack<>();
        stack.push(res);
        int start = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedInteger ni = new NestedInteger();
                stack.peek().add(ni);
                stack.push(ni);
                start = i + 1;
            } else if (c == ']' || c == ',') {
                if (i > start) {
                    Integer val = Integer.valueOf(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                start = i + 1;
                if (c == ']') {
                    stack.pop();
                }
            }
        }
        return res;
    }
}
