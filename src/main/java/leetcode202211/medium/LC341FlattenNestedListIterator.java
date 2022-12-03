package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC341FlattenNestedListIterator extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80147/Simple-Java-solution-using-a-stack-with-explanation
     * 用一個 stack, 把傳進來的 List<NestedInteger> nestedList 從尾巴放進去, 這樣 nestedList[0] 就會在 top
     * 但是每次 next 之前先看 hasNext,
     * hasNext 每次先看看 stack.top 是否是 nestedList, 如果是 還是得繼續把
     * - List<NestedInteger> nestedList 從尾巴放進去,
     * - 直到 stack.top.isInteger, 這樣就可以給下一次 next()
     *
     * 關鍵是 nestedList 從尾巴放進 stack, 然後遇到 stack.top 是否是 nestedList, 如果是 還是得繼續把 nestedList 從尾巴放進去,
     * 可以想像是, List<NestedInteger> nestedList 先不管裡面是否真的是 nestedList 先塞進 stack
     * 但真的要 pop 時候, 得確認是否是 stack 來解壓縮, 解壓縮也是往上堆疊, 因為解壓縮的值 還是要最先出來
     * 關鍵在於
     * 1. 透過 stack 特性來操作 解壓縮, 找到第一個 isInteger
     * 2. nestedList 從尾巴放進 stack
     * */
    public void NestedIterator(List<NestedInteger> nestedList) {
        populateStack(nestedList);
    }

    void populateStack(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            sk.push(nestedList.get(i));
        }
    }

    Stack<NestedInteger> sk = new Stack<>();

    public Integer next() {
        if(!hasNext()) return null;
        return sk.pop().getInteger();
    }

    public boolean hasNext() {
        while (sk.size() >0 && !sk.peek().isInteger()) {
            populateStack(sk.pop().getList());
        }
        return !sk.isEmpty();
    }
}
