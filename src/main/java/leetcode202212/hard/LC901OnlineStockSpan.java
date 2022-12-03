package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC901OnlineStockSpan extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC901OnlineStockSpan();
    }

    /**
     * 很直觀的單調遞減
     * 1. Stack 裏面放上 int[i, j], i = days, j = price
     * 2. Stack 初始化放一個 [0 day, 100001: max price]
     * 3. 只要當前 price > sk.peek().price -> stack.pop
     * 4. 看看當前 return nowDays - stack.peek().day
     * 5. stack.push(new int[]{nowDays++, price})
     * */
    public void StockSpanner() {
        sk.push(new int[]{0, 100001});
    }
    int idx = 1;
    Stack<int[]> sk = new Stack<>();

    public int next(int price) {
        while (sk.size() > 0 && sk.peek()[1] <= price) sk.pop();
        int res = idx - sk.peek()[0];
        sk.push(new int[]{idx++, price});
        return res;
    }
}
