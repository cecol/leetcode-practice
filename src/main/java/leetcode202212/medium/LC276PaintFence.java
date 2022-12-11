package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC276PaintFence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC276PaintFence();
    }

    /**
     * 我們從最後已塗 2 個來看
     * 1. 最後兩個同色 same = k 種
     * 2. 最後兩個不同色 diff = k * (k - 1); 種
     * 所以現在要填下一個
     * 下一個不同色 -> (diff + same) * (k - 1); 剩下 k-1 可以選 * (diff + same)
     * 下一個同色 -> 前兩個一定要不同色 -> 繼承 diff
     * 最後回傳 diff + same
     * */
    public int numWays(int n, int k) {
        if (n == 1) return k;
        int diff = k * (k - 1);
        int same = k;
        for (int i = 2; i < n; i++) {
            int temp = diff;
            diff = (diff + same) * (k - 1);
            same = temp;
        }
        return diff + same;
    }
}
