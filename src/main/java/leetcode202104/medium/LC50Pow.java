package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC50Pow extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC50Pow();
        LC.myPow(2.0, -2);
    }

    /**
     * https://leetcode.com/problems/powx-n/discuss/19544/5-different-choices-when-talk-with-interviewers
     * 沒一開始想到 recursive 是比較有效的方式
     * 比較特別的是
     * 1. n < 0 case:
     * -> if (n < 0) return 1 / x * myPow(1 / x, -(n + 1));
     * -> 把 n < 0 的case 把 x倒數後, myPow 就用 1/x, 然後把剩下的n轉成正數帶下去算
     * 2. n == 0 本來就是回傳 1
     * 3. n == 2, 因為後面都是處理 n > 2 -> 有分n是 even or odd
     * -> even -> if (n % 2 == 0) return myPow(myPow(x, n / 2), 2);
     * -> odd -> x * myPow(myPow(x, n / 2), 2);
     */
    public double myPow(double x, int n) {
        if (x == 0D) return 0D;
        if (n < 0) return 1 / x * myPow(1 / x, -(n + 1));
        if (n == 0) return 1;
        if (n == 2) return x * x;
        if (n % 2 == 0) return myPow(myPow(x, n / 2), 2);
        else return x * myPow(myPow(x, n / 2), 2);
    }
}
