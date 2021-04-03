package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1472DesignBrowserHistory extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1472DesignBrowserHistory();
    }

    /**
     * 看了一下幾種實作
     * 1. Two Stack -> 簡單明瞭
     * 2. List -> double linked list or list with manipulated index -> 複雜
     * 3. HashMap -> 也是有點扭曲
     * https://leetcode.com/problems/design-browser-history/discuss/674486/Two-Stacks-Pretty-code.
     * two stack 比較直觀
     * 1. history stack 往回看 & future stack 往前看
     * 2. visit 就塞進 history, 且 future 清空, 因為沒有前面
     * 3. back -> 開始有前面, 所以 history 每清一個出來(back one step) 都塞進 future -> 有現在往回看就有未來往前看
     * -> history 只能退到 size 1, 回傳 history top
     * 4. forward -> 開始有後面, 所以 future 每清一個出來(forward one step) 都塞進 history -> 有現在往前看就未來的有往回看
     * -> future 可能會退完, 所以還是回傳 history top
     * history 一定都會至少保留1(畢竟有個homepage起點)
     * future 是一直都有機會被清空
     * */
    Stack<String> history = new Stack<>();
    Stack<String> future = new Stack<>();
    public void BrowserHistory(String homepage) {
        history.push(homepage);
    }

    public void visit(String url) {
        history.push(url);
        future = new Stack<>();
    }

    public String back(int steps) {
        while(steps > 0 && history.size() > 1) {
            future.push(history.pop());
            steps--;
        }
        return history.peek();
    }

    public String forward(int steps) {
        while(steps > 0 && future.size() > 0) {
            history.push(future.pop());
            steps--;
        }
        return history.peek();
    }
}
